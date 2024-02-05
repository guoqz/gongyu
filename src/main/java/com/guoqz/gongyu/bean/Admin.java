package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 对应数据库中的tb_admin表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer roleId;
    private String roleName;
    private String sex;
    private String phone;
    private String email;

}
