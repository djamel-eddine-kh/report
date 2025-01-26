package dz.protid.it.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer clientRef;

    public Client() {}

    // Constructor to initialize all fields
    public Client(String name, Integer clientRef) {
        this.name = name;
        this.clientRef = clientRef;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClientRef() {
        return clientRef;
    }

    public void setClientRef(Integer clientRef) {
        this.clientRef = clientRef;
    }
}
