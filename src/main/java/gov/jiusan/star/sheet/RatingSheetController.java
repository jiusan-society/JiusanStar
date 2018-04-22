package gov.jiusan.star.sheet;

import gov.jiusan.star.sheet.model.RatingDetails;
import gov.jiusan.star.sheet.model.RatingPhase;
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
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "sheet")
public class RatingSheetController {

    private final RatingSheetService rsService;

    @Autowired
    public RatingSheetController(RatingSheetService service) {
        this.rsService = service;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createSheet(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet) {
        return "redirect:/sheet?seq=" + rsService.create(sheet);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String retrieveSheet(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<RatingSheet> sheet = rsService.find(seq);
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
        // TODO，前端用的是 JQuery 的方式来做，现在这样捞出来在前端页面是不能显示数据的
        Optional<RatingSheet> sheet = rsService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", RatingSheetUtil.convert(sheet.get()));
        return "sheet/sheet_editor";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(params = "addPhase")
    public String addPhase(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet) {
        sheet.getRatingPhases().add(new RatingPhase());
        return "sheet/sheet_editor";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(params = "removePhase")
    public String removePhase(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheet.getRatingPhases().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(params = "addDetails")
    public String addDetails(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheet.getRatingPhases().get(rowId).getRatingDetails().add(new RatingDetails());
        return "sheet/sheet_editor";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(params = "removeDetails")
    public String removeDetails(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheet.getRatingPhases().get(phaseIndex).getRatingDetails().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "list")
    public String findAllSheets(Model model) {
        model.addAttribute("sheets", rsService.findAll().stream().map(RatingSheetUtil::convert).collect(Collectors.toList()));
        return "sheet/sheet_list";
    }

}
