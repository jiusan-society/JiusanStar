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

import gov.jiusan.star.sheet.model.SheetDTO;

import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
public class SheetUtil {

    public static SheetDTO convert(Sheet entity) {
        SheetDTO model = new SheetDTO();
        model.setSeq(entity.getSeq());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setMaxScore(entity.getMaxScore());
        model.setCategoryDTOS(entity.getPhases().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        model.setCreateTime(entity.getCreateTime());
        model.setLastUpdateTime(entity.getLastUpdateTime());
        return model;
    }

    static Sheet convert(SheetDTO model) {
        Sheet entity = new Sheet();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setMaxScore(model.getCategoryDTOS().stream().mapToInt(SheetDTO.CategoryDTO::getMaxScore).sum());
        entity.setPhases(model.getCategoryDTOS().stream().map(SheetUtil::convertRatingPhase).collect(Collectors.toList()));
        return entity;
    }

    static Category convertRatingPhase(SheetDTO.CategoryDTO model) {
        Category phase = new Category();
        phase.setName(model.getName());
        phase.setMaxScore(model.getMaxScore());
        phase.setDetails(model.getDetailsDTOs().stream().map(SheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return phase;
    }

    private static SheetDTO.CategoryDTO convertRatingPhase(Category phase) {
        SheetDTO.CategoryDTO model = new SheetDTO.CategoryDTO();
        model.setSeq(phase.getSeq());
        model.setName(phase.getName());
        model.setMaxScore(phase.getMaxScore());
        model.setDetailsDTOs(phase.getDetails().stream().map(SheetUtil::convertRatingDetails).collect(Collectors.toList()));
        return model;
    }

    private static Details convertRatingDetails(SheetDTO.DetailsDTO model) {
        Details details = new Details();
        details.setDescription(model.getDescription());
        details.setEachScore(model.getEachScore());
        details.setMaxScore(model.getMaxScore());
        return details;
    }

    private static SheetDTO.DetailsDTO convertRatingDetails(Details details) {
        SheetDTO.DetailsDTO model = new SheetDTO.DetailsDTO();
        model.setSeq(details.getSeq());
        model.setDescription(details.getDescription());
        model.setEachScore(details.getEachScore());
        model.setMaxScore(details.getMaxScore());
        return model;
    }
}
