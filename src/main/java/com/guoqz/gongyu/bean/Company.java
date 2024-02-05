package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 对应数据库中的 tb_company表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Integer id;
    private String companyName;
    private String companyAddress;

    private List<Person> personList;

}
