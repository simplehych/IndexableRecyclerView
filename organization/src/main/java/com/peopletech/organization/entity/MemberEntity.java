package com.peopletech.organization.entity;

import com.google.gson.annotations.SerializedName;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * 组织成员
 *
 * @author hych
 * @date 2019/2/20 14:15
 */
public class MemberEntity implements IndexableEntity {

    /**
     * 组织成员 id
     */
    @SerializedName("id")
    private int id;

    /**
     * 组织成员 名称
     */
    @SerializedName("user_name")
    private String userName;


    public MemberEntity() {
    }

    public MemberEntity(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getFieldIndexBy() {
        return userName;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.userName = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
