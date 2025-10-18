package com.example.shoppingcart.shoppinglist;


import com.example.shoppingcart.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "SHOPPING_LIST")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name="LIST_NAME")
    private String listName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "SHOPPINGLIST_CATEGORY",
            joinColumns = @JoinColumn(name = "SHOPPINGLIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categoryList;
}
