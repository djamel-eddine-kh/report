package dz.protid.it.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer blNumber;
    private Integer quantitySold;
    @ManyToOne
    private Client client;

    public Order() {}

    // Constructor to initialize all fields
    public Order(Integer blNumber, Integer quantitySold, Client client) {
        this.blNumber = blNumber;
        this.quantitySold = quantitySold;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlNumber() {
        return blNumber;
    }

    public void setBlNumber(Integer blNumber) {
        this.blNumber = blNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
