package data;

import java.time.LocalDate;

public class OrderDetail {
    private LocalDate orderDate;
    private String productName;
    private int quantity;
    private String price;

    public OrderDetail(LocalDate orderDate, String productName, int quantity, String price) {
        this.orderDate = orderDate;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;

    }

    public String asJson() {
        return "{\"orderDate\": \"" + orderDate +
                "\", \"productName\": \"" + productName +
                "\", \"quantity\": " + quantity +
                ", \"price\": " + price + "}";
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
