package com.zhangjun.excel.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelIgnore;


/**
 * <p>
 * 配网引导
 * </p>
 *
 * @author zhangjun
 * @since 2025-06-07
 */
@Data
@TableName("network_configuration_guide")
@Schema(name = "NetworkConfigurationGuide", description = "配网引导")
public class NetworkConfigurationGuide implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    @Schema(description = "产品Model")
    @TableField("product_model")
    @ExcelProperty("产品Model")
    private String productModel;

    @Schema(description = "配置名称")
    @TableField("configuration_name")
    @ExcelProperty("配置名称")
    private String configurationName;

    @Schema(description = "简体中文")
    @TableField("simplified_chinese")
    @ExcelProperty("简体中文")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String simplifiedChinese;

    @Schema(description = "英语")
    @TableField("english")
    @ExcelProperty("英语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String english;

    @Schema(description = "台湾正体")
    @TableField("taiwan_traditional")
    @ExcelProperty("台湾正体")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String taiwanTraditional;

    @Schema(description = "港澳繁体")
    @TableField("hongkong_traditional")
    @ExcelProperty("港澳繁体")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String hongkongTraditional;

    @Schema(description = "韩语")
    @TableField("korean")
    @ExcelProperty("韩语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String korean;

    @Schema(description = "俄语")
    @TableField("russian")
    @ExcelProperty("俄语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String russian;

    @Schema(description = "西班牙语")
    @TableField("spanish")
    @ExcelProperty("西班牙语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String spanish;

    @Schema(description = "意大利语")
    @TableField("italian")
    @ExcelProperty("意大利语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String italian;

    @Schema(description = "法语")
    @TableField("french")
    @ExcelProperty("法语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String french;

    @Schema(description = "印度尼西亚语")
    @TableField("indonesian")
    @ExcelProperty("印度尼西亚语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String indonesian;

    @Schema(description = "德语")
    @TableField("german")
    @ExcelProperty("德语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String german;

    @Schema(description = "波兰语")
    @TableField("polish")
    @ExcelProperty("波兰语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String polish;

    @Schema(description = "越南语")
    @TableField("vietnamese")
    @ExcelProperty("越南语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String vietnamese;

    @Schema(description = "日语")
    @TableField("japanese")
    @ExcelProperty("日语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String japanese;

    @Schema(description = "泰语")
    @TableField("thai")
    @ExcelProperty("泰语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String thai;

    @Schema(description = "土耳其语")
    @TableField("turkish")
    @ExcelProperty("土耳其语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String turkish;

    @Schema(description = "巴西葡萄牙语")
    @TableField("brazilian_portuguese")
    @ExcelProperty("巴西葡萄牙语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String brazilianPortuguese;

    @Schema(description = "荷兰语")
    @TableField("dutch")
    @ExcelProperty("荷兰语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String dutch;

    @Schema(description = "乌克兰语")
    @TableField("ukrainian")
    @ExcelProperty("乌克兰语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String ukrainian;

    @Schema(description = "捷克语")
    @TableField("czech")
    @ExcelProperty("捷克语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String czech;

    @Schema(description = "罗马尼亚语")
    @TableField("romanian")
    @ExcelProperty("罗马尼亚语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String romanian;

    @Schema(description = "希腊语")
    @TableField("greek")
    @ExcelProperty("希腊语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String greek;

    @Schema(description = "欧洲葡萄牙语")
    @TableField("european_portuguese")
    @ExcelProperty("欧洲葡萄牙语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String europeanPortuguese;

    @Schema(description = "阿拉伯语")
    @TableField("arabic")
    @ExcelProperty("阿拉伯语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String arabic;

    @Schema(description = "希伯来语")
    @TableField("hebrew")
    @ExcelProperty("希伯来语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String hebrew;

    @Schema(description = "挪威语")
    @TableField("norwegian")
    @ExcelProperty("挪威语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String norwegian;

    @Schema(description = "芬兰语")
    @TableField("finnish")
    @ExcelProperty("芬兰语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String finnish;

    @Schema(description = "丹麦语")
    @TableField("danish")
    @ExcelProperty("丹麦语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String danish;

    @Schema(description = "瑞典语")
    @TableField("swedish")
    @ExcelProperty("瑞典语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String swedish;

    @Schema(description = "匈牙利语")
    @TableField("hungarian")
    @ExcelProperty("匈牙利语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String hungarian;

    @Schema(description = "拉美西班牙语")
    @TableField("latin_spanish")
    @ExcelProperty("拉美西班牙语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String latinSpanish;

    @Schema(description = "斯洛伐克语")
    @TableField("slovak")
    @ExcelProperty("斯洛伐克语")
    @Getter(onMethod_ = @Size(max = 800))
    @Column(length = 800)
    private String slovak;
}
