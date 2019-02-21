package com.peopletech.organization.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 组织机构
 *
 * @author hych
 * @date 2019/2/20 14:09
 */
public class OrganizationEntity {

    /**
     * 组织机构 id
     */
    @SerializedName("id")
    private int id;

    /**
     * 组织机构 名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 组织机构 简称
     */
    @SerializedName("short_name")
    private String shortName;

    /**
     * 组织机构 类型
     */
    @SerializedName("dept_type")
    private int deptType;

    /**
     * 组织机构 类型名称
     */
    @SerializedName("dept_type_name")
    private String deptTypeName;

    /**
     * 是否有子节点
     * <p>
     * 1：有
     */
    @SerializedName("has_child")
    private int hasChild;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getDeptType() {
        return deptType;
    }

    public void setDeptType(int deptType) {
        this.deptType = deptType;
    }

    public String getDeptTypeName() {
        return deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    public int getHasChild() {
        return hasChild;
    }

    public void setHasChild(int hasChild) {
        this.hasChild = hasChild;
    }
}
