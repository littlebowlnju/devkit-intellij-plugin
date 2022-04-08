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

package com.huawei.kunpeng.porting.process;

import com.huawei.kunpeng.intellij.common.bean.NotificationBean;
import com.huawei.kunpeng.intellij.common.bean.RequestDataBean;
import com.huawei.kunpeng.intellij.common.bean.ResponseBean;
import com.huawei.kunpeng.intellij.common.enums.HttpMethod;
import com.huawei.kunpeng.intellij.common.log.Logger;
import com.huawei.kunpeng.intellij.common.task.IDEBaseTask;
import com.huawei.kunpeng.intellij.common.util.CommonUtil;
import com.huawei.kunpeng.intellij.common.util.I18NServer;
import com.huawei.kunpeng.intellij.common.util.IDENotificationUtil;
import com.huawei.kunpeng.intellij.common.util.JsonUtil;
import com.huawei.kunpeng.porting.common.constant.PortingIDEConstant;
import com.huawei.kunpeng.porting.common.constant.enums.RespondStatus;
import com.huawei.kunpeng.porting.http.PortingHttpsServer;

import com.alibaba.fastjson.JSONObject;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.progress.ProgressIndicator;

import java.util.Date;
import java.util.Locale;

/**
 * 软件迁移模板任务管理
 *
 * @since 2021-01-28
 */
public class PortingTemplateProcess extends IDEBaseTask {
    private static final double PROGRESS_DONE = 100;

    private static final String PERCENT = "%";

    private String taskId;

    /**
     * 默认构造函数
     */
    public PortingTemplateProcess() {
        this(null);
    }

    /**
     * 推荐构造函数
     *
     * @param taskId 运行时参数
     */
    public PortingTemplateProcess(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 任务执行时的方法
     *
     * @param indicator 自定义参数
     */
    @Override
    public void runTask(ProgressIndicator indicator) {
        try {
            double currentProgress = 0.0;
            while (currentProgress < 100) {
                indicator.checkCanceled();
                currentProgress = portingTemplateStatus(indicator, currentProgress);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            Logger.error("runTask error: InterruptedException");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 任务取消后的动作
     *
     * @param indicator 自定义参数
     */
    @Override
    public void cancel(ProgressIndicator indicator) {
    }

    /**
     * 任务执行成功后的动作
     *
     * @param indicator 自定义参数
     */
    @Override
    public void success(ProgressIndicator indicator) {
    }

    /**
     * 任务执行进度查询，根据查询结果刷新进度条
     *
     * @param indicator       ProgressIndicator
     * @param currentProgress 当前进度值
     * @return 刷新后的进度值
     */
    public double portingTemplateStatus(ProgressIndicator indicator, double currentProgress) {
        double lastProgressNumber = 0.0;
        String url = String.format(Locale.ROOT, PortingIDETask.PROCESS_URL,
            PortingIDETask.PORTING_TEMPLATE_TASK_TYPE, taskId) + "&timestamp=" + new Date().getTime();
        ResponseBean responseData = PortingHttpsServer.INSTANCE.requestData(
            new RequestDataBean(PortingIDEConstant.TOOL_NAME_PORTING, url, HttpMethod.GET.vaLue(), ""));
        if (responseData == null) {
            return lastProgressNumber;
        }
        String statusCode = responseData.getStatus(); // resp.status
        if (statusCode.equals(RespondStatus.PROCESS_STATUS_NORMAL.value())) {
            JSONObject jsonMessage = JsonUtil.getJsonObjectFromJsonStr(responseData.getData());
            lastProgressNumber = jsonMessage.getInteger(PortingIDETask.PROGRESS);
            int status = jsonMessage.getInteger(PortingIDETask.RUNNING_STATUS); // resp.data中包含的任务状态返回值
            String dataStatus = String.valueOf(status);
            String info = CommonUtil.getRepDataInfo(responseData, "info", "infochinese");
            if (dataStatus.equals(RespondStatus.PROCESS_PORTING_TEMPLATE_STATUS_SUCCESS.value())
                && lastProgressNumber == PROGRESS_DONE) {
                updateProgressBar(indicator, lastProgressNumber, PROGRESS_DONE + PERCENT);
                // 弹窗提示任务执行成功
                IDENotificationUtil.notificationCommon(
                    new NotificationBean("", info, NotificationType.INFORMATION));
            } else if (dataStatus.equals(RespondStatus.PROCESS_PORTING_TEMPLATE_STATUS_FAILED.value())) {
                info = info + I18NServer.toLocale("plugins_porting_faq_tips");
                IDENotificationUtil.notificationForHyperlink(new NotificationBean("", info, NotificationType.ERROR),
                    op -> CommonUtil.openURI(I18NServer.toLocale("plugins_porting_template_faq_url")));
            } else {
                // 任务正在执行中
                if (lastProgressNumber < currentProgress) {
                    lastProgressNumber = currentProgress;
                }
                String progressVal = lastProgressNumber + PERCENT;
                updateProgressBar(indicator, lastProgressNumber,
                    info + " " + progressVal);
            }
        }
        return lastProgressNumber;
    }

    /**
     * 刷新进度条进程及状态说明
     *
     * @param indicator   ProgressIndicator
     * @param progressNum 当前进度值
     * @param tips        状态说明
     */
    public void updateProgressBar(ProgressIndicator indicator, double progressNum, String tips) {
        indicator.setFraction(progressNum / 100);
        indicator.setText(tips);
    }
}
