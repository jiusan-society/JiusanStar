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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Service
public class PhaseService {

    private final PhaseRepository repository;

    @Autowired
    public PhaseService(PhaseRepository repository) {
        this.repository = repository;
    }

    public Optional<Phase> findByName(String name) {
        return repository.findByName(name);
    }

    public Optional<Phase> find(Long seq) {
        return Optional.ofNullable(repository.findOne(seq));
    }

    public List<Phase> findAll() {
        return repository.findAll();
    }


}
