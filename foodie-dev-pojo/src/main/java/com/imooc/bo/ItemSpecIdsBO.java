package com.imooc.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ItemSpecIdsBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String priceDiscount;
    private String priceNormal;
    private Integer buyCounts;
}
