package com.java_8_training.answers.streams;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionsAndTradesPart1Test {

    private List<Transaction> transactions = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void findSortedTransactionsFrom2011() {
        List<Transaction> transactionsYear2011 = transactions.stream()
                .filter(trans -> trans.getYear() == 2011)
                .sorted(comparing(trans -> trans.getValue()))
                .collect(toList());

        assertEquals(300, transactionsYear2011.get(0).getValue());
        assertEquals(2011, transactionsYear2011.get(0).getYear());

        assertEquals(400, transactionsYear2011.get(1).getValue());
        assertEquals(2011, transactionsYear2011.get(1).getYear());

        assertEquals(2, transactionsYear2011.size());
    }

    @Test
    public void findUniqueCitiesWhereTradersWork() {
        List<String> cities = transactions.stream()
                .map(trans -> trans.getTrader().getCity())
                .distinct()
                .collect(toList());

        assertEquals(2, cities.size());
        assertTrue(cities.contains("Cambridge"));
        assertTrue(cities.contains("Milan"));

    }

    @Test
    public void findTradersFromCambridgeSortedByName() {
        List<Trader> traders = getTraders()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(comparing(Trader::getName))
                .collect(toList());

        assertEquals(3, traders.size());
        assertEquals("Alan", traders.get(0).getName());
        assertEquals("Brian", traders.get(1).getName());
        assertEquals("Raoul", traders.get(2).getName());
    }

    private Stream<Trader> getTraders() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .distinct();
    }

    @Test
    public void formatTradersNamesSorted() {
        String result = getTraders().map(Trader::getName)
                .sorted()
                .collect(joining());

        assertEquals("AlanBrianMarioRaoul", result);
    }

    @Test
    public void findMilaneseTraders() {
        boolean milan = getTraders().anyMatch(trader -> "Milan".equals(trader.getCity()));

        assertTrue(milan);
    }

    @Test
    public void moveMilaneseTradersToCambridge() {
        transactions.forEach(trans -> trans.getTrader().setCity("Cambridge"));

        assertTrue(transactions.stream().allMatch(t -> "Cambridge".equals(t.getTrader().getCity())));

    }

    @Test
    public void findHighestValueTransaction() {
        int highestValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max().getAsInt();

        assertEquals(1000, highestValue);
    }

    @Test
    public void findLowestValueTransaction() {
        Transaction smallestTransaction = transactions.stream()
                .sorted(comparing(Transaction::getValue))
                .findFirst()
                .get();

        assertEquals(transactions.get(0), smallestTransaction);
    }

}
