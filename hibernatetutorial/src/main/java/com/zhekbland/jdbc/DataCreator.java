package com.zhekbland.jdbc;



import java.sql.*;

/**
 * Class DataCreator creates database vacancy and table jobs.
 * Takes information from class Parser and put into table jobs.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class DataCreator implements AutoCloseable {

    private final Config config;

    private Connection connection;

    /**
     * Create and init config and get values from parser.properties.
     */
    public DataCreator() {
        this.config = new Config();
        config.init();
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
    private void createDB() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/",
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            try (Statement st = connection.createStatement()) {
                String sql = "CREATE DATABASE hb_student_tracker";
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
            ResultSet rs = metaData.getTables(null, null, "student", null);
            if (rs.next()) {
                result = true;
            } else {
                st.executeUpdate("create table student (id serial primary key, first_name varchar(45),"
                        + "last_name varchar(45), email varchar(45))");
                System.out.println("Table jobs was create.");
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
        return result;
    }

    /**
     * Create database if it wasn't create.
     */
    public void deleteTable() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/hb_student_tracker",
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            try (Statement st = connection.createStatement()) {
                String sql = "DROP TABLE student";
                st.executeUpdate(sql);
                System.out.println("Table was delete");
            } catch (SQLException e) {
                System.out.println("DB error: " + e);
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
    }

    /**
     * Implementation of AutoCloseable.
     */
    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.err.println("DB error: " + e);
            }
        }
    }
}