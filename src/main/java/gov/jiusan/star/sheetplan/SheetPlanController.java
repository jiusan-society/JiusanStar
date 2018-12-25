package gov.jiusan.star.sheetplan;

import gov.jiusan.star.sheetplan.model.ReportDTO;
import gov.jiusan.star.sheetplan.model.SheetPlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "sheetplan")
@PreAuthorize("hasRole('ROLE_L1_ADM')")
public class SheetPlanController {

    private final SheetPlanService service;

    @Autowired
    public SheetPlanController(SheetPlanService service) {
        this.service = service;
    }

    @RequestMapping(path = "list")
    public String findAll(Model model) {
        List<SheetPlanDTO> plans = service.findEffectives().stream().map(SheetPlanUtil::convert).collect(Collectors.toList());
        // 用年份来归类，且依照年份从大到小排序
        Map<Integer, SheetPlanDTO> planOfYear = new TreeMap<>(Collections.reverseOrder());
        plans.forEach(p -> planOfYear.put(p.getEffectiveTime().get(Calendar.YEAR), p));
        model.addAttribute("planOfYear", planOfYear);
        return "sheetplan/sheet_plan_list";
    }

    /**
     * 统计报表
     *
     * @param model
     * @return
     */
    @RequestMapping(path = "reports")
    public String findReport(Model model) {
        List<SheetPlan> plans = service.findEffectives();
        Map<Integer, ReportDTO> reportsOfYear = new TreeMap<>(Collections.reverseOrder());
        plans.forEach(p -> reportsOfYear.put(p.getEffectiveTime().get(Calendar.YEAR), SheetPlanUtil.convertToReport(p)));
        model.addAttribute("reportsOfYear", reportsOfYear);
        return "sheetplan/report_list";
    }

}
