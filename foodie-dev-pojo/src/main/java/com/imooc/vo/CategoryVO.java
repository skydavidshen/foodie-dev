package com.imooc.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryVO {
    private Integer id;
    private String name;
    private Integer type;
    private Integer fatherId;

    private List<SubCategoryVO> subCatList;
}
