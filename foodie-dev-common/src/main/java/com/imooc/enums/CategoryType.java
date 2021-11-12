package com.imooc.enums;

/**
 * @Desc: 分类级别
 */
public enum CategoryType {
    ONE(1, "一级分类"),
    TWO(2, "二级分类"),
    THREE(3, "三级分类");

    public final Integer type;
    public final String value;

    CategoryType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
