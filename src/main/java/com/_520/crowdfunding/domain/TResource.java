package com._520.crowdfunding.domain;

public class TResource extends TResourceKey {
    private String url;

    private String createat;

    private String updateat;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String getUpdateat() {
        return updateat;
    }

    public void setUpdateat(String updateat) {
        this.updateat = updateat;
    }
}