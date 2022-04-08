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

package com.huawei.kunpeng.porting.action.install;

import com.huawei.kunpeng.intellij.ui.dialog.wrap.InstallUpgradeWrapDialog;
import com.huawei.kunpeng.intellij.ui.panel.InstallUpgradePanel;
import com.huawei.kunpeng.porting.common.constant.PortingUserManageConstant;
import com.huawei.kunpeng.porting.ui.dialog.wrap.PortingInstallUpgradeWrapDialog;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import org.jetbrains.annotations.NotNull;

/**
 * 安装菜单事件
 *
 * @since 2020-10-08
 */
public class IDEInstallAction extends AnAction {
    /**
     * 点击事件
     *
     * @param anActionEvent 事件
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        InstallAction installAction = new InstallAction();
        InstallUpgradePanel up = new InstallUpgradePanel(null,
            PortingUserManageConstant.INSTALL_TITLE, false, installAction);
        InstallUpgradeWrapDialog dialog = new PortingInstallUpgradeWrapDialog(
            PortingUserManageConstant.INSTALL_TITLE, up);
        up.setDialog(dialog);
        dialog.displayPanel();
    }
}
