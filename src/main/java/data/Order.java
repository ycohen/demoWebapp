package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Order implements Jsonifiable {
    private final int orderNumber;

    private final LocalDate orderDate;
    private final LocalDate requiredDate;
    private final LocalDate shippedDate;
    private final String status;
    private final String comment;
    private final Collection<OrderDetail> details;

    public Order(int orderNumber, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
                 String status, String comment, Collection<OrderDetail> details) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comment = comment;
        this.details = new ArrayList<>(details);
    }

    @Override
    public String toJson() {
        return "{\"orderNumber\": " + orderNumber +
                ", \"orderDate\": \"" + orderDate +
                "\", \"requiredDate\": \"" + requiredDate +
                "\", \"shippedDate\": \"" + shippedDate +
                "\", \"status\": \"" + status +
                "\", \"comment\": \"" + comment +
                "\", \"orderDetails\": " + Jsonifiable.jsonifyCollection(details) +
                "}";
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
