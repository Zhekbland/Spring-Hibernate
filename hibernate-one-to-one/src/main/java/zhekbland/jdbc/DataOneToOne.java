package zhekbland.jdbc;

import java.sql.*;

public class DataOneToOne implements DatabaseCreator {

    private final Config config;

    private Connection connection;

    private static final DataOneToOne INSTANCE = new DataOneToOne();

    /**
     * Create and init config and get values from parser.properties.
     */
    public DataOneToOne() {
        this.config = new Config();
        config.init();
        getConnection();
    }

    public static DataOneToOne getInstance() {
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
                String sql = "CREATE DATABASE hb_one_to_one";
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
            ResultSet rs = metaData.getTables(null, null, "instructor", null);
            if (rs.next()) {
                result = true;
            } else {
                st.addBatch("create table instructor_detail (id serial primary key, youtube_channel varchar(128),"
                        + "hobby varchar(45))");
                st.addBatch("create table instructor (id serial primary key, first_name varchar(45),"
                        + "last_name varchar(45), email varchar(45),"
                        + "instructor_detail_id int references instructor_detail(id))");
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
            st.addBatch("DROP TABLE instructor");
            st.addBatch("DROP TABLE instructor_detail");
            st.executeBatch();
            System.out.println("Tables were deleted");
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
    }
}