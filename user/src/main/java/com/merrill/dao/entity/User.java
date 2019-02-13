package com.merrill.dao.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-12
 * Time: 15:12
 * Description:
 */

@Data
public class User {

    private Long id;

    private String openID;

    private String name;

    private String phone;
}
