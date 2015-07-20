package com.framgia.lupx.androidtraining.models;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class NavDrawerItem {
    public int iconResource;
    public String name;

    public NavDrawerItem() {
    }

    public NavDrawerItem(int res, String name) {
        this.iconResource = res;
        this.name = name;
    }
}
