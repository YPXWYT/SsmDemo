package com.tna.ssmdemo.entity;

import javax.validation.constraints.NotBlank;

public class SearchCriteria {
    @NotBlank(message = "用户名不能为空")
    private String name	;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
