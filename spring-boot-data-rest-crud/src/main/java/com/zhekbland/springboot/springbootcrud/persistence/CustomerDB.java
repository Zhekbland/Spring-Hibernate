package com.zhekbland.springboot.springbootcrud.persistence;

import java.sql.*;

public class CustomerDB {

    private final Config config;

    private Connection connection;

    private static final CustomerDB INSTANCE = new CustomerDB();

    /**
     * Create and init config and get values from persistence-mysql.properties.
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
                    config.get("jdbc.user"),
                    config.get("jdbc.password")
            );
            System.out.println("Database already exists");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Database doesn't exist");
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
            ResultSet rs = metaData.getTables(null, null, "employee", null);
            if (rs.next()) {
                result = true;
            } else {
                st.executeUpdate("create table employee (id serial primary key, first_name varchar(45) not null,"
                        + "last_name varchar(45) not null, email varchar(45) not null)");

                st.addBatch("INSERT INTO employee(first_name, last_name, email)"
                        + "values('Leslie', 'Andrews', 'leslie@gmail.com')");
                st.addBatch("INSERT INTO employee(first_name, last_name, email)"
                        + "values('Emma', 'Baumgarten', 'emma@gmail.com')");
                st.addBatch("INSERT INTO employee(first_name, last_name, email)"
                        + "values('Linda', 'Stark', 'linda@gmail.com')");
                st.addBatch("INSERT INTO employee(first_name, last_name, email)"
                        + "values('Avani', 'Gupta', 'avani@gmail.com')");
                st.addBatch("INSERT INTO employee(first_name, last_name, email)"
                        + "values('Juan', 'Vega', 'juan@gmail.com')");
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