package dz.protid.it.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String productCode;
    private String productName;
    private String category;
    private Integer quantity;
    public Product() {}

    // Constructor to initialize all fields
    public Product(String productCode, String productName, String category, Integer quantity, List<Order> orders) {
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.orders = orders;
    }
    @OneToMany
    private List<Order> orders;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
    }

    @OneToOne
    private Client client;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
