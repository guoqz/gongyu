package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 对应数据库中的 tb_fix表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Fix {

    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fixTime;
    private Integer flatsId;
    private Integer fixPeopleId;
    private Integer fixStatus;
    private String fixNote;

    private Flats flats;
    private Admin fixPeople;

}
