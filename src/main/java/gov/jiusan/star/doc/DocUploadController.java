package gov.jiusan.star.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "/upload")
public class DocUploadController {

    @GetMapping
    public String showUploadPage() {
        return "upload";
    }

    @PostMapping
    public String uploadDocument(@RequestParam("doc") MultipartFile doc) {
        return "redirect:/";
    }

}
