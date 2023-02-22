package com.devcam.shop24h.entity;

import javax.persistence.*;

@Entity
@Table(name ="province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_name")
    private String name;

    @Column(name = "_code")
    private String code;

    public Long getId() {
        return id;
    }

    public Province() {
    }

    public Province(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
