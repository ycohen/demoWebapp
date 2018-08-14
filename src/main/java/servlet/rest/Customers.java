package servlet.rest;

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
import java.util.Collections;
import java.util.List;

@Path("customers")
public class Customers {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet() {
        try (Connection conn = DbManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT customerName " +
                "FROM customers");

            ResultSet resultSet = statement.executeQuery();

            List<String> customerNames = new ArrayList<>();

            while (resultSet.next()) {
                customerNames.add(resultSet.getString("customerName"));
            }
            Collections.sort(customerNames);
            StringBuilder jsonResponse = getJsonifiedCustomers(customerNames);

            return jsonResponse.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "{}";
    }

    private StringBuilder getJsonifiedCustomers(List<String> customerNames) {
        StringBuilder jsonResponse = new StringBuilder("{\"customers\": [");

        for (String name: customerNames) {
           jsonResponse.append("\"").append(name).append("\", ");
        }

        jsonResponse.delete(jsonResponse.length() - 2, jsonResponse.length());
        jsonResponse.append("]}");
        return jsonResponse;
    }
}
