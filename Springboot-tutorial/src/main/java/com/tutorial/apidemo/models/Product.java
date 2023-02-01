package com.tutorial.apidemo.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    @Column(name = "ProductName")
    private String productName;
    @NonNull
    @Column(name = "Price")
    private Double price;
    @NonNull
    @Column(name = "Url")
    private String url;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

}
