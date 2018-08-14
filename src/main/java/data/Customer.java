package data;

public class Customer {
    private final String customerName;
    private final int customerNumber;

    public Customer(String name, int customerNumber) {
        this.customerName = name;
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

}
