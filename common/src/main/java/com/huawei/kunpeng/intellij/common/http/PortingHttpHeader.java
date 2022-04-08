/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2022. All rights reserved.
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

package com.huawei.kunpeng.intellij.common.http;

import com.huawei.kunpeng.intellij.common.util.I18NServer;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * The class: PortingHttpHeader
 *
 * @since 2021-08-26
 */
@AllArgsConstructor
public class PortingHttpHeader implements HttpHeader {
    private String token;

    /**
     * Create Http header Map
     *
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> createReqHeaderMap() {
        HashMap<String, String> headerMap = new HashMap<>(3);
        headerMap.put("Authorization", token);
        headerMap.put("Accept-Language", I18NServer.getCurrentLanguage());
        headerMap.put("choice", "normal");
        return headerMap;
    }
}
