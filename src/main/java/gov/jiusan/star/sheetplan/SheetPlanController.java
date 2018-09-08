package gov.jiusan.star.sheetplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "sheet_plan")
@PreAuthorize("hasRole('ROLE_L1_ADM')")
public class SheetPlanController {

    private final SheetPlanService service;

    @Autowired
    public SheetPlanController(SheetPlanService service) {
        this.service = service;
    }

    @RequestMapping(path = "list")
    public String findAllSheetPlans(Model model) {
        model.addAttribute("sheetPlans", service.findAll());
        return "sheet_plan/sheet_plan_list";
    }

}
