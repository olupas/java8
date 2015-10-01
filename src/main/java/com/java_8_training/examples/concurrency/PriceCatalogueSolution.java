package com.java_8_training.examples.concurrency;

import com.java_8_training.answers.pricefinder.*;

import java.util.concurrent.CompletableFuture;

/**
 * .
 */
public class PriceCatalogueSolution
{
    private static final Catalogue catalogue = new Catalogue();
    private static final PriceFinder priceFinder = new PriceFinder();
    private static final ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args) throws InterruptedException
    {
        new PriceCatalogueSolution().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName) throws InterruptedException
    {
        long time = System.currentTimeMillis();

         lookupProductByName(productName)

        .thenCompose(this::findBestPrice)

        .thenCombine(lookupExchangeRate(localCurrency), this::exchange)

        .thenAccept(localAmount ->
        {
            System.out.printf("A %s will cost us %f %s\n", productName, localAmount, localCurrency);
            System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
        });

        Thread.sleep(2000);
    }

    private CompletableFuture<Price> findBestPrice(Product product)
    {
        return CompletableFuture.supplyAsync(() -> priceFinder.findBestPrice(product));
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

    private CompletableFuture<Product> lookupProductByName(String productName)
    {
        return CompletableFuture.supplyAsync(() -> catalogue.productByName(productName));
    }

    private CompletableFuture<Double> lookupExchangeRate(Currency localCurrency)
    {
        return CompletableFuture.supplyAsync(() ->
                exchangeService.lookupExchangeRate(Currency.USD, localCurrency));
    }

}
