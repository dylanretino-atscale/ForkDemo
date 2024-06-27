package com.atscale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AtScalePostgreSQLExample {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:postgresql://localhost:15432/sml-models_main";
        String user = "admin";
        String password = "admin";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the AtScale server successfully.");

            // Create a statement object
            statement = connection.createStatement();

            // Execute a query
            String query = "SELECT \"Internet Sales\".\"CountryCity\" AS \"Country\",\n" + //
                                "  SUM(\"Internet Sales\".\"orderquantity1\") AS \"Order Quantity\"\n" + //
                                "FROM \"sml-models_main\".\"Internet Sales\" \"Internet Sales\"\n" + //
                                "GROUP BY 1";
            resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                String Country = resultSet.getString("Country");
                Long OrderQuantity = resultSet.getLong("Order Quantity");
                // Fetch other columns as needed
                
                // Display the results
                System.out.println("Country: " + Country + ", Order Quantity: " + OrderQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

