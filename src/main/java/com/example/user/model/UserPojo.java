package com.example.user.model;

public class UserPojo {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String mobileno;
    private Integer status=1;
    private String roleId;
    private RolePojo role;
    private BusinessPojo businessPojo;
    public BusinessPojo getBusinessPojo() {
        return businessPojo;
    }

    public void setBusinessPojo(BusinessPojo businessPojo) {
        this.businessPojo = businessPojo;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RolePojo getRole() {
        return role;
    }

    public void setRole(RolePojo role) {
       this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
