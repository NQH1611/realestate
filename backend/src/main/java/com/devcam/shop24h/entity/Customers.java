package com.devcam.shop24h.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_title")
    private String contactTitle;
     @Column(name = "address")
    private String address;
     @Column(name = "mobile")
    private String mobile;
     @Column(name = "email")
    private String email;
     @Column(name = "note")
    private String note;
     @Column(name = "create_by")
    private Integer createBy;
     @Column(name = "update_by")
    private Integer updateBy;
     @Column(name = "create_date")
    private Date createDate;
     @Column(name = "update_date")
    private Date updateDate;
    public Customers() {
    }
    public Customers(int id, String contactName, String contactTitle, String address, String mobile, String email,
            String note, Integer createBy, Integer updateBy, Date createDate, Date updateDate) {
        this.id = id;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.note = note;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactTitle() {
        return contactTitle;
    }
    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Integer getCreateBy() {
        return createBy;
    }
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    public Integer getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
}
