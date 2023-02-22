package com.devcam.shop24h.entity;

import javax.persistence.*;

@Entity
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_name")
    private String name;

    @Column(name = "_prefix")
    private String prefix;

    @Column(name = "_province_id")
    private int province;

    @Column(name = "_district_id")
    private int district;

    public Ward() {
    }

    public Ward(Long id, String name, String prefix, int province, int district) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.province = province;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }
    
}
