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

package gov.jiusan.star.org;

import gov.jiusan.star.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Service
public class OrgService {

    private final OrgRepository repository;
    @Value("${app.root-dir}")
    private String rootDir;

    @Autowired
    public OrgService(OrgRepository repository) {
        this.repository = repository;
    }

    public List<Org> findNonRootOrgs() {
        return repository.findOrgsByRootCodeIsNotNull();
    }

    public List<Org> findOrgsByParentCode(String code) {
        return repository.findOrgsByParentCode(code);
    }

    public Org createOrg(Org org) {
        return repository.save(org);
    }

    public Optional<Org> findOrgBySeq(Long seq) {
        return Optional.ofNullable(repository.findOne(seq));
    }

    public void loadFiles(Org org) {
        org.setFiles(FileUtil.getDirFiles(rootDir + org.getCode()));
    }

}
