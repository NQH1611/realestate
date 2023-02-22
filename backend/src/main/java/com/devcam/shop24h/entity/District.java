package com.devcam.shop24h.entity;

import javax.persistence.*;

@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_name", length = 100)
    private String name;

    @Column(name = "_prefix", length = 20)
    private String prefix;

    @Column(name = "_province_id")
    private int province;

    
    public District() {
    }

    
    public District(Long id, String name, String prefix, int province) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.province = province;
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

    public int getProvice() {
        return province;
    }

    public void setProvice(int province) {
        this.province = province;
    }
    
}
