package dz.protid.it.service;

import dz.protid.it.domain.Invoice;
import dz.protid.it.domain.Client;
import dz.protid.it.domain.Order;
import dz.protid.it.domain.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private Invoice invoice;


    //generate a fake Article data to be returned

    public Invoice getInvoice(){
        return this.invoice;
    }
    @PostConstruct
    private void setArticle(){
        Client client1 = new Client("SOFYAN LAREBAA", 123);
        Client client2 = new Client("SOFYAN 2", 124);
        Client client3 = new Client("AHMED RAFIGO", 125);

        List<Order> orders = List.of(
                new Order(7598, 20, client1),
                new Order(7599, 20, client2),
                new Order(7597, 50, client3)
        );

        List<Product> products = List.of(
                new Product("026", "MARGARINE LABELLE", "MARGARINE LABELLE 250g", 100, orders.subList(0, 2)),
                new Product("027", "MARGARINE LABELLE", "MARGARINE LABELLE 500g", 200, orders.subList(2, 3))
        );

        this.invoice = new Invoice("Labelle", "Mohamed Master", products);

    }

}
