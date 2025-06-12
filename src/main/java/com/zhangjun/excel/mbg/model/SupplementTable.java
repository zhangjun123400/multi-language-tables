package com.zhangjun.excel.mbg.model;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author zhangjun
 * @Date 2025/6/11 21:29
 * @Version 1.0
 */
@Data
@Schema(name = "SupplementTable", description = "缺失表")
public class SupplementTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "简体中文")
    @ExcelProperty("简体中文")
    private String simplifiedChinese;

    @Schema(description = "英语")
    @ExcelProperty("英语")
    private String english;

    @Schema(description = "台湾正体")
    @ExcelProperty("台湾正体")
    private String taiwanTraditional;

    @Schema(description = "港澳繁体")
    @ExcelProperty("港澳繁体")
    private String hongkongTraditional;

    @Schema(description = "韩语")
    @ExcelProperty("韩语")
    private String korean;

    @Schema(description = "俄语")
    @ExcelProperty("俄语")
    private String russian;

    @Schema(description = "西班牙语")
    @ExcelProperty("西班牙语")
    private String spanish;

    @Schema(description = "意大利语")
    @ExcelProperty("意大利语")
    private String italian;

    @Schema(description = "法语")
    @ExcelProperty("法语")
    private String french;

    @Schema(description = "印度尼西亚语")
    @ExcelProperty("印度尼西亚语")
    private String indonesian;

    @Schema(description = "德语")
    @ExcelProperty("德语")
    private String german;

    @Schema(description = "波兰语")
    @ExcelProperty("波兰语")
    private String polish;

    @Schema(description = "越南语")
    @ExcelProperty("越南语")
    private String vietnamese;

    @Schema(description = "日语")
    @ExcelProperty("日语")
    private String japanese;

    @Schema(description = "泰语")
    @ExcelProperty("泰语")
    private String thai;

    @Schema(description = "土耳其语")
    @ExcelProperty("土耳其语")
    private String turkish;

    @Schema(description = "巴西葡萄牙语")
    @ExcelProperty("巴西葡萄牙语")
    private String brazilianPortuguese;

    @Schema(description = "荷兰语")
    @ExcelProperty("荷兰语")
    private String dutch;

    @Schema(description = "乌克兰语")
    @ExcelProperty("乌克兰语")
    private String ukrainian;

    @Schema(description = "捷克语")
    @ExcelProperty("捷克语")
    private String czech;

    @Schema(description = "罗马尼亚语")
    @ExcelProperty("罗马尼亚语")
    private String romanian;

    @Schema(description = "希腊语")
    @ExcelProperty("希腊语")
    private String greek;

    @Schema(description = "欧洲葡萄牙语")
    @ExcelProperty("欧洲葡萄牙语")
    private String europeanPortuguese;

    @Schema(description = "阿拉伯语")
    @ExcelProperty("阿拉伯语")
    private String arabic;

    @Schema(description = "希伯来语")
    @ExcelProperty("希伯来语")
    private String hebrew;

    @Schema(description = "挪威语")
    @ExcelProperty("挪威语")
    private String norwegian;

    @Schema(description = "芬兰语")
    @ExcelProperty("芬兰语")
    private String finnish;

    @Schema(description = "丹麦语")
    @ExcelProperty("丹麦语")
    private String danish;

    @Schema(description = "瑞典语")
    @ExcelProperty("瑞典语")
    private String swedish;

    @Schema(description = "匈牙利语")
    @ExcelProperty("匈牙利语")
    private String hungarian;

    @Schema(description = "拉美西班牙语")
    @ExcelProperty("拉美西班牙语")
    private String latinSpanish;

    @Schema(description = "斯洛伐克语")
    @ExcelProperty("斯洛伐克语")
    private String slovak;

}
