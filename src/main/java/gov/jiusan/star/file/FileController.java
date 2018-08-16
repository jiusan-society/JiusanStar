package gov.jiusan.star.file;

import gov.jiusan.star.annotation.LoggedUser;
import gov.jiusan.star.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "file")
public class FileController {

    @Value("${app.root-dir}")
    private String rootDir;

    @GetMapping(path = "list")
    public String getFileList(Model model, @LoggedUser UserDetailsImpl user) {
        String position = rootDir + user.getOrg().getCode();
        Set<File> files = Arrays.stream(Objects.requireNonNull(new File(position).listFiles()))
            .sorted(Comparator.comparing(File::lastModified).reversed())
            .collect(Collectors.toCollection(LinkedHashSet::new));
        model.addAttribute("files", files);
        return "file/file_list";
    }

    @GetMapping(path = "delete")
    public String deleteFile(@RequestParam("name") String name, @LoggedUser UserDetailsImpl user) {
        String position = rootDir + user.getOrg().getCode();
        File file = new File(position + "/" + name);
        if (!file.exists()) {
            return "error";
        }
        return file.delete() ? "redirect:/file/list" : "error";
    }

    @GetMapping(path = "download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("name") String name, @LoggedUser UserDetailsImpl user) throws MalformedURLException, UnsupportedEncodingException {
        String position = rootDir + user.getOrg().getCode();
        File file = new File(position + "/" + name);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(resource.getFilename(), "utf-8") + "\"")
            .body(resource);
    }

    @PostMapping(path = "upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file, @LoggedUser UserDetailsImpl user) {
        String position = rootDir + user.getOrg().getCode();
        File f = new File(position + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(f); BufferedInputStream bis = new BufferedInputStream(file.getInputStream())) {
            byte[] span = new byte[256];
            for (int len = 0; len != -1; len = bis.read(span)) {
                fos.write(span, 0, len);
            }
        } catch (IOException e) {
        }
        return "redirect:/file/list";
    }

}
