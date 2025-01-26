package dz.protid.it.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private Integer id;
    private String marqueName;
    private String livreur;
    @OneToMany
    private List<Product> products;
    public Invoice() {}

    // Constructor to initialize all fields
    public Invoice(String marqueName, String livreur, List<Product> products) {
        this.marqueName = marqueName;
        this.livreur = livreur;
        this.products = products;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarqueName() {
        return marqueName;
    }

    public void setMarqueName(String marqueName) {
        this.marqueName = marqueName;
    }

    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
