package com.peopletech.organization.entity;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * @author hych
 * @date 2019/2/22 12:51
 */
public class InfoItemEntity implements IndexableEntity {

    private String order;
    private String des;
    private String content;

    public InfoItemEntity(String group, String des, String info) {
        this.order = group;
        this.des = des;
        this.content = info;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getFieldIndexBy() {
        return order;
    }

    @Override
    public void setFieldIndexBy(String index) {
        this.order = index;
    }

    @Override
    public void setFieldPinyinIndexBy(String s) {

    }
}
