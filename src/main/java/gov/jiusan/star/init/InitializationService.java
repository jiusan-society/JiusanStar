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

package gov.jiusan.star.init;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.org.Role;
import gov.jiusan.star.org.RoleService;
import gov.jiusan.star.user.User;
import gov.jiusan.star.user.UserService;
import gov.jiusan.star.util.ExcelUtil;
import gov.jiusan.star.util.PasswordUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Service
public class InitializationService {

    private static final String LEVEL_1_ORG = "01";
    private static final String LEVEL_2_ORG = "02";
    private static final String LEVEL_3_ORG = "03";
    private static final Integer DATA_SHEET_INDEX = 0;
    private static final Integer DATA_START_INDEX = 1;

    private final OrgService orgService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitializationService(OrgService orgService, UserService userService, RoleService roleService) {
        this.orgService = orgService;
        this.userService = userService;
        this.roleService = roleService;
    }

    private static Org convertOrg(gov.jiusan.star.init.model.Org org) {
        Org o = new Org();
        o.setName(org.getName());
        o.setCode(org.getCode());
        o.setParentCode(org.getParentCode());
        o.setRootCode(org.getRootCode());
        return o;
    }

    private static User convertUser(gov.jiusan.star.init.model.Org org) {
        User u = new User();
        u.setNickname(org.getAdminUserName());
        u.setAccount(org.getAdminUserAccount());
        u.setPassword(PasswordUtil.encode(org.getAdminUserPassword()));
        return u;
    }

    private static String parseCode(String code) {
        return code.substring(0, 2);
    }

    void readSeedData(File file) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(file);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(DATA_SHEET_INDEX);
        List<gov.jiusan.star.init.model.Org> orgs = new ArrayList<>();
        for (int i = DATA_START_INDEX; !ExcelUtil.isRowNullOrEmpty(sheet.getRow(i)); i++) {
            Row r = sheet.getRow(i);
            gov.jiusan.star.init.model.Org o = new gov.jiusan.star.init.model.Org();
            String orgName = ExcelUtil.extractStringData(r, OrgDataColumn.ORG_NAME.getColIndex());
            String orgCode = ExcelUtil.extractStringData(r, OrgDataColumn.ORG_CODE.getColIndex());
            String parentCode = ExcelUtil.extractStringData(r, OrgDataColumn.ORG_PARENT_CODE.getColIndex());
            String rootCode = ExcelUtil.extractStringData(r, OrgDataColumn.ROOT_CODE.getColIndex());
            String username = ExcelUtil.extractStringData(r, OrgDataColumn.ADMIN_USER_NAME.getColIndex());
            String account = ExcelUtil.extractStringData(r, OrgDataColumn.ADMIN_USER_ACCOUNT.getColIndex());
            String pwd = ExcelUtil.extractStringData(r, OrgDataColumn.ADMIN_USER_PWD.getColIndex());
            o.setName(orgName);
            o.setCode(orgCode);
            o.setParentCode(parentCode);
            o.setRootCode(rootCode);
            o.setAdminUserName(username);
            o.setAdminUserAccount(account);
            o.setAdminUserPassword(pwd);
            orgs.add(o);
        }
        saveToDB(orgs);
    }

    private void saveToDB(List<gov.jiusan.star.init.model.Org> orgs) {
        List<User> users = initUsers(orgs);
        initRoles(orgs, users);
        initOrgs(orgs, users);
    }

    private List<User> initUsers(List<gov.jiusan.star.init.model.Org> orgs) {
        return orgs.stream().map(InitializationService::convertUser).map(userService::createUser).collect(Collectors.toList());
    }

    private void initRoles(List<gov.jiusan.star.init.model.Org> orgs, List<User> users) {
        // Level1
        Role l1Admin = new Role();
        l1Admin.setName("ROLE_L1_ADM");
        List<String> l1AdminAccounts = orgs.stream().filter(o -> LEVEL_1_ORG.equals(parseCode(o.getCode())))
            .map(gov.jiusan.star.init.model.Org::getAdminUserAccount)
            .collect(Collectors.toList());
        List<User> l1AdminUsers = users.stream()
            .filter(u -> l1AdminAccounts.contains(u.getAccount()))
            .collect(Collectors.toList());
        l1Admin.setUsers(l1AdminUsers);
        l1Admin.getUsers().forEach(u -> u.setRole(l1Admin));
        roleService.createRole(l1Admin);
        Role l1Normal = new Role();
        l1Normal.setName("ROLE_L1_USER");
        roleService.createRole(l1Normal);
        // Level2
        Role l2Admin = new Role();
        l2Admin.setName("ROLE_L2_ADM");
        List<String> l2AdminAccounts = orgs.stream().filter(o -> LEVEL_2_ORG.equals(parseCode(o.getCode())))
            .map(gov.jiusan.star.init.model.Org::getAdminUserAccount)
            .collect(Collectors.toList());
        List<User> l2AdminUsers = users.stream()
            .filter(u -> l2AdminAccounts.contains(u.getAccount()))
            .collect(Collectors.toList());
        l2Admin.setUsers(l2AdminUsers);
        l2Admin.getUsers().forEach(u -> u.setRole(l2Admin));
        roleService.createRole(l2Admin);
        Role l2Normal = new Role();
        l2Normal.setName("ROLE_L2_USER");
        roleService.createRole(l2Normal);
        // Level3
        Role l3Admin = new Role();
        l3Admin.setName("ROLE_L3_ADM");
        List<String> l3AdminAccounts = orgs.stream().filter(o -> LEVEL_3_ORG.equals(parseCode(o.getCode())))
            .map(gov.jiusan.star.init.model.Org::getAdminUserAccount)
            .collect(Collectors.toList());
        List<User> l3AdminUsers = users.stream()
            .filter(u -> l3AdminAccounts.contains(u.getAccount()))
            .collect(Collectors.toList());
        l3Admin.setUsers(l3AdminUsers);
        l3Admin.getUsers().forEach(u -> u.setRole(l3Admin));
        roleService.createRole(l3Admin);
        Role l3Normal = new Role();
        l3Normal.setName("ROLE_L3_USER");
        roleService.createRole(l3Normal);
    }

    private void initOrgs(List<gov.jiusan.star.init.model.Org> orgs, List<User> users) {
        // REMIND[2018-07-13][Marcus Lin]: 初始时一个组织仅有一个管理员用户
        orgs.stream().map(InitializationService::convertOrg).forEach(o -> {
            String account = orgs.stream().filter(org -> org.getCode().equals(o.getCode())).findFirst().get().getAdminUserAccount();
            User user = users.stream().filter(u -> u.getAccount().equals(account)).findFirst().get();
            user.setOrg(o);
            o.setUsers(Arrays.asList(user));
            orgService.createOrg(o);
        });
    }

    private enum OrgDataColumn {
        ORG_NAME(0),
        ORG_CODE(1),
        ORG_PARENT_CODE(2),
        ROOT_CODE(3),
        ADMIN_USER_NAME(4),
        ADMIN_USER_ACCOUNT(5),
        ADMIN_USER_PWD(6);

        private int colIndex;

        OrgDataColumn(int colIndex) {
            this.colIndex = colIndex;
        }

        public int getColIndex() {
            return colIndex;
        }

        public void setColIndex(int colIndex) {
            this.colIndex = colIndex;
        }
    }

}
