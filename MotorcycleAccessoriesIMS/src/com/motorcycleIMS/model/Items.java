/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorcycleIMS.model;

/**
 *
 * @author USER
 */
public class Items {
    private int itemCode;
    private String name;
    private int quantity;
    private double mPrice;
    private String supplier;
    private String bikeModel;
    
    public Items(int itemCode, String name, int quantity, double mPrice,String supplier,String bikeModel) {
        this.itemCode = itemCode;
        this.name = name;
        this.quantity = quantity;
        this.mPrice = mPrice;
        this.supplier = supplier;
        this.bikeModel = bikeModel;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }
}

