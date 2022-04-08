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

package com.huawei.kunpeng.hyper.tuner.common.utils;

import com.huawei.kunpeng.hyper.tuner.model.sysperf.SysPerfOperateLogBean;
import com.huawei.kunpeng.intellij.common.util.ExcelExportUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.List;

/**
 * 运行日志导入导出 工具类
 *
 * @since 2020/12/03
 */
public class SysPerfLogExcelExportUtil extends ExcelExportUtil<SysPerfOperateLogBean> {
    // 需要填充的数据信息
    private List<SysPerfOperateLogBean> data;

    @Override
    public List<SysPerfOperateLogBean> getData() {
        return data;
    }

    public void setData(List<SysPerfOperateLogBean> data) {
        this.data = data;
    }

    @Override
    public void formatData(HSSFSheet wbSheet, HSSFCellStyle style) {
        int rowNum = 2;
        for (SysPerfOperateLogBean bean : data) {
            HSSFRow row = wbSheet.createRow(rowNum);
            int j = 0;
            setCell(row, style, j++, bean.getUsername());
            setCell(row, style, j++, bean.getModuleType());
            setCell(row, style, j++, bean.getResult());
            setCell(row, style, j++, bean.getTime());
            setCell(row, style, j, bean.getInformation());
            rowNum++;
        }
    }
}