package com.imooc.bo.center;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CenterUserBo {
    @NotBlank(message = "昵称不能为空")
    @Length(max = 12, message = "昵称不能超过12位")
    private String nickname;

    @Length(max = 12, message = "姓名不能超过12位")
    private String realname;

    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "手机号格式不正确")
    private String mobile;

    @Email
    private String email;

    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 2, message = "性别选择不正确")
    private Integer sex;

    private Date birthday;
}
