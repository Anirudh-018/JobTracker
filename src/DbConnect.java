import java.sql.*;
class DbConnect{
    private String host="localhost";
    private String dbName="job_tracker";
    private String password="sairam123";
    private String username="root";
    private int port=3306;
    private String url="jdbc:mysql://"+host+":"+port+"/"+dbName;
    Connection con=null;
    Connection getConnection() throws Exception{
        con=DriverManager.getConnection(url, username, password);
        return con;
    }
}