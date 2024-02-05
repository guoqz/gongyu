package com.guoqz.gongyu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 对应数据库中的tb_menu表
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer id;
    private String name;
    private String icon;
    private String href;
    private String spread;
    private Integer parentId;
    private Integer sorting;

    // 存放子菜单的list集合
    private List<Menu> children;
}
