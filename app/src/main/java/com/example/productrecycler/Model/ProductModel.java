package com.example.productrecycler.Model;

public class ProductModel {

    private String userid,imageurl,gender,
            productcategory,producttype,sku_id,
            product_title,product_description,
            product_color,quantity,mrp,selling_price,serial_number;

    public ProductModel(String userid, String imageurl, String gender, String productcategory, String producttype, String sku_id, String product_title, String product_description, String product_color, String quantity, String mrp, String selling_price, String serial_number) {
        this.userid = userid;
        this.imageurl = imageurl;
        this.gender = gender;
        this.productcategory = productcategory;
        this.producttype = producttype;
        this.sku_id = sku_id;
        this.product_title = product_title;
        this.product_description = product_description;
        this.product_color = product_color;
        this.quantity = quantity;
        this.mrp = mrp;
        this.selling_price = selling_price;
        this.serial_number = serial_number;
    }

    public ProductModel() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }
}
