/*
 * Copyright 2019 Marcus Lin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gov.jiusan.star.doc;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.sheet.Category;
import gov.jiusan.star.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Service
public class DocumentService {

    private final DocumentRepository dRepository;

    public DocumentService(DocumentRepository dRepository) {
        this.dRepository = dRepository;
    }

    Document saveDocument(Category category, Org org, String dirPath, MultipartFile file) {
        FileUtil.saveToFile(file, dirPath);
        Document document = new Document();
        document.setCategory(category);
        document.setOrg(org);
        document.setName(file.getOriginalFilename());
        document.setType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        document.setUrl(dirPath + file.getOriginalFilename());
        Calendar now = Calendar.getInstance();
        document.setCreateTime(now);
        document.setLastUpdateTime(now);
        return dRepository.save(document);
    }

    void deleteDocument(String url) {
        Optional<Document> document = dRepository.findByUrl(url);
        document.ifPresent(dRepository::delete);
    }

}
