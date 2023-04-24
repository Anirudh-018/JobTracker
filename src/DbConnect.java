import java.sql.*;
class DbConnect{
    static private String host="localhost";
    static private String dbName="job_tracker";
    static private String password="sairam123";
    static private String username="root";
    static private int port=3306;
    static private String url="jdbc:mysql://"+host+":"+port+"/"+dbName;
    static Connection con=null;
    static DbConnect ref=null;
    Connection getConnection() throws Exception{
        con=DriverManager.getConnection(url, username, password);
        return con;
    }
    DbConnect DbConnect(){
        if(ref==null){
            return new DbConnect();
        }
        else{
            return ref;
        }
    }
}