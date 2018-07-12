package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.sheet.model.RatingDetails;
import gov.jiusan.star.sheet.model.RatingPhase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "sheet")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RatingSheetController {

    private final RatingSheetService rsService;

    private final OrgService oService;

    @Autowired
    public RatingSheetController(RatingSheetService service, OrgService oService) {
        this.rsService = service;
        this.oService = oService;
    }

    @PostMapping
    public String createSheet(@ModelAttribute("sheet") @Valid gov.jiusan.star.sheet.model.RatingSheet sheet, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sheet/sheet_editor";
        }
        return "redirect:/sheet?seq=" + rsService.create(sheet);
    }

    @GetMapping
    public String retrieveSheet(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<RatingSheet> sheet = rsService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", RatingSheetUtil.convert(sheet.get()));
        return "sheet/sheet_viewer";
    }

    @PostMapping(path = "update")
    public String updateSheet(@RequestParam(value = "seq") Long seq, gov.jiusan.star.sheet.model.RatingSheet sheetModel, Model model) {
        Optional<RatingSheet> sheet = rsService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        RatingSheet updatedSheet = rsService.update(sheet.get(), sheetModel);
        model.addAttribute("sheet", RatingSheetUtil.convert(updatedSheet));
        return "sheet/sheet_viewer";
    }

    @GetMapping(path = "editor")
    public String editSheet(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("sheet", new gov.jiusan.star.sheet.model.RatingSheet());
            return "sheet/sheet_editor";
        }
        Optional<RatingSheet> sheet = rsService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", RatingSheetUtil.convert(sheet.get()));
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "addPhase")
    public String addPhase(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet) {
        sheet.getRatingPhases().add(new RatingPhase());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removePhase")
    public String removePhase(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheet.getRatingPhases().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "addDetails")
    public String addDetails(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheet.getRatingPhases().get(rowId).getRatingDetails().add(new RatingDetails());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removeDetails")
    public String removeDetails(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheet.getRatingPhases().get(phaseIndex).getRatingDetails().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addPhase")
    public String addPhase(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet) {
        sheet.getRatingPhases().add(new RatingPhase());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removePhase")
    public String removePhase(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheet.getRatingPhases().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addDetails")
    public String addDetails(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheet.getRatingPhases().get(rowId).getRatingDetails().add(new RatingDetails());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removeDetails")
    public String removeDetails(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.RatingSheet sheet, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheet.getRatingPhases().get(phaseIndex).getRatingDetails().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @GetMapping(path = "list")
    public String findAllSheets(Model model) {
        model.addAttribute("sheets", rsService.findAll().stream().map(RatingSheetUtil::convert).collect(Collectors.toList()));
        return "sheet/sheet_list";
    }

    @GetMapping(path = "/dispatch")
    public String dispatchSheet(@RequestParam("seq") Long seq) {
        Optional<RatingSheet> sheet = rsService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        List<Org> orgs = oService.findNonRootOrgs();
        // REMIND，当非根组织为空时，不得派发
        if (orgs.isEmpty()) {
            return "error";
        }
        rsService.dispatchSheet(sheet.get(), orgs);
        return "sheet/sheet_list";
    }

}
