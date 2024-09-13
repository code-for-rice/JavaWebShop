/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Dell
 */
public class Products {
//    SELECT TOP (1000) [productId]
//      ,[productName]
//      ,[productImage]
//      ,[brief]
//      ,[postedDate]
//      ,[typeId]
//      ,[account]
//      ,[unit]
//      ,[price]
//      ,[discount]
//  FROM [ProductIntro].[dbo].[products]
    
    private String productId, productName, productImage, brief;
    private Date postedDate;
    private Category typeId;
    private Accounts account;
    private String unit;
    private int price;
    private int discount;

    public Products() {
    }

    public Products(String productId, String productName, String productImage, String brief, Category typeId, Accounts account, String unit, int price, int discount) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.brief = brief;
        this.typeId = typeId;
        this.account = account;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
    }

    public Products(String productId, String productName, String brief, Date postedDate, Category typeId, Accounts account, String unit, int price, int discount) {
        this.productId = productId;
        this.productName = productName;
        this.brief = brief;
        this.postedDate = Date.valueOf(LocalDate.now());
        this.typeId = typeId;
        this.account = account;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Category getTypeId() {
        return typeId;
    }

    public void setTypeId(Category typeId) {
        this.typeId = typeId;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    
}
