package com.java_8_training.answers.pricefinder;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static com.java_8_training.answers.pricefinder.Product.Category;
import static java.util.stream.Collectors.*;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/*
TODO:
  - refactoring productPriceComparator to use Java 8 features/library
  - refactor the method in getCategoryToProductsSortedByPrice to use Java 8 features
  - refactor the method getMaxProductPrice to use Java 8 features
  - reduce processing time
 */
public class PriceFinderTest {

    public static final Comparator<Pair<Product, Price>> productPriceComparator = new Comparator<Pair<Product, Price>>() {
        @Override
        public int compare(Pair<Product, Price> p1, Pair<Product, Price> p2) {
            return Double.compare(p2.getSecond().getAmount(), p1.getSecond().getAmount());
        }
    };

    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService ex = new ExchangeService();

    private final Product harrypotter = new Product(Category.BOOK, "Harry Potter");
    private final Product davincicode = new Product(Category.BOOK, "Da Vinci Code");
    private final Product iphone5 = new Product(Category.ELECTRONICS, "Iphone5");
    private final Product nexus7 = new Product(Category.ELECTRONICS, "Nexus7");
    private final Product toothbrush = new Product(Category.HEALTH, "Tooth brush");
    private final Product perfume = new Product(Category.HEALTH, "Perfume");

    private final List<Product> products =
            Arrays.asList(harrypotter, davincicode, iphone5,
                    nexus7, perfume, toothbrush
            );

    private Executor executor = Executors.newFixedThreadPool(products.size(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            }
    );

    @Test
    public void testCategoryToProductsSortedByPrice() {

        long start = System.nanoTime();

        Map<Category, SortedSet<Pair<Product, Price>>> categoryToProductsSortedByPrice
                = getCategoryToProductsSortedByPrice();

        Map<Category, Set<Pair<Product, Price>>> result = getResult();
        System.out.println("Collected all prices in in " + (System.nanoTime() - start) / 1_000_000 + " msecs");


        assertThat(categoryToProductsSortedByPrice.entrySet(), equalTo(result.entrySet()));
    }


    private Map<Category, SortedSet<Pair<Product, Price>>> getCategoryToProductsSortedByPrice() {

        Map<Category, SortedSet<Pair<Product, Price>>> categoryToProductsSortedByPrice = new HashMap<>();
        for (Product product : products) {
            Price price = priceFinder.findBestPrice(product);

            System.out.println(product.getName() + " is priced in " + price.getCurrency() + " at " + price.getAmount());

            double rate = 0;
            double priceInGPB = price.getAmount();
            if (price.getCurrency() != Currency.GBP) {
                priceInGPB = ex.exchangeCurrency(price.getCurrency(), Currency.GBP, price.getAmount());
            }
            System.out.println(product.getName() + " is priced in GBP at " + priceInGPB);

            SortedSet<Pair<Product, Price>> productsForCategory = categoryToProductsSortedByPrice.get(product.getCategory());
            if (productsForCategory == null) {
                productsForCategory = new TreeSet<>(productPriceComparator);

                categoryToProductsSortedByPrice.put(product.getCategory(), productsForCategory);
            }

            productsForCategory.add(new Pair<>(product, new Price(Currency.GBP, priceInGPB)));

        }

        return categoryToProductsSortedByPrice;
    }

    @Test
    public void testCategoryToProductsSortedByPriceWithJava8() {
        long start = System.nanoTime();

        Map<Category, SortedSet<Pair<Product, Price>>> categoryToProductsSortedByPrice
                = getCategoryToProductsSortedByPriceWithJava8();

        Map<Category, Set<Pair<Product, Price>>> result = getResult();
        System.out.println("Collected all prices in in " + (System.nanoTime() - start) / 1_000_000 + " msecs");

        assertThat(categoryToProductsSortedByPrice.entrySet(), equalTo(result.entrySet()));
    }

    private Map<Category, SortedSet<Pair<Product, Price>>> getCategoryToProductsSortedByPriceWithJava8() {
        List<CompletableFuture<Pair<Product, Price>>> l = products.stream()
                .map(product -> CompletableFuture.supplyAsync(
                        () -> new Pair<Product, Price>(product, priceFinder.findBestPrice(product)),
                        executor))
                .map(future -> future.thenApply((Pair<Product, Price> pair) -> {
                    double priceInGPB = ex.exchangeCurrency(pair.getSecond().getCurrency(),
                            Currency.GBP, pair.getSecond().getAmount());
                    return new Pair<Product, Price>(pair.getFirst(), new Price(Currency.GBP, priceInGPB));
                }))
                .collect(toList());

        return l.stream().map(CompletableFuture::join)
                .collect(groupingBy(pair -> pair.getFirst().getCategory(), toCollection(
                        () -> new TreeSet<Pair<Product, Price>>(productPriceComparator)
                )));
    }

    @Test
    public void testMaxPrice() {
        Map<Category, SortedSet<Pair<Product, Price>>> categoryToProductsSortedByPrice
                = getCategoryToProductsSortedByPrice();

        double max = getMaxPrice();

        assertEquals(777.0, max);
    }

    private double getMaxPrice() {
        Map<Category, SortedSet<Pair<Product, Price>>> categoryToProductsSortedByPrice
                = getCategoryToProductsSortedByPrice();

        double max = 0;
        for (Map.Entry<Category, SortedSet<Pair<Product, Price>>> entry : categoryToProductsSortedByPrice.entrySet()) {
            for (Pair<Product, Price> pair : entry.getValue()) {
                double price = pair.getSecond().getAmount();
                max = max > price ? max : price;
            }
        }
        return max;
    }

    @Test
    public void testMaxPriceWithJava8() {
        double max = getMaxPriceWithJava8();

        assertEquals(777.0, max);
    }

    private double getMaxPriceWithJava8() {
        Map<Category, SortedSet<Pair<Product, Price>>> categoryToProductsSortedByPrice
                = getCategoryToProductsSortedByPriceWithJava8();

        return categoryToProductsSortedByPrice.values()
                .stream()
                .map(SortedSet::first)
                .mapToDouble(p -> p.getSecond().getAmount())
                .max().getAsDouble();
    }

    private Map<Category, Set<Pair<Product, Price>>> getResult() {
        Map<Category, Set<Pair<Product, Price>>> result = new HashMap<>();
        TreeSet<Pair<Product, Price>> books = new TreeSet<>(productPriceComparator);
        books.add(new Pair(harrypotter, new Price(Currency.GBP, 72.95)));
        books.add(new Pair(davincicode, new Price(Currency.GBP, 777.0)));
        result.put(Category.BOOK, books);
        TreeSet<Pair<Product, Price>> electronics = new TreeSet<>(productPriceComparator);
        electronics.add(new Pair(iphone5, new Price(Currency.GBP, 75.18)));
        electronics.add(new Pair(nexus7, new Price(Currency.GBP, 524.4)));
        result.put(Category.ELECTRONICS, electronics);
        TreeSet<Pair<Product, Price>> health = new TreeSet<>(productPriceComparator);
        health.add(new Pair(toothbrush, new Price(Currency.GBP, 764.2)));
        health.add(new Pair(perfume, new Price(Currency.GBP, 80.45)));
        result.put(Category.HEALTH, health);
        return result;
    }

}
