package gov.jiusan.star.batch;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(path = "/import")
public class BatchImportController {

    private final BatchImportService service;

    @Autowired
    public BatchImportController(BatchImportService service) {
        this.service = service;
    }

    @GetMapping
    public String showImportPage() {
        return "import";
    }

    @PostMapping
    public String importSeedData(@RequestParam("seedData") MultipartFile seedData) {
        File file = new File(seedData.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file); BufferedInputStream bis = new BufferedInputStream(seedData.getInputStream())) {
            byte[] span = new byte[256];
            for (int len = 0; len != -1; len = bis.read(span)) {
                fos.write(span, 0, len);
            }
        } catch (IOException e) {
        }
        service.processSeedData(file);
        return "redirect:/";
    }

}
