package com.imooc.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private Integer subType;
    private Integer subFatherId;
}
