package com.java_8_training.problems.collectors;


import com.java_8_training.answers.streams.Trader;
import com.java_8_training.answers.streams.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

@Ignore
public class TransactionsAndTradesPart2Test {

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
    public void mapEachTraderToTheSumOfTheirTransactions() {
        //TODO: Create a Map<String, Integer> that maps each Trader's name with the sum of all its Transactions’ values
        Map<String, Integer> mapNameToSumValue = new HashMap<>();


        mapNameToSumValue = null;


        assertEquals(1400, (int) mapNameToSumValue.get("Raoul"));
        assertEquals(300, (int) mapNameToSumValue.get("Brian"));
        assertEquals(950, (int) mapNameToSumValue.get("Alan"));
        assertEquals(1410, (int) mapNameToSumValue.get("Mario"));
    }

    @Test
    public void collectTheTransactionWithHighestValue() {
        //TODO: What's the transaction with highest value? (using Collectors.maxBy)
        Transaction highestTransaction = null;

        highestTransaction = null;

        assertEquals(transactions.get(1), highestTransaction);
    }

    @Test
    public void mapEachYearToItsHighestTransaction() {
        //TODO: Create a Map<Integer, Integer> that maps each year with the highest transaction value of that year
        Map<Integer, Integer> yearToHighestValue = new HashMap<>();


        // IntelliJ complaining but code is correct & running
        yearToHighestValue = null;

        assertEquals(1000, (int) yearToHighestValue.get(2012));
        assertEquals(400, (int) yearToHighestValue.get(2011));
    }

}
