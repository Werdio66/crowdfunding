package com._520.crowdfunding.domain;

public class TMemberProjectFollow extends TMemberProjectFollowKey {
    private Integer projectid;

    private Integer memberid;

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }
}