package com.java_8_training.examples.design.template;


public abstract class CustomerLoanTemplate {
    public void onCustomerLoanRequest(int id, int amount) {
        Customer customer = Database.getCustomerWithId(id);
        if (checkLoan(customer, amount) && canWeAffordLoan(amount)) {
            loanMoney(customer, amount);
        }
    }

    abstract boolean canWeAffordLoan(int amount);

    abstract boolean checkLoan(Customer c, int amount);

    abstract void loanMoney(Customer c, int amount);
}
