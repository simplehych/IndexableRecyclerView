package com.peopletech.organization.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 组织成员信息
 *
 * @author hych
 * @date 2019/2/20 14:25
 */
public class MemberInfoEntity {

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

    /**
     * 头像
     */
    @SerializedName("avatar")
    private String avatar;

    /**
     * 性别
     */
    @SerializedName("sex")
    private String sex;

    /**
     * 民族
     */
    @SerializedName("ethnicity_name")
    private String ethnicityName;

    /**
     * 生日
     */
    @SerializedName("birthday")
    private String birthday;

    /**
     * 学历
     */
    @SerializedName("education_name")
    private String educationName;

    /**
     * 电话
     */
    @SerializedName("phone")
    private int phone;

    /**
     * 所在机构id
     */
    @SerializedName("org_id")
    private int orgId;

    /**
     * 所在机构名称
     */
    @SerializedName("org_name")
    private String orgName;

    /**
     * 党籍状态
     */
    @SerializedName("party_status")
    private String partyStatus;
    /**
     * 党员类型
     */
    @SerializedName("party_type")
    private String partyType;

    /**
     * 转正时间
     */
    @SerializedName("to_normal_date")
    private String toNormalDate;

    /**
     * 加入组织时间
     */
    @SerializedName("partyday")
    private String partyday;

    public String getEthnicityName() {
        return ethnicityName;
    }

    public void setEthnicityName(String ethnicityName) {
        this.ethnicityName = ethnicityName;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPartyStatus() {
        return partyStatus;
    }

    public void setPartyStatus(String partyStatus) {
        this.partyStatus = partyStatus;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getToNormalDate() {
        return toNormalDate;
    }

    public void setToNormalDate(String toNormalDate) {
        this.toNormalDate = toNormalDate;
    }

    public String getPartyday() {
        return partyday;
    }

    public void setPartyday(String partyday) {
        this.partyday = partyday;
    }
}
