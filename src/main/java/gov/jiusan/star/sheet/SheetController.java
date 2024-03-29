/*
 * Copyright 2019 Marcus Lin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.jiusan.star.sheet;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.sheet.model.Sheet;
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

    private final CategoryService cService;

    @Autowired
    public SheetController(SheetService service, OrgService oService, CategoryService cService) {
        this.sService = service;
        this.oService = oService;
        this.cService = cService;
    }

    @GetMapping(path = "editor")
    public String getSheetEditor(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("sheet", new Sheet());
            model.addAttribute("selectableCategories", cService.findAll());
            return "sheet/sheet_editor";
        }
        Optional<gov.jiusan.star.sheet.Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", SheetUtil.convert(sheet.get()));
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping
    public String createSheet(@ModelAttribute("sheet") @Valid Sheet dto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sheet/sheet_editor";
        }
        return "redirect:/sheet?seq=" + sService.create(dto).getSeq();
    }

    @GetMapping
    public String retrieveSheet(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<gov.jiusan.star.sheet.Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        model.addAttribute("sheet", SheetUtil.convert(sheet.get()));
        return "sheet/sheet_viewer";
    }

    @PostMapping(path = "update")
    public String updateSheet(@RequestParam(value = "seq") Long seq, @ModelAttribute("sheet") @Valid Sheet dto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sheet/sheet_editor";
        }
        Optional<gov.jiusan.star.sheet.Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        return "redirect:/sheet?seq=" + sService.update(sheet.get(), dto).getSeq();
    }

    @GetMapping(path = "list")
    public String findAllSheets(Model model) {
        List<Sheet> sheets = sService.findAll().stream()
            .map(SheetUtil::convert)
            .sorted(Comparator.comparing(Sheet::getCreateTime))
            .collect(Collectors.toList());
        model.addAttribute("sheets", sheets);
        return "sheet/sheet_list";
    }

    @GetMapping(path = "dispatch")
    public String dispatchSheet(@RequestParam("seq") Long seq) {
        Optional<gov.jiusan.star.sheet.Sheet> sheet = sService.find(seq);
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
        Optional<gov.jiusan.star.sheet.Sheet> sheet = sService.find(seq);
        if (!sheet.isPresent()) {
            return "error";
        }
        sService.delete(sheet.get());
        return "redirect:/sheet/list";
    }

    /**
     * =============================================
     * 以下区块用于动态新增或删除评分表中的评分大类或细则
     * =============================================
     */

    @PostMapping(params = "addCategory")
    public String addCategory(@ModelAttribute("sheet") Sheet dto, Model model) {
        dto.getCategories().add(new Sheet.Category());
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "removeCategory")
    public String removeCategory(@ModelAttribute("sheet") Sheet dto, final HttpServletRequest request, Model model) {
        int rowId = Integer.valueOf(request.getParameter("removeCategory"));
        dto.getCategories().remove(rowId);
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping(params = "addItem")
    public String addItem(@ModelAttribute("sheet") Sheet dto, final HttpServletRequest request, Model model) {
        int rowId = Integer.valueOf(request.getParameter("addItem"));
        dto.getCategories().get(rowId).getItems().add(new Sheet.Item());
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }


    @PostMapping(params = "removeItem")
    public String removeItem(@ModelAttribute("sheet") Sheet dto, final HttpServletRequest request, Model model) {
        String value = request.getParameter("removeItem");
        int categoryIdx = Integer.valueOf(value.split("\\|")[0]);
        int itemIdx = Integer.valueOf(value.split("\\|")[1]);
        dto.getCategories().get(categoryIdx).getItems().remove(itemIdx);
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addCategory")
    public String addCategory(@RequestParam("seq") Long seq, @ModelAttribute("sheet") Sheet dto, Model model) {
        dto.getCategories().add(new Sheet.Category());
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removeCategory")
    public String removeCategory(@RequestParam("seq") Long seq, @ModelAttribute("sheet") Sheet dto, final HttpServletRequest request, Model model) {
        int rowId = Integer.valueOf(request.getParameter("removeCategory"));
        dto.getCategories().remove(rowId);
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "addItem")
    public String addItem(@RequestParam("seq") Long seq, @ModelAttribute("sheet") Sheet dto, final HttpServletRequest request, Model model) {
        int rowId = Integer.valueOf(request.getParameter("addItem"));
        dto.getCategories().get(rowId).getItems().add(new Sheet.Item());
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

    @PostMapping(path = "update", params = "removeItem")
    public String removeItem(@RequestParam("seq") Long seq, @ModelAttribute("sheet") Sheet dto, final HttpServletRequest request, Model model) {
        String value = request.getParameter("removeItem");
        int categoryIdx = Integer.valueOf(value.split("\\|")[0]);
        int itemIdx = Integer.valueOf(value.split("\\|")[1]);
        dto.getCategories().get(categoryIdx).getItems().remove(itemIdx);
        model.addAttribute("selectableCategories", cService.findAll());
        return "sheet/sheet_editor";
    }

}
