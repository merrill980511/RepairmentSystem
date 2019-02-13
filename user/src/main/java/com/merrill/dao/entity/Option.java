package com.merrill.dao.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-12
 * Time: 15:00
 * Description:
 */

@Data
public class Option {

    private Long id;

    private String name;

    private String content;

    private int location;

    private Long nextStepId;
}
