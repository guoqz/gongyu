package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 对应数据库中的tb_clean表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Clean {

    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cleanTime;
    private Integer personId;
    private Integer cleanLevel;
    private Integer flatsId;

    private Flats flats;
    private Person person;

}
