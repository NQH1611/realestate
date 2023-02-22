package com.devcam.shop24h.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tenduan")
    private String tenDuAn;
    @Column(name = "diachi")
    private String diaChi;
    @Column(name = "mucgia")
    private BigDecimal mucGia;
    @Column(name = "dientich")
    private int dienTich;
    @Column(name = "sophong")
    private int soPhong;
    @Column(name = "mota")
    private String moTa;
    @Column(name = "loaibatdongsan")
    private String loaiBatDongSan;
    @ManyToOne
    @JoinColumn(name = "khachhang")
    private Employees employees;
    public Post() {
    }
    public Post(int id, String tenDuAn, String diaChi, BigDecimal mucGia, int dienTich, int soPhong, String moTa,
            String loaiBatDongSan, Employees employees) {
        this.id = id;
        this.tenDuAn = tenDuAn;
        this.diaChi = diaChi;
        this.mucGia = mucGia;
        this.dienTich = dienTich;
        this.soPhong = soPhong;
        this.moTa = moTa;
        this.loaiBatDongSan = loaiBatDongSan;
        this.employees = employees;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTenDuAn() {
        return tenDuAn;
    }
    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public BigDecimal getMucGia() {
        return mucGia;
    }
    public void setMucGia(BigDecimal mucGia) {
        this.mucGia = mucGia;
    }
    public int getDienTich() {
        return dienTich;
    }
    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }
    public int getSoPhong() {
        return soPhong;
    }
    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public String getLoaiBatDongSan() {
        return loaiBatDongSan;
    }
    public void setLoaiBatDongSan(String loaiBatDongSan) {
        this.loaiBatDongSan = loaiBatDongSan;
    }
    public Employees getEmployees() {
        return employees;
    }
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    
}
