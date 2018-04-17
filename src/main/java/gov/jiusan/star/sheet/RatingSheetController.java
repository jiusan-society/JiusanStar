package gov.jiusan.star.sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    public String createSheet(@ModelAttribute gov.jiusan.star.sheet.model.RatingSheet sheet) {
        return "redirect:/sheet?seq=" + service.create(sheet);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String retrieveSheet(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<RatingSheet> sheet = service.find(seq);
        if (sheet.isPresent()) {
            model.addAttribute("sheet", RatingSheetUtil.convertToModel(sheet.get()));
            return "sheet_viewer";
        }
        return "error";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "editor")
    public String editSheet(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("sheet", new gov.jiusan.star.sheet.model.RatingSheet());
            return "sheet_editor";
        }
        Optional<RatingSheet> sheet = service.find(seq);
        if (sheet.isPresent()) {
            model.addAttribute("sheet", RatingSheetUtil.convertToModel(sheet.get()));
            return "sheet_editor";
        }
        return "error";
    }

    @PostMapping(params = {"addPhase"})
    public String addPhase(final gov.jiusan.star.sheet.model.RatingSheet ratingSheet) {
        return "sheet_editor";
    }

    @PostMapping(params = {"removePhase"})
    public String removePhase(final gov.jiusan.star.sheet.model.RatingSheet ratingSheet, final HttpServletRequest req) {
        return "sheet_editor";
    }

    @PostMapping(params = {"addDetail"})
    public String addDetail(final gov.jiusan.star.sheet.model.RatingSheet ratingSheet) {
        return "sheet_editor";
    }

    @PostMapping(params = {"removeDetail"})
    public String removeDetail(final gov.jiusan.star.sheet.model.RatingSheet ratingSheet, final HttpServletRequest req) {
        return "sheet_editor";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "list")
    public String findAllSheets() {
        return "";
    }

}
