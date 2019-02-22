package com.peopletech.organization.entity;

/**
 * 更新展示描述信息
 *
 * @author hych
 * @date 2019/2/19 16:12
 */
public class StringEntity {
    private String str;

    public StringEntity(String title) {
        this.str = title;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
