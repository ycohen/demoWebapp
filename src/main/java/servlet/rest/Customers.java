package servlet.rest;

import data.Customer;
import data.Jsonifiable;
import db.DbManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Path("customers")
public class Customers {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet() {
        try (Connection conn = DbManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT customerName, customerNumber " +
                "FROM customers");

            ResultSet resultSet = statement.executeQuery();

            List<Customer> customerNames = new ArrayList<>();

            while (resultSet.next()) {
                customerNames.add(new Customer(resultSet.getString("customerName"), resultSet.getInt("customerNumber")));
            }
            customerNames.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
            String jsonResponse = "{\"customers\": " + Jsonifiable.jsonifyCollection(customerNames) + "}";

            return jsonResponse;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "{}";
    }
}
