package com.zhekbland.persistence;

import java.sql.*;

public class CustomerDB {

    private final Config config;

    private Connection connection;

    private static final CustomerDB INSTANCE = new CustomerDB();

    /**
     * Create and init config and get values from parser.properties.
     */
    public CustomerDB() {
        this.config = new Config();
        config.init();
        getConnection();
    }

    public static CustomerDB getInstance() {
        return INSTANCE;
    }

    /**
     * Get connection and check database exist or not.
     */
    public void getConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            System.out.println("Database already exists");
        } catch (SQLException e) {
            System.out.println("Database doesn't exist");
            createDB();
        }
    }

    /**
     * Create database if it wasn't create.
     */
    public void createDB() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/",
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            try (Statement st = connection.createStatement()) {
                String sql = "CREATE DATABASE spring_customer_tracker";
                st.executeUpdate(sql);
                System.out.println("Database was create");
                getConnection();
            } catch (SQLException e) {
                System.out.println("DB error: " + e);
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
    }

    /**
     * Check table jobs exist or not.
     * If table doesn't exist then create.
     *
     * @return exist (true) or not exist (false).
     */
    public boolean checkTableExist() {
        boolean result = false;
        try (Statement st = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "customer", null);
            if (rs.next()) {
                result = true;
            } else {
                st.addBatch("create table customer (id serial primary key, first_name varchar(45),"
                        + "last_name varchar(45), email varchar(45))");
                st.addBatch("INSERT INTO customer(first_name, last_name, email)"
                        + "values('David', 'Adams', 'david@gmail.com')");
                st.addBatch("INSERT INTO customer(first_name, last_name, email)"
                        + "values('John', 'Boston', 'john@gmail.com')");
                st.addBatch("INSERT INTO customer(first_name, last_name, email)"
                        + "values('Linda', 'Stark', 'linda@gmail.com')");
                st.executeBatch();
                System.out.println("Tables were created.");
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
        return result;
    }

    /**
     * Delete table.
     */
    public void deleteTable() {
        try (Statement st = connection.createStatement()) {
            st.addBatch("DROP TABLE customer");
            st.executeBatch();
            System.out.println("Tables were deleted");
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
    }
}