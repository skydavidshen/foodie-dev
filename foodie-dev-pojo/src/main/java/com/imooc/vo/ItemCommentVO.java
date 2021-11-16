package com.imooc.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private String  specName;
    private Date createTime;
    private String userFace;
    private String  nickname;
}
