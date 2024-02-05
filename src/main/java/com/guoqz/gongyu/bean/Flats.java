package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 对应数据库中的 tb_flats表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Flats {

    private Integer id;
    private String flatsName;
    private String flatsPhoto;

    private List<Person> personList;

}
