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

package com.huawei.kunpeng.hyper.tuner.setting.sysperf.tasktemplete;

import com.huawei.kunpeng.hyper.tuner.common.SettingCommonConfigurable;
import com.huawei.kunpeng.hyper.tuner.common.constant.sysperf.TaskTemplateContent;
import com.huawei.kunpeng.hyper.tuner.setting.user.ConfigurableAbstractProvider;
import com.huawei.kunpeng.hyper.tuner.toolview.panel.impl.sysperf.TaskTemplatePanel;
import com.huawei.kunpeng.intellij.common.UserInfoContext;

import com.intellij.openapi.options.Configurable;

import org.jetbrains.annotations.Nullable;

/**
 * 系统性能分析- 任务模板 面板XML配置类
 *
 * @since 2021-4-21
 */
public class TaskTemplateConfigProvider extends ConfigurableAbstractProvider {
    /**
     * 判断运行时条件是否满足
     *
     * @return isLogin
     */
    @Override
    public boolean canCreateConfigurable() {
        return UserInfoContext.getInstance().getUserName() != null;
    }

    @Nullable
    @Override
    public Configurable createConfigurable() {
        return new SettingCommonConfigurable(TaskTemplateContent.TASK_TEMPLATE_DIC, new TaskTemplatePanel());
    }

    /**
     * 执行自动登录之后，返回当前是否展示面板
     * 判断用户是否登录
     *
     * @return boolean isCreate()
     */
    @Override
    public boolean isCreate() {
        return UserInfoContext.getInstance().getUserName() != null;
    }
}