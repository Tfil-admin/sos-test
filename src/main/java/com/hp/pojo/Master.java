package com.hp.pojo;

public class Master {
    private Integer id;

    private String name;

    private String sex;

    private String age;

    private String account;

    private String password;

    private String phone;

    private String did;

    private String address;

    private MasterAddress masterAddress;

    public Master() {
        super();
    }

    public Master(Integer id, String name, String sex, String age, String account, String password, String phone, String did) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.did = did;
    }

    public Master(Integer id, String name, String sex, String age, String account, String password, String phone, String did, String address, MasterAddress masterAddress) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.did = did;
        this.address = address;
        this.masterAddress = masterAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MasterAddress getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(MasterAddress masterAddress) {
        this.masterAddress = masterAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }
}