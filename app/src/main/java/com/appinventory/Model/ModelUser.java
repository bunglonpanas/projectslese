package com.appinventory.Model;

public class ModelUser {
    private int id;
    private String name;
    private String address;
    private String telp;
    private String userName;
    private String password;
    private byte[] image;

    public ModelUser(int id, String name, String address, String telp, String userName, String password,  byte[] image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telp = telp;
        this.userName = userName;
        this.password = password;
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
