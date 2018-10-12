package gov.jiusan.star.init;

import gov.jiusan.star.org.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "init")
public class InitializationController {

    private final InitializationService iService;

    private final OrgService oService;

    @Value("${app.root-dir}")
    private String rootDir;

    @Autowired
    public InitializationController(InitializationService iService, OrgService oService) {
        this.iService = iService;
        this.oService = oService;
    }

    @GetMapping
    public String showInitPage() {
        return "init";
    }

    @PostMapping
    public String importSeedData(@RequestParam("seedData") MultipartFile seedData) {
        var file = new File(seedData.getOriginalFilename());
        try (var fos = new FileOutputStream(file); var bis = new BufferedInputStream(seedData.getInputStream())) {
            byte[] span = new byte[256];
            for (int len = 0; len != -1; len = bis.read(span)) {
                fos.write(span, 0, len);
            }
        } catch (IOException e) {
        }
        iService.readSeedData(file);
        return "redirect:/";
    }

    @GetMapping(path = "dirs")
    public String generateDirForEachOrg() {
        oService.findNonRootOrgs().forEach(o -> {
            var dir = new File(rootDir + o.getCode());
            try {
                Files.createDirectories(dir.toPath());
            } catch (IOException e) {
            }
        });
        return "redirect:/";
    }

}
