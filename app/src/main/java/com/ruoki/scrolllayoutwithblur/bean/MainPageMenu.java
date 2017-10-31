package com.ruoki.scrolllayoutwithblur.bean;

/**
 * Created by 01 on 2017/3/9.
 */
public class MainPageMenu {
    public int id;
    public int name;
    public int icon;

    public MainPageMenu(int id, int name, int icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
}
