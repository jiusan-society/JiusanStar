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

package gov.jiusan.star.init.model;

import java.io.Serializable;

/**
 * @author Marcus Lin
 */
public class Org implements Serializable {

    private String name;
    private String code;
    private String parentCode;
    private String rootCode;
    private String adminUserName;
    private String adminUserAccount;
    private String adminUserPassword;

    public Org() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return "".equals(parentCode) ? null : parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getAdminUserAccount() {
        return adminUserAccount;
    }

    public void setAdminUserAccount(String adminUserAccount) {
        this.adminUserAccount = adminUserAccount;
    }

    public String getAdminUserPassword() {
        return adminUserPassword;
    }

    public void setAdminUserPassword(String adminUserPassword) {
        this.adminUserPassword = adminUserPassword;
    }

    public String getRootCode() {
        return "".equals(rootCode) ? null : rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
}
