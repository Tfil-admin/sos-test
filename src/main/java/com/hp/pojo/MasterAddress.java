package com.hp.pojo;

public class MasterAddress {
    private Integer id;

    private String lng;

    private String lat;

    private Integer mid;

    private String status;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MasterAddress() {
        super();
    }

    public MasterAddress(Integer id, String lng, String lat, Integer mid, String status, String address) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.mid = mid;
        this.status = status;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}