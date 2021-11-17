package com.imooc.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SearchItemVO {
    private String itemName;
    private String itemId;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;
}
