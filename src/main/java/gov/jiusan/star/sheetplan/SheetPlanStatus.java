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

package gov.jiusan.star.sheetplan;

/**
 * @author Marcus Lin
 */
public enum SheetPlanStatus {
    /**
     * 正常可用状态
     */
    NORMAL,
    /**
     * 超过截止时间后变为逾期失效状态
     */
    EXPIRED,
    /**
     * 同一年下仅可有一张可用的 SheetPlan，其他需变为不可用状态
     */
    INVALID
}
