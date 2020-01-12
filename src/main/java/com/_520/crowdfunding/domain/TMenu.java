package com._520.crowdfunding.domain;

import java.util.ArrayList;
import java.util.List;

public class TMenu extends TMenuKey {
    private Integer pid;

    private String name;

    private String icon;

    private String url;

    private List<TMenu> children = new ArrayList<>();

    public List<TMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TMenu{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}