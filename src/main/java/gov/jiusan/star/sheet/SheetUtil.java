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

import gov.jiusan.star.sheet.model.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
public class SheetUtil {

    public static Sheet convert(gov.jiusan.star.sheet.Sheet entity) {
        Sheet model = new Sheet();
        model.setSeq(entity.getSeq());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setMaxScore(entity.getMaxScore());
        Map<Long, List<Item>> itemsOfCategory = new TreeMap<>();
        for (Item item : entity.getDetails()) {
            itemsOfCategory.computeIfAbsent(item.getCategory().getSeq(), v -> new ArrayList<>()).add(item);
        }
        model.setCategories(entity.getCategories().stream().map(c -> convertCategory(c, itemsOfCategory)).collect(Collectors.toList()));
        model.setCreateTime(entity.getCreateTime());
        model.setLastUpdateTime(entity.getLastUpdateTime());
        return model;
    }

    private static Sheet.Category convertCategory(Category category, Map<Long, List<Item>> itemsOfCategory) {
        Sheet.Category model = new Sheet.Category();
        model.setSeq(category.getSeq());
        model.setName(category.getName());
        model.setMaxScore(category.getMaxScore());
        List<Item> items = itemsOfCategory.get(category.getSeq());
        model.setItems(items.stream().map(SheetUtil::convertItem).collect(Collectors.toList()));
        return model;
    }

    private static Sheet.Item convertItem(Item item) {
        Sheet.Item model = new Sheet.Item();
        model.setSeq(item.getSeq());
        model.setEachScore(item.getEachScore());
        model.setMaxScore(item.getMaxScore());
        model.setDescription(model.getDescription());
        return model;
    }
}
