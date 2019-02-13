package com.merrill.dao.entity;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-12
 * Time: 15:00
 * Description:
 */

@Data
public class Step {

    private Long id;

    private String name;

    private String content;

    private Link link;

    private List<Option> options;
}
