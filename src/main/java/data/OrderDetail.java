package data;

public class OrderDetail implements Jsonifiable {
    private final int orderNumber;
    private final int orderLineNumber;
    private final String productName;
    private final int quantity;
    private final String price;

    public OrderDetail(int orderNumber, int orderLineNumber, String productName, int quantity, String price) {
        this.orderNumber = orderNumber;
        this.orderLineNumber = orderLineNumber;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;

    }

    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    @Override
    public String toJson() {
        return "{\"orderNumber\": " + orderNumber +
                ", \"orderLineNumber\": " + orderLineNumber +
                ", \"productName\": \"" + productName +
                "\", \"quantity\": " + quantity +
                ", \"price\": " + price + "}";
    }
}
