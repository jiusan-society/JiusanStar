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

package gov.jiusan.star.org.model;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

/**
 * @author Marcus Lin
 */
public class OrgDTO implements Comparable<OrgDTO>, Serializable {

    private Long seq;
    private String code;
    private String name;
    private boolean fileExisted;

    public OrgDTO() {
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(OrgDTO o) {
        Collator collator = Collator.getInstance(Locale.CHINA);
        return collator.compare(this.name, o.getName());
    }

    public boolean isFileExisted() {
        return fileExisted;
    }

    public void setFileExisted(boolean fileExisted) {
        this.fileExisted = fileExisted;
    }
}
