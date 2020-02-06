/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.math.BigDecimal;

/**
 *
 * @author Aman
 */
public class BillDetail {
    private int billId;
    private int productId;
    private BigDecimal price;
    private int quantity;

    public BillDetail() {
    }

    public BillDetail(int productId, BigDecimal price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public BillDetail(int billId, int productId, BigDecimal price, int quantity) {
        this.billId = billId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getTotal() {
        BigDecimal quantity = new BigDecimal(getQuantity());
        return price.multiply(quantity);
    }
    
}

