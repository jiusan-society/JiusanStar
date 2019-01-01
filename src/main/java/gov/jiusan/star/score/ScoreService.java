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

package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author Marcus Lin
 */
@Service
public class ScoreService {

    private final ScoreRepository repository;

    @Autowired
    public ScoreService(ScoreRepository repository) {
        this.repository = repository;
    }

    public Score create(Score score) {
        Calendar now = Calendar.getInstance();
        score.setCreateTime(now);
        score.setLastUpdateTime(now);
        return repository.save(score);
    }

    public Score update(Score score) {
        score.setLastUpdateTime(Calendar.getInstance());
        return repository.save(score);
    }

    public Score find(Long seq) {
        return repository.findOne(seq);
    }
}
