package com.devcam.shop24h.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "title")
    private String title;
    @Column(name = "title_of_courtesy")
    private String titleOfCourtesy;
    @Column(name = "birthdate")
    private Date birthDay;
    @Column(name = "hiredate")
    private Date hireDate;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "region")
    private String region;
    @Column(name = "postalcode")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "homephone")
    private String homePhone;
    @Column(name = "extension")
    private String extention;
    @Column(name = "photo")
    private String photo;
    @Column(name = "notes")
    private String notes;
    @Column(name = "reportsto")
    private Integer reportsTo;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "activated")
    private String activated;
    @Column(name = "profile")
    private String profile;
    @Column(name = "userlevel")
    private Integer userLevel;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "fk_employees_role", joinColumns = {@JoinColumn(name = "employees_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employees")
    private Set<Post> posts;
    public Employees() {
    }
    public Employees(Long id, String lastName, String firstName, String title, String titleOfCourtesy, Date birthDay,
            Date hireDate, String address, String city, String region, String postalCode, String country,
            String homePhone, String extention, String photo, String notes, Integer reportsTo, String userName,
            String password, String email, String activated, String profile, Integer userLevel) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDay = birthDay;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extention = extention;
        this.photo = photo;
        this.notes = notes;
        this.reportsTo = reportsTo;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.activated = activated;
        this.profile = profile;
        this.userLevel = userLevel;
    }
    /**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }
    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public String getExtention() {
        return extention;
    }
    public void setExtention(String extention) {
        this.extention = extention;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Integer getReportsTo() {
        return reportsTo;
    }
    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getActivated() {
        return activated;
    }
    public void setActivated(String activated) {
        this.activated = activated;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public Integer getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
    
    
}
