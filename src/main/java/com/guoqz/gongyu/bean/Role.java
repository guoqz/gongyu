package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 对应数据库中的tb_role表
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Integer id;
    private String roleName;
    private String roleRemark;

}
