package gov.jiusan.star.file;

import gov.jiusan.star.annotation.LoggedUser;
import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "file")
public class FileController {

    private final OrgService oService;
    private final FileService fService;
    @Value("${app.root-dir}")
    private String rootDir;

    @Autowired
    public FileController(OrgService oService, FileService fService) {
        this.oService = oService;
        this.fService = fService;
    }

    @GetMapping(path = "list/own")
    public String getOwnFiles(Model model, @LoggedUser CustomUserDetails user) {
        String dir = rootDir + user.getOrg().getCode();
        model.addAttribute("files", fService.getDirFiles(dir));
        return "file/file_list_own";
    }

    @PreAuthorize("hasRole('ROLE_L1_ADM')")
    @GetMapping(path = "list/all")
    public String getAllFiles(Model model) {
        File[] dirs = Objects.requireNonNull(new File(rootDir).listFiles());
        Map<Org, List<File>> orgFilesMap = new HashMap<>();
        for (File dir : dirs) {
            Org org = oService.findByCode(dir.getName());
            orgFilesMap.put(org, fService.getDirFiles(dir));
        }
        model.addAttribute("orgFilesMap", orgFilesMap);
        return "file/file_list_all";
    }

    @PreAuthorize("hasAnyRole('ROLE_L2_ADM', 'ROLE_L3_ADM')")
    @GetMapping(path = "delete")
    public String deleteFile(@RequestParam("name") String name, @LoggedUser CustomUserDetails user) {
        String position = rootDir + user.getOrg().getCode();
        File file = new File(position + "/" + name);
        if (!file.exists()) {
            return "error";
        }
        return file.delete() ? "redirect:/file/list/own" : "error";
    }

    @GetMapping(path = "download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("path") String path, @RequestParam("name") String name) throws MalformedURLException, UnsupportedEncodingException {
        File file = new File(rootDir + path + "/" + name);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(resource.getFilename(), "utf-8") + "\"")
            .body(resource);
    }

    @PreAuthorize("hasAnyRole('ROLE_L2_ADM', 'ROLE_L3_ADM')")
    @PostMapping(path = "upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file, @LoggedUser CustomUserDetails user) {
        String position = rootDir + user.getOrg().getCode();
        File f = new File(position + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(f); BufferedInputStream bis = new BufferedInputStream(file.getInputStream())) {
            byte[] span = new byte[256];
            for (int len = 0; len != -1; len = bis.read(span)) {
                fos.write(span, 0, len);
            }
        } catch (IOException e) {
        }
        return "redirect:/file/list/own";
    }

}
