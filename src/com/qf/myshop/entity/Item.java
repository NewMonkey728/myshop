package com.qf.myshop.entity;

import java.io.Serializable;

/**
 * 对应数据库订单项
 */
public class Item implements Serializable {

    private static  final long serialVersionUID = 1L;

    private int iid;
    private String oid;
    private int pid;
    private Product product;
    private double icount;
    private int inum;

    public Item(String oid, int pid, double icount, int inum) {
        this.oid = oid;
        this.pid = pid;
        this.icount = icount;
        this.inum = inum;
    }

    public Item(int iid, String oid, int pid, Product product, double icount, int inum) {
        this.iid = iid;
        this.oid = oid;
        this.pid = pid;
        this.product = product;
        this.icount = icount;
        this.inum = inum;
    }

    public Item() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getIcount() {
        return icount;
    }

    public void setIcount(double icount) {
        this.icount = icount;
    }

    public int getInum() {
        return inum;
    }

    public void setInum(int inum) {
        this.inum = inum;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", oid='" + oid + '\'' +
                ", pid=" + pid +
                ", icount=" + icount +
                ", inum=" + inum +
                '}';
    }
}
