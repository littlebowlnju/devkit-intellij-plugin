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

package com.huawei.kunpeng.hyper.tuner.toolview.dialog.impl.webservercert;

import com.huawei.kunpeng.hyper.tuner.action.panel.webservercert.TuningWebServerCertificateAction;
import com.huawei.kunpeng.hyper.tuner.common.i18n.TuningI18NServer;
import com.huawei.kunpeng.hyper.tuner.toolview.panel.impl.webservercert.TuningExportWebServerCertificatePanel;
import com.huawei.kunpeng.intellij.common.i18n.CommonI18NServer;
import com.huawei.kunpeng.intellij.common.util.ValidateUtils;
import com.huawei.kunpeng.intellij.ui.dialog.ExportWebServerCertDialog;
import com.huawei.kunpeng.intellij.ui.enums.Dialogs;
import com.huawei.kunpeng.intellij.ui.panel.IDEBasePanel;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建服务证书弹框.
 *
 * @since 2012-10-12
 */
public class TuningExportWebServerCertDialog extends ExportWebServerCertDialog {
    /**
     * 帮助文档
     */
    private static final String HELP_URL = TuningI18NServer.toLocale("plugins_hyper_tuner_generate_csr_help_url");

    /**
     * 带位置信息的完整构造函数（不推荐使用）
     *
     * @param title      弹窗标题
     * @param dialogName 弹框名称
     * @param panel      需要展示的面板之一
     */
    public TuningExportWebServerCertDialog(String title, String dialogName, IDEBasePanel panel, Rectangle rectangle) {
        this.title =
                ValidateUtils.isEmptyString(title)
                        ? TuningI18NServer.toLocale("plugins_hyper_tuner_certificate_generation_file")
                        : title;
        this.dialogName =
                ValidateUtils.isEmptyString(dialogName)
                        ? Dialogs.EXPORT_WEB_SERVER_CERTIFICATE.dialogName()
                        : dialogName;
        this.mainPanel = panel;

        // 无位置信息时居中显示
        this.rectangle = rectangle;

        setOKAndCancelName(
                CommonI18NServer.toLocale("common_term_operate_ok"),
                CommonI18NServer.toLocale("common_term_operate_cancel"));
        // 设置帮助
        setHelp(TuningI18NServer.toLocale("plugins_hyper_tuner_generate_csr_file_help"), HELP_URL);

        // 初始化弹框内容
        initDialog();
    }

    /**
     * 不带位置信息的完整构造函数，代理生成时会使用
     *
     * @param title 弹窗标题
     * @param panel 需要展示的面板之一
     */
    public TuningExportWebServerCertDialog(String title, IDEBasePanel panel) {
        this(title, null, panel, null);
    }

    /**
     * 确认新增弱口令
     */
    @Override
    protected void onOKAction() {
        // 生成CSR文件
        if (mainPanel.getAction() instanceof TuningWebServerCertificateAction
                && mainPanel instanceof TuningExportWebServerCertificatePanel) {
            TuningExportWebServerCertificatePanel tuningExportWebServerCertificatePanel =
                    (TuningExportWebServerCertificatePanel) mainPanel;
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("country", tuningExportWebServerCertificatePanel.getCountryTextField().getText());
            paramMap.put("province", tuningExportWebServerCertificatePanel.getProvinceTextField().getText());
            paramMap.put("city", tuningExportWebServerCertificatePanel.getCityTextField().getText());
            paramMap.put("organization", tuningExportWebServerCertificatePanel.getOrgTextField().getText());
            paramMap.put("department", tuningExportWebServerCertificatePanel.getDepartmentTextField().getText());
            paramMap.put("commonName", tuningExportWebServerCertificatePanel.getCommonNameTextField().getText());
            TuningWebServerCertificateAction action = (TuningWebServerCertificateAction) mainPanel.getAction();
            action.exportCsrFile(paramMap);
        }
    }
}
