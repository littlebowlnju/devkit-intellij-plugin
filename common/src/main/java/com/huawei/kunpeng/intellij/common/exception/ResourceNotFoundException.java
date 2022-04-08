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

package com.huawei.kunpeng.intellij.common.exception;

import com.huawei.kunpeng.intellij.common.enums.ErrorCode;
import com.huawei.kunpeng.intellij.common.enums.HttpStatus;

/**
 * The Class ResourceNotFoundException.
 *
 * @since 1.0.0
 */
public class ResourceNotFoundException extends IDEException {
    /**
     * Instantiates a new resource not found exception.
     *
     * @param resourceType the type of resource
     * @param resourceId the identity of resource
     */
    public ResourceNotFoundException(String resourceType, String resourceId) {
        super(HttpStatus.HTTP_400_BAD_REQUEST, ErrorCode.RESOURCE_NOT_FOUND, resourceType, resourceId);
    }
}
