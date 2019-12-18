package com.appinventory.Model;

public class Model {
    private int id;
    private String name;
    private Integer safetyStock;
    private Integer optimumStock;
    private Integer currentStock;
    private String tags;
    private String notes;
    private String price;
    private byte[] image;

    public Model(int id, String name, Integer safetyStock, Integer optimumStock, Integer currentStock, String tags, String notes, String price, byte[] image) {
        this.id = id;
        this.name = name;
        this.safetyStock = safetyStock;
        this.optimumStock = optimumStock;
        this.currentStock = currentStock;
        this.tags = tags;
        this.notes = notes;
        this.price = price;
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

    public Integer getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(int safetyStock) {
        this.safetyStock = safetyStock;
    }

    public Integer getOptimumStock() {
        return optimumStock;
    }

    public void setOptimumStock(int optimumStock) {
        this.optimumStock = optimumStock;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}