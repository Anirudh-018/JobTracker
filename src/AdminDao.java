import java.sql.*;

public class AdminDao {
    private String adminTable = "admins";
    private String jobTable = "jobs";
    private Connection dbConnection = null;

    void connection() throws Exception {
        DbConnect con = new DbConnect();
        dbConnection = con.getConnection();
    }

    ResultSet selector() throws Exception {
        String sql = "select * from " + jobTable;
        connection();
        Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeQuery(sql);
    }

    ResultSet authSelector(String condition) throws Exception {
        String exist = "select * from " + adminTable + " where username='" + condition + "';";
        connection();
        Statement stmt = dbConnection.createStatement();
        return stmt.executeQuery(exist);
    }
    
    int updater(int id, String field, String newValue) throws Exception {
        System.out.println(newValue);
        String updateQuery = "update jobs set " + field + "='" + newValue + "' where jobId=" + Integer.toString(id) + ";";
        System.out.println(updateQuery);
        connection();
        Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeUpdate(updateQuery);
    }
}