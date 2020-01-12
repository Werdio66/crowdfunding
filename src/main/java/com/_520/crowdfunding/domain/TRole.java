package com._520.crowdfunding.domain;

public class TRole extends TRoleKey {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TRole{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}