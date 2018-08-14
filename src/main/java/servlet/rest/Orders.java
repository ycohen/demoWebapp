package servlet.rest;

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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Path("orders")
public class Orders {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet(@QueryParam("customer") String customerName) {
        List<OrderDetail> orders = new ArrayList<>();
        try (Connection conn = DbManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT orders.orderDate, orderdetails.orderNumber, productName, " +
                    "orderdetails.quantityOrdered, orderdetails.priceEach " +
                "FROM customers NATURAL JOIN orders NATURAL JOIN orderdetails NATURAL JOIN products " +
                "WHERE customerName = ?");

            statement.setString(1, customerName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LocalDate orderDate = resultSet.getDate("orderDate").toLocalDate();
                String productName = resultSet.getString("productName");
                int quantity = resultSet.getInt("quantityOrdered");
                String price = resultSet.getString("priceEach");

                OrderDetail orderDetail = new OrderDetail(orderDate, productName, quantity, price);
                orders.add(orderDetail);
            }

            orders.sort(Comparator.comparing(OrderDetail::getOrderDate));

            return "{\"orders\": " + getJsonifiedCollection(orders) + "}";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "{}";
    }

    private StringBuilder getJsonifiedCollection(List<OrderDetail> orderDetailCollection) {
        StringBuilder jsonResponse = new StringBuilder("[");

        for (OrderDetail orderDetail: orderDetailCollection) {
            jsonResponse.append(orderDetail.asJson()).append(", ");
        }

        if (jsonResponse.length() > 2) {
            jsonResponse.delete(jsonResponse.length() - 2, jsonResponse.length());
        }
        jsonResponse.append("]");
        return jsonResponse;
    }
}
