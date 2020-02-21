package com.zhekbland.springsecurity.persistence;

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
//            createDB();
        }
    }

    /**
     * Create database if it wasn't create.
     */
    public void createDB() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/",
                    config.get("jdbc.user"),
                    config.get("jdbc.password")
            );
            try (Statement st = connection.createStatement()) {
                String sql = "CREATE DATABASE spring_security_db";
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
            ResultSet rs = metaData.getTables(null, null, "users", null);
            if (rs.next()) {
                result = true;
            } else {
                st.executeUpdate("create table users (username varchar(50) primary key,"
                        + "password char(68) not null,"
                        + "enabled tinyint(1) not null)");

                st.executeUpdate("create table authorities (username varchar(50) not null references users(user),"
                        + "authority varchar(50) not null)");

                st.addBatch("INSERT INTO users values('john',"
                        + "'{bcrypt}$2a$10$xejjGhLS/pa5khDJJISspObul.y7YD8LCnFnw3urJd5HnB.B.5906', 1)");
                st.addBatch("INSERT INTO users values('mary',"
                        + "'{bcrypt}$2a$10$xejjGhLS/pa5khDJJISspObul.y7YD8LCnFnw3urJd5HnB.B.5906', 1)");
                st.addBatch("INSERT INTO users values('susan',"
                        + "'{bcrypt}$2a$10$xejjGhLS/pa5khDJJISspObul.y7YD8LCnFnw3urJd5HnB.B.5906', 1)");

                st.addBatch("INSERT INTO authorities values('john', 'ROLE_EMPLOYEE')");
                st.addBatch("INSERT INTO authorities values('mary', 'ROLE_EMPLOYEE')");
                st.addBatch("INSERT INTO authorities values('mary', 'ROLE_MANAGER')");
                st.addBatch("INSERT INTO authorities values('susan', 'ROLE_EMPLOYEE')");
                st.addBatch("INSERT INTO authorities values('susan', 'ROLE_ADMIN')");
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