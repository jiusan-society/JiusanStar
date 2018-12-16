package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.sheet.model.SheetDTO;
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
    public String createSheet(@ModelAttribute("sheet") @Valid SheetDTO sheetDTO, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sheet/sheet_editor";
        }
        return "redirect:/sheet?seq=" + sService.create(sheetDTO);
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
    public String updateSheet(@RequestParam(value = "seq") Long seq, SheetDTO sheetDTO) {
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        sService.update(sheet.get(), sheetDTO);
        return "redirect:/sheet?seq=" + seq;
    }

    @GetMapping(path = "editor")
    public String editSheet(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("sheet", new SheetDTO());
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
    public String addPhase(@ModelAttribute("sheet") SheetDTO sheetDTO) {
        sheetDTO.getPhaseDTOs().add(new SheetDTO.PhaseDTO());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removePhase")
    public String removePhase(@ModelAttribute("sheet") SheetDTO sheetDTO, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheetDTO.getPhaseDTOs().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "addDetails")
    public String addDetails(@ModelAttribute("sheet") SheetDTO sheetDTO, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheetDTO.getPhaseDTOs().get(rowId).getDetailsDTOs().add(new SheetDTO.DetailsDTO());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removeDetails")
    public String removeDetails(@ModelAttribute("sheet") SheetDTO sheetDTO, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheetDTO.getPhaseDTOs().get(phaseIndex).getDetailsDTOs().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addPhase")
    public String addPhase(@RequestParam("seq") Long seq, @ModelAttribute("sheet") SheetDTO sheetDTO) {
        sheetDTO.getPhaseDTOs().add(new SheetDTO.PhaseDTO());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removePhase")
    public String removePhase(@RequestParam("seq") Long seq, @ModelAttribute("sheet") SheetDTO sheetDTO, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("removePhase"));
        sheetDTO.getPhaseDTOs().remove(rowId);
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addDetails")
    public String addDetails(@RequestParam("seq") Long seq, @ModelAttribute("sheet") SheetDTO sheetDTO, final HttpServletRequest request) {
        int rowId = Integer.valueOf(request.getParameter("addDetails"));
        sheetDTO.getPhaseDTOs().get(rowId).getDetailsDTOs().add(new SheetDTO.DetailsDTO());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removeDetails")
    public String removeDetails(@RequestParam("seq") Long seq, @ModelAttribute("sheet") SheetDTO sheetDTO, final HttpServletRequest request) {
        String value = request.getParameter("removeDetails");
        int phaseIndex = Integer.valueOf(value.split("\\|")[0]);
        int detailsIndex = Integer.valueOf(value.split("\\|")[1]);
        sheetDTO.getPhaseDTOs().get(phaseIndex).getDetailsDTOs().remove(detailsIndex);
        return "sheet/sheet_editor";
    }

    @GetMapping(path = "list")
    public String findAllSheets(Model model) {
        List<SheetDTO> sheetDTOs = sService.findAll().stream()
            .map(SheetUtil::convert)
            .sorted(Comparator.comparing(SheetDTO::getCreateTime))
            .collect(Collectors.toList());
        model.addAttribute("sheets", sheetDTOs);
        return "sheet/sheet_list";
    }

    @GetMapping(path = "dispatch")
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
        return "redirect:/sheetplan/list";
    }

    @GetMapping(path = "delete")
    public String deleteSheet(@RequestParam("seq") Long seq) {
        Optional<Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        sService.delete(sheet.get());
        return "redirect:/sheet/list";
    }

}
