package com.guoqz.gongyu.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 对应数据库中的 tb_person 表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String sex;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer companyId;
    private Integer flatsId;

    private Company company;
    private Flats flats;

}
