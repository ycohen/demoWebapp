package data;

public class Customer implements Jsonifiable {
    private final String name;
    private final int customerNumber;

    public Customer(String name, int customerNumber) {
        this.name = name;
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    @Override
    public String toJson() {
        return "{\"customerName\": \"" + name + "\", \"customerNumber\": " + customerNumber + "}";
    }
}
