package com.qf.myshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 对应数据库的商品表
 */
public class Product implements Serializable {

    private static  final long serialVersionUID = 1L;

    private int pid;
    private int tid;
    private String pname;
    private Type type;
    private Date  ptime; //商品的上架时间！ 数据库date --> java.util.Date
    private String pimage; //商品的图片名称
    private int pstate; //商品的热门指数
    private String pinfo; //商品的描述
    private double pprice; //价格

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Product() {
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public int getPstate() {
        return pstate;
    }

    public void setPstate(int pstate) {
        this.pstate = pstate;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public Product(int pid, int tid, String pname, Type type, Date ptime, String pimage, int pstate, String pinfo, double pprice) {
        this.pid = pid;
        this.tid = tid;
        this.pname = pname;
        this.type = type;
        this.ptime = ptime;
        this.pimage = pimage;
        this.pstate = pstate;
        this.pinfo = pinfo;
        this.pprice = pprice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", pname='" + pname + '\'' +
                ", type=" + type +
                ", ptime=" + ptime +
                ", pimage='" + pimage + '\'' +
                ", pstate=" + pstate +
                ", pinfo='" + pinfo + '\'' +
                ", pprice=" + pprice +
                '}';
    }
}
