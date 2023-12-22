package org.example.carshop.dto;


import org.example.carshop.entity.Product;
import org.example.carshop.entity.accout.Account;

public interface ProductView {
    Long getProductId();
    String getProductName();
    String getCategoryName();
    String getDescription();
    Double getPrice();
    String getAvatar();
    Double getQuantity();
    Account getAccount();
    Product getProduct();
}
