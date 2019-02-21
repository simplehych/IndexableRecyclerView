package com.peopletech.organization.entity;

/**
 * @author hych
 * @date 2019/2/20 14:45
 */

public class HeaderMenuEntity {
    /**
     * id
     */
    private long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private int iconRes;

    /**
     * 是否该组第一个
     */
    private boolean isFirst = false;

    /**
     * 是否该组最后一个
     */
    private boolean isLast = false;

    public HeaderMenuEntity(String title, int iconRes) {
        this.title = title;
        this.iconRes = iconRes;
    }

    public HeaderMenuEntity(long menuId, String menuTitle, int menuIconRes) {
        this.id = menuId;
        this.title = menuTitle;
        this.iconRes = menuIconRes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
