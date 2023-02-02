package com.tutorial.apidemo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NonNull
    @Column(name = "product_name")
    private String productName;
    @NonNull
    @Column(name = "price")
    private Double price;
    @NonNull
    @Column(name = "url")
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
