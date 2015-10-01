package com.java_8_training.examples.design.template;


import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;

public abstract class CustomerLoanLambdaTemplate {
    public static void onCustomerLoanRequest(
            int id,
            int amount,
            IntPredicate canWeAffordLoan,
            BiFunction<Customer, Integer, Boolean> checkLoan,
            BiConsumer<Customer, Integer> loanMoney) {

        Customer customer = Database.getCustomerWithId(id);
        if (checkLoan.apply(customer, amount)
        &&  canWeAffordLoan.test(amount)) {

            loanMoney.accept(customer, amount);
        }
    }
}
