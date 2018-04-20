package gov.jiusan.star.sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "sheet")
public class RatingSheetController {

    private final RatingSheetService service;

    @Autowired
    public RatingSheetController(RatingSheetService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public @ResponseBody gov.jiusan.star.sheet.model.RatingSheet createSheet(@RequestBody gov.jiusan.star.sheet.model.RatingSheet sheet) {
        sheet.setSeq(service.create(sheet));
        return sheet;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String retrieveSheet(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<RatingSheet> sheet = service.find(seq);
        if (sheet.isPresent()) {
            model.addAttribute("sheet", RatingSheetUtil.convert(sheet.get()));
            return "sheet/sheet_viewer";
        }
        return "error";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "editor")
    public String editSheet(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("sheet", new gov.jiusan.star.sheet.model.RatingSheet());
            return "sheet/sheet_editor";
        }
        Optional<RatingSheet> sheet = service.find(seq);
        if (sheet.isPresent()) {
            model.addAttribute("sheet", RatingSheetUtil.convert(sheet.get()));
            return "sheet/sheet_editor";
        }
        return "error";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "list")
    public String findAllSheets() {
        return "";
    }

}
