package com.java_8_training.answers.pricefinder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Catalogue
{
    private final Product harrypotter = new Product(Product.Category.BOOK, "Harry Potter");
    private final Product davincicode = new Product(Product.Category.BOOK, "Da Vinci Code");
    private final Product iphone5 = new Product(Product.Category.ELECTRONICS, "Iphone5");
    private final Product nexus7 = new Product(Product.Category.ELECTRONICS, "Nexus7");
    private final Product toothbrush = new Product(Product.Category.HEALTH, "Tooth brush");
    private final Product perfume = new Product(Product.Category.HEALTH, "Perfume");

    private final List<Product> products = Arrays.asList(
            harrypotter, davincicode, iphone5,
            nexus7, perfume, toothbrush
    );

    private final Map<String, Product> productsByName = products
            .stream().collect(toMap(Product::getName, p -> p));

    public Product productByName(final String name)
    {
        return productsByName.get(name);
    }

}
