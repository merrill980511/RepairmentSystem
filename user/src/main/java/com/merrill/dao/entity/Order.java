package com.merrill.dao.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-12
 * Time: 15:15
 * Description:
 */

@Data
public class Order {

    private Long id;

    private User user;

    private Operator operatorID;

    private int location;

    private String phone;

    private Date beginTime;

    private Date endTime;

    private String description;

    private String repairment;

    private int status;
}
