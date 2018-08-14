package servlet.rest;

import com.google.gson.Gson;
import data.Order;
import data.OrderDetail;
import db.DbManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Path("orders")
public class Orders {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet(@QueryParam("customerKey") int customerKey) {
        try (Connection conn = DbManager.getConnection()) {
            Map<Integer, List<OrderDetail>> details = getOrderDetails(customerKey, conn);
            List<Order> orders = getOrders(customerKey, details, conn);

            orders.sort(Comparator.comparing(Order::getOrderDate));

            Gson gson = new Gson();

            return "{\"orders\": " + gson.toJson(orders) + "}";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    private List<Order> getOrders(int customerKey, Map<Integer, List<OrderDetail>> details, Connection conn) throws SQLException {
        List<Order> orders = new ArrayList<>();

        PreparedStatement statement = conn.prepareStatement("SELECT orderNumber, orderDate, " +
                    "requiredDate, shippedDate, status, comments, customerNumber " +
                "FROM customers NATURAL JOIN orders " +
                "WHERE customerNumber = ?");
        statement.setInt(1, customerKey);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int orderNumber = resultSet.getInt("orderNumber");
            LocalDate orderDate = resultSet.getDate("orderDate").toLocalDate();
            LocalDate requiredDate = resultSet.getDate("requiredDate").toLocalDate();
            LocalDate shippedDate = resultSet.getDate("shippedDate").toLocalDate();
            String status = resultSet.getString("status");
            String comment = resultSet.getString("comments");

            details.get(orderNumber).sort(Comparator.comparingInt(OrderDetail::getOrderLineNumber));

            Order order = new Order(orderNumber, orderDate, requiredDate, shippedDate, status, comment, details.get(orderNumber));
            orders.add(order);
        }

        return orders;
    }

    private Map<Integer, List<OrderDetail>> getOrderDetails(int customerKey, Connection conn) throws SQLException {
        Map<Integer, List<OrderDetail>> details = new HashMap<>();

        PreparedStatement statement = conn.prepareStatement("SELECT orderdetails.orderNumber, orderdetails.orderLineNumber, orderdetails.orderNumber, productName, " +
                "orderdetails.quantityOrdered, orderdetails.priceEach " +
            "FROM customers NATURAL JOIN orders NATURAL JOIN orderdetails NATURAL JOIN products " +
            "WHERE customerNumber = ?");

        statement.setInt(1, customerKey);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int orderNumber = resultSet.getInt("orderNumber");
            int orderLineNumber = resultSet.getInt("orderLineNumber");
            String productName = resultSet.getString("productName");
            int quantity = resultSet.getInt("quantityOrdered");
            String price = resultSet.getString("priceEach");

            OrderDetail orderDetail = new OrderDetail(orderLineNumber, orderNumber, productName, quantity, price);
            details.computeIfAbsent(orderNumber, (orderNum) -> new ArrayList<>()).add(orderDetail);
        }

        return details;
    }

}
