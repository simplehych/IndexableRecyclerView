package com.peopletech.organization.entity;

/**
 * Created by YoKey on 16/10/8.
 */

public class MenuEntity {
    private long menuId;
    private String menuTitle;
    private int menuIconRes;
    private boolean isFirst = false;
    private boolean isLast = false;

    public MenuEntity(String title, int iconRes) {
        this.menuTitle = title;
        this.menuIconRes = iconRes;
    }

    public MenuEntity(long menuId, String menuTitle, int menuIconRes) {
        this.menuId = menuId;
        this.menuTitle = menuTitle;
        this.menuIconRes = menuIconRes;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuIconRes() {
        return menuIconRes;
    }

    public void setMenuIconRes(int menuIconRes) {
        this.menuIconRes = menuIconRes;
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
