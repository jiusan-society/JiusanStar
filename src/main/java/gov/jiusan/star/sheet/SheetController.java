package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.sheet.model.Details;
import gov.jiusan.star.sheet.model.Phase;
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "sheet")
@PreAuthorize("hasRole('ROLE_L1_ADM')")
public class SheetController {

    private final SheetService sService;

    private final OrgService oService;

    @Autowired
    public SheetController(SheetService service, OrgService oService) {
        this.sService = service;
        this.oService = oService;
    }

    @PostMapping
    public String createSheet(@ModelAttribute("sheet") @Valid gov.jiusan.star.sheet.model.Sheet sheet, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sheet/sheet_editor";
        }
        return "redirect:/sheet?seq=" + sService.create(sheet);
    }

    @GetMapping
    public String retrieveSheet(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", SheetUtil.convert(sheet.get()));
        return "sheet/sheet_viewer";
    }

    @PostMapping(path = "update")
    public String updateSheet(@RequestParam(value = "seq") Long seq, gov.jiusan.star.sheet.model.Sheet sheetModel, Model model) {
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        Sheet updatedSheet = sService.update(sheet.get(), sheetModel);
        model.addAttribute("sheet", SheetUtil.convert(updatedSheet));
        return "sheet/sheet_viewer";
    }

    @GetMapping(path = "editor")
    public String editSheet(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("sheet", new gov.jiusan.star.sheet.model.Sheet());
            return "sheet/sheet_editor";
        }
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", SheetUtil.convert(sheet.get()));
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "addPhase")
    public String addPhase(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet) {
        sheet.getPhases().add(new Phase());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removePhase")
    public String removePhase(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheet.getPhases().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "addDetails")
    public String addDetails(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheet.getPhases().get(rowId).getDetails().add(new Details());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removeDetails")
    public String removeDetails(@ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheet.getPhases().get(phaseIndex).getDetails().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addPhase")
    public String addPhase(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet) {
        sheet.getPhases().add(new Phase());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removePhase")
    public String removePhase(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheet.getPhases().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addDetails")
    public String addDetails(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheet.getPhases().get(rowId).getDetails().add(new Details());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removeDetails")
    public String removeDetails(@RequestParam("seq") Long seq, @ModelAttribute("sheet") gov.jiusan.star.sheet.model.Sheet sheet, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheet.getPhases().get(phaseIndex).getDetails().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @GetMapping(path = "list")
    public String findAllSheets(Model model) {
        List<gov.jiusan.star.sheet.model.Sheet> sheets = sService.findAll().stream()
            .map(SheetUtil::convert)
            .sorted(Comparator.comparing(gov.jiusan.star.sheet.model.Sheet::getCreateTime))
            .collect(Collectors.toList());
        model.addAttribute("sheets", sheets);
        return "sheet/sheet_list";
    }

    @GetMapping(path = "/dispatch")
    public String dispatchSheet(@RequestParam("seq") Long seq) {
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        List<Org> orgs = oService.findNonRootOrgs();
        // REMIND，当非根组织为空时，不得派发
        if (orgs.isEmpty()) {
            return "error";
        }
        sService.dispatchSheet(sheet.get(), orgs);
        return "redirect:/sheet_plan/list";
    }

    @GetMapping(path = "/delete")
    public String deleteSheet(@RequestParam("seq") Long seq) {
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        sService.delete(sheet.get());
        return "redirect:/sheet/list";
    }

}
