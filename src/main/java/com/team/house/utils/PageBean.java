package com.team.house.utils;

public class PageBean {
    private Integer page=1;  //页码
    private Integer rows=3; //页大小


    //查询条件 用户表
    private String name;  //名称
    private String telephone;  //电话
    private Integer startAge; //开始年龄
    private Integer endAge;  //结束年龄



    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", rows=" + rows +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", startAge=" + startAge +
                ", endAge=" + endAge +
                '}';
    }

    public PageBean() {

    }

    public PageBean(Integer page, Integer rows, String name, String telephone, Integer startAge, Integer endAge) {
        this.page = page;
        this.rows = rows;
        this.name = name;
        this.telephone = telephone;
        this.startAge = startAge;
        this.endAge = endAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
