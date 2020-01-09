package com._520.crowdfunding.domain;

public class TAccountTypeCert extends TAccountTypeCertKey {
    private String accttype;

    private Integer certid;

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }
}