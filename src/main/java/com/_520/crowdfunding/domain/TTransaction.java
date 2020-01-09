package com._520.crowdfunding.domain;

public class TTransaction extends TTransactionKey {
    private String ordersn;

    private String paysn;

    private Integer memberid;

    private Float amount;

    private Byte paystate;

    private String source;

    private Byte status;

    private String completiontime;

    private String note;

    private String createat;

    private String updateat;

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    public String getPaysn() {
        return paysn;
    }

    public void setPaysn(String paysn) {
        this.paysn = paysn;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Byte getPaystate() {
        return paystate;
    }

    public void setPaystate(Byte paystate) {
        this.paystate = paystate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCompletiontime() {
        return completiontime;
    }

    public void setCompletiontime(String completiontime) {
        this.completiontime = completiontime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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