package zhekbland.jdbc;

public interface DatabaseCreator {
    void getConnection();
    void createDB();
    boolean checkTableExist();
    void deleteTable();

}
