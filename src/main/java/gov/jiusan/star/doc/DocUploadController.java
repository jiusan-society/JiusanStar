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
@RequestMapping(path = "doc")
public class DocUploadController {

    @GetMapping(path = "list")
    public String showUploadPage() {
        return "doc/doc_list";
    }

    @PostMapping(path = "upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file) {
        return "redirect:/doc/list";
    }

}
