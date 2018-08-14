package servlet.rest;

import com.google.gson.Gson;
import data.Customer;
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

            List<Customer> customers = new ArrayList<>();

            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getString("customerName"), resultSet.getInt("customerNumber")));
            }
            customers.sort(Comparator.comparing(o -> o.getCustomerName().toLowerCase()));

            Gson gson = new Gson();

            return gson.toJson(customers);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "[]";
    }
}
