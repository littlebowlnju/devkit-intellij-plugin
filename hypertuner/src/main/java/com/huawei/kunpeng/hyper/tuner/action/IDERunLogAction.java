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

package com.huawei.kunpeng.hyper.tuner.action;

import com.huawei.kunpeng.hyper.tuner.toolview.dialog.impl.logger.RunLogDialog;
import com.huawei.kunpeng.hyper.tuner.toolview.panel.impl.RunLogPanel;
import com.huawei.kunpeng.intellij.common.action.IDETableCommonAction;

import com.intellij.ui.AnActionButton;

import javax.swing.JTable;

/**
 * 日志下载Action
 *
 * @since 2020-10-30
 */
public class IDERunLogAction extends IDETableCommonAction {
    /**
     * 添加用户
     *
     * @param targetTable 目标表格
     */
    public IDERunLogAction(JTable targetTable) {
        super(targetTable);
    }

    @Override
    public void run(AnActionButton anActionButton) {
        RunLogDialog runLogDialog = new RunLogDialog("", new RunLogPanel("", "", true));
        runLogDialog.displayPanel();
    }
}
