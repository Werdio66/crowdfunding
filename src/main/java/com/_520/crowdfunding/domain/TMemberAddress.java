package com._520.crowdfunding.domain;

public class TMemberAddress extends TMemberAddressKey {
    private Integer memberid;

    private String address;

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}