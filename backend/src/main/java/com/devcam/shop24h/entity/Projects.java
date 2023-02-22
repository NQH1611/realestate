package com.devcam.shop24h.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "_name")
    private String name;
    @Column(name = "_province_id")
    private Integer provinceId;
    @Column(name = "_district_id")
    private Integer districtId;
    @Column(name = "_ward_id")
    private Integer wardId;
    @Column(name = "_street_id")
    private Integer streetId;
    @Column(name = "address")
    private String address;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "description")
    private String description;
    @Column(name = "acreage")
    private BigDecimal acreage;
    @Column(name = "construct_area")
    private BigDecimal constructArea;
    @Column(name = "num_block")
    private Integer numBlock;
    @Column(name = "num_floors")
    private String numFloors;
    @Column(name = "num_apartment")
    private int numApartment;
    @Column(name = "apartmentt_area")
    private String apartmenttArea;
    @Column(name = "investor")
    private int investor;
    @Column(name = "construction_contractor")
    private Integer constructContractor;
    @Column(name = "design_unit")
    private Integer designUnit;
    @Column(name = "utilities")
    private String utilities;
    @Column(name = "region_link")
    private String regionLink;
    @Column(name = "photo")
    private String photo;
    @Column(name = "_lat")
    private Double lat;
    @Column(name = "_lng")
    private Double lng;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private int status;
    @Column(name = "customer")
    private int customer;
    public Projects() {
    }
    
    public Projects(int id, String name, Integer provinceId, Integer districtId, Integer wardId, Integer streetId,
            String address, String slogan, String description, BigDecimal acreage, BigDecimal constructArea,
            Integer numBlock, String numFloors, int numApartment, String apartmenttArea, int investor,
            Integer constructContractor, Integer designUnit, String utilities, String regionLink, String photo,
            Double lat, Double lng, double price, int status, int customer) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.streetId = streetId;
        this.address = address;
        this.slogan = slogan;
        this.description = description;
        this.acreage = acreage;
        this.constructArea = constructArea;
        this.numBlock = numBlock;
        this.numFloors = numFloors;
        this.numApartment = numApartment;
        this.apartmenttArea = apartmenttArea;
        this.investor = investor;
        this.constructContractor = constructContractor;
        this.designUnit = designUnit;
        this.utilities = utilities;
        this.regionLink = regionLink;
        this.photo = photo;
        this.lat = lat;
        this.lng = lng;
        this.price = price;
        this.status = status;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public Integer getDistrictId() {
        return districtId;
    }
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
    public Integer getWardId() {
        return wardId;
    }
    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }
    public Integer getStreetId() {
        return streetId;
    }
    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSlogan() {
        return slogan;
    }
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getAcreage() {
        return acreage;
    }
    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }
    public BigDecimal getConstructArea() {
        return constructArea;
    }
    public void setConstructArea(BigDecimal constructArea) {
        this.constructArea = constructArea;
    }
    public Integer getNumBlock() {
        return numBlock;
    }
    public void setNumBlock(Integer numBlock) {
        this.numBlock = numBlock;
    }
    public String getNumFloors() {
        return numFloors;
    }
    public void setNumFloors(String numFloors) {
        this.numFloors = numFloors;
    }
    public int getNumApartment() {
        return numApartment;
    }
    public void setNumApartment(int numApartment) {
        this.numApartment = numApartment;
    }
    public String getApartmenttArea() {
        return apartmenttArea;
    }
    public void setApartmenttArea(String apartmenttArea) {
        this.apartmenttArea = apartmenttArea;
    }
    public int getInvestor() {
        return investor;
    }
    public void setInvestor(int investor) {
        this.investor = investor;
    }
    public Integer getConstructContractor() {
        return constructContractor;
    }
    public void setConstructContractor(Integer constructContractor) {
        this.constructContractor = constructContractor;
    }
    public Integer getDesignUnit() {
        return designUnit;
    }
    public void setDesignUnit(Integer designUnit) {
        this.designUnit = designUnit;
    }
    public String getUtilities() {
        return utilities;
    }
    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }
    public String getRegionLink() {
        return regionLink;
    }
    public void setRegionLink(String regionLink) {
        this.regionLink = regionLink;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    public Double getLng() {
        return lng;
    }
    public void setLng(Double lng) {
        this.lng = lng;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }
    
}
