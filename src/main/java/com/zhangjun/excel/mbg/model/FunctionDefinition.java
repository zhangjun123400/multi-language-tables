package com.zhangjun.excel.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.ToString;


/**
 * <p>
 * 功能定义
 * </p>
 *
 * @author zhangjun
 * @since 2025-06-07
 */
@Getter
@Setter
@ToString
@TableName("function_definition")
@Schema(name = "FunctionDefinition", description = "功能定义")
public class FunctionDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    @Schema(description = "配置ID")
    @TableField("configuration_id")
    @ExcelProperty("配置ID")
    private String configurationId;

    @Schema(description = "配置名称")
    @TableField("configuration_name")
    @ExcelProperty("配置名称")
    private String configurationName;

    @Schema(description = "简体中文")
    @TableField("simplified_chinese")
    @ExcelProperty("简体中文")
    private String simplifiedChinese;

    @Schema(description = "英语")
    @TableField("english")
    @ExcelProperty("英语")
    private String english;

    @Schema(description = "台湾正体")
    @TableField("taiwan_traditional")
    @ExcelProperty("台湾正体")
    private String taiwanTraditional;

    @Schema(description = "港澳繁体")
    @TableField("hongkong_traditional")
    @ExcelProperty("港澳繁体")
    private String hongkongTraditional;

    @Schema(description = "韩语")
    @TableField("korean")
    @ExcelProperty("韩语")
    private String korean;

    @Schema(description = "俄语")
    @TableField("russian")
    @ExcelProperty("俄语")
    private String russian;

    @Schema(description = "西班牙语")
    @TableField("spanish")
    @ExcelProperty("西班牙语")
    private String spanish;

    @Schema(description = "意大利语")
    @TableField("italian")
    @ExcelProperty("意大利语")
    private String italian;

    @Schema(description = "法语")
    @TableField("french")
    @ExcelProperty("法语")
    private String french;

    @Schema(description = "印度尼西亚语")
    @TableField("indonesian")
    @ExcelProperty("印度尼西亚语")
    private String indonesian;

    @Schema(description = "德语")
    @TableField("german")
    @ExcelProperty("德语")
    private String german;

    @Schema(description = "波兰语")
    @TableField("polish")
    @ExcelProperty("波兰语")
    private String polish;

    @Schema(description = "越南语")
    @TableField("vietnamese")
    @ExcelProperty("越南语")
    private String vietnamese;

    @Schema(description = "日语")
    @TableField("japanese")
    @ExcelProperty("日语")
    private String japanese;

    @Schema(description = "泰语")
    @TableField("thai")
    @ExcelProperty("泰语")
    private String thai;

    @Schema(description = "土耳其语")
    @TableField("turkish")
    @ExcelProperty("土耳其语")
    private String turkish;

    @Schema(description = "巴西葡萄牙语")
    @TableField("brazilian_portuguese")
    @ExcelProperty("巴西葡萄牙语")
    private String brazilianPortuguese;

    @Schema(description = "荷兰语")
    @TableField("dutch")
    @ExcelProperty("荷兰语")
    private String dutch;

    @Schema(description = "乌克兰语")
    @TableField("ukrainian")
    @ExcelProperty("乌克兰语")
    private String ukrainian;

    @Schema(description = "捷克语")
    @TableField("czech")
    @ExcelProperty("捷克语")
    private String czech;

    @Schema(description = "罗马尼亚语")
    @TableField("romanian")
    @ExcelProperty("罗马尼亚语")
    private String romanian;

    @Schema(description = "希腊语")
    @TableField("greek")
    @ExcelProperty("希腊语")
    private String greek;

    @Schema(description = "欧洲葡萄牙语")
    @TableField("european_portuguese")
    @ExcelProperty("欧洲葡萄牙语")
    private String europeanPortuguese;

    @Schema(description = "阿拉伯语")
    @TableField("arabic")
    @ExcelProperty("阿拉伯语")
    private String arabic;

    @Schema(description = "希伯来语")
    @TableField("hebrew")
    @ExcelProperty("希伯来语")
    private String hebrew;

    @Schema(description = "挪威语")
    @TableField("norwegian")
    @ExcelProperty("挪威语")
    private String norwegian;

    @Schema(description = "芬兰语")
    @TableField("finnish")
    @ExcelProperty("芬兰语")
    private String finnish;

    @Schema(description = "丹麦语")
    @TableField("danish")
    @ExcelProperty("丹麦语")
    private String danish;

    @Schema(description = "瑞典语")
    @TableField("swedish")
    @ExcelProperty("瑞典语")
    private String swedish;

    @Schema(description = "匈牙利语")
    @TableField("hungarian")
    @ExcelProperty("匈牙利语")
    private String hungarian;

    @Schema(description = "拉美西班牙语")
    @TableField("latin_spanish")
    @ExcelProperty("拉美西班牙语")
    private String latinSpanish;

    @Schema(description = "斯洛伐克语")
    @TableField("slovak")
    @ExcelProperty("斯洛伐克语")
    private String slovak;
}
