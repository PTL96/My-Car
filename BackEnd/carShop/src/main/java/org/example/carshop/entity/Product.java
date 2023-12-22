package org.example.carshop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    @Column(columnDefinition = "longtext")
    private String description;
    private Double price;
    @Column(columnDefinition = "longtext")
    private String avatar;
    private boolean flagDelete;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
