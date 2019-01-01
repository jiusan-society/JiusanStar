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

package gov.jiusan.star.file;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.org.OrgUtil;
import gov.jiusan.star.org.model.OrgDTO;
import gov.jiusan.star.user.CustomUserDetails;
import gov.jiusan.star.user.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "file")
public class FileController {

    private final OrgService oService;
    @Value("${app.root-dir}")
    private String rootDir;

    @Autowired
    public FileController(OrgService oService) {
        this.oService = oService;
    }

    @GetMapping(path = "list/own")
    public String findOwnFiles(Model model, @LoggedUser CustomUserDetails user) {
        String dir = rootDir + user.getOrg().getCode();
        model.addAttribute("files", FileUtil.getDirFiles(dir));
        return "file/file_list_own";
    }

    @GetMapping(path = "list/org")
    public String findOrgFilesByCode(@RequestParam("seq") Long seq, Model model, @LoggedUser CustomUserDetails user) {
        // seq 不存在时，返回错误页面
        if (seq == null) {
            return "error";
        }
        // 所查组织不属于自己可见范围内时，返回错误页面
        List<Long> subOrgSeqs = oService.findOrgsByParentCode(user.getOrg().getCode()).stream().map(Org::getSeq).collect(Collectors.toList());
        if (!subOrgSeqs.contains(seq)) {
            return "error";
        }
        // seq 有误时导致找不到组织时，返回错误页面
        Optional<Org> org = oService.findOrgBySeq(seq);
        if (!org.isPresent()) {
            return "error";
        }
        // 取出这个 org 下的文件并返回
        String dir = rootDir + org.get().getCode();
        model.addAttribute("orgName", org.get().getName());
        model.addAttribute("files", FileUtil.getDirFiles(dir));
        return "file/file_list_org";
    }

    @PreAuthorize("hasAnyRole('ROLE_L1_ADM', 'ROLE_L2_ADM')")
    @GetMapping(path = "list/children")
    public String findChildrenFiles(Model model, @LoggedUser CustomUserDetails user) {
        List<Org> subOrgs = oService.findOrgsByParentCode(user.getOrg().getCode());
        Map<OrgDTO, List<File>> orgFilesMap = new TreeMap<>();
        for (Org org : subOrgs) {
            String dir = rootDir + org.getCode();
            orgFilesMap.put(OrgUtil.convert(org), FileUtil.getDirFiles(dir));
        }
        model.addAttribute("orgFilesMap", orgFilesMap);
        return "file/file_list_children";
    }

    @PreAuthorize("hasAnyRole('ROLE_L1_ADM')")
    @GetMapping(path = "list/children/all")
    public String findAllOrgsFiles(Model model, @LoggedUser CustomUserDetails user) {
        List<Org> allOrgs = oService.findNonRootOrgs();
        Map<OrgDTO, List<File>> orgFilesMap = new TreeMap<>();
        for (Org org : allOrgs) {
            String dir = rootDir + org.getCode();
            orgFilesMap.put(OrgUtil.convert(org), FileUtil.getDirFiles(dir));
        }
        model.addAttribute("orgFilesMap", orgFilesMap);
        return "file/file_list_children";
    }

    @PreAuthorize("hasAnyRole('ROLE_L2_ADM', 'ROLE_L3_ADM')")
    @GetMapping(path = "delete")
    public String deleteFile(@RequestParam("name") String name, @LoggedUser CustomUserDetails user) {
        String position = rootDir + user.getOrg().getCode();
        File file = new File(position + "/" + name);
        if (!file.exists()) {
            return "error";
        }
        return file.delete() ? "redirect:/file/list/own" : "error";
    }

    @GetMapping(path = "download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("path") String path, @RequestParam("name") String name) throws MalformedURLException, UnsupportedEncodingException {
        File file = new File(rootDir + path + "/" + name);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(resource.getFilename(), "utf-8") + "\"")
            .body(resource);
    }

    @PreAuthorize("hasAnyRole('ROLE_L2_ADM', 'ROLE_L3_ADM')")
    @PostMapping(path = "upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file, @LoggedUser CustomUserDetails user) {
        String position = rootDir + user.getOrg().getCode();
        File f = new File(position + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(f); BufferedInputStream bis = new BufferedInputStream(file.getInputStream())) {
            byte[] span = new byte[256];
            for (int len = 0; len != -1; len = bis.read(span)) {
                fos.write(span, 0, len);
            }
        } catch (IOException e) {
        }
        return "redirect:/file/list/own";
    }

}
