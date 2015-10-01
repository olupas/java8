package com.java_8_training.examples.concurrency;

import com.java_8_training.answers.pricefinder.*;

import static com.java_8_training.answers.pricefinder.Currency.USD;

public class PriceCatalogueExample
{
    private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args)
    {
        new PriceCatalogueExample().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    // Explain Example Code
    // Explain Blocking
    // Explain futures - I-owe-you
    // Refactor Code & Show Diagrams of each operation

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName)
    {
        long time = System.currentTimeMillis();

        Product product = catalogue.productByName(productName);

        Price price = priceFinder.findBestPrice(product);

        double exchangeRate = exchangeService.lookupExchangeRate(USD, localCurrency);

        double localPrice = exchange(price, exchangeRate);

        System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

}
