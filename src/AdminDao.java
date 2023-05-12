import java.sql.*;
import java.security.*;
import java.util.*;
public class AdminDao {
    private String adminTable = "admins";
    private String jobTable = "jobs";
    private Connection dbConnection = null;

    void connection() throws Exception {
        DbConnect con = new DbConnect();
        dbConnection = con.getConnection();
    }

    //password generator
    public static String generateRandomPassword(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
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
        String updateQuery = "update jobs set " + field + "='" + newValue + "' where jobId=" + Integer.toString(id)
                + ";";
        System.out.println(updateQuery);
        connection();
        Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeUpdate(updateQuery);
    }

    int deleter(int id) throws Exception {
        String deleteQuery = "delete from jobs where jobId=" + id;
        connection();
        Statement stmt = dbConnection.createStatement();
        return stmt.executeUpdate(deleteQuery);
    }

    int adderJob(String jobName, String head, String clientName) throws Exception {
        String insertJobQuery = "insert into jobs(jobName,head,clientName) values('" + jobName + "','" + head + "','"
                + clientName + "',)";
        connection();
        Statement stmt = dbConnection.createStatement();
        return stmt.executeUpdate(insertJobQuery);
    }

    int adderTeamLead(String username) throws Exception {
        Random rand = new Random();
   
        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(10,15);
        String password=generateRandomPassword(rand_int1);
        String addLeadQuery = "insert into team_leads values('"+username+"','"+password+"')";
        System.out.println(addLeadQuery);
        connection();
        Statement stmt = dbConnection.createStatement();
        return stmt.executeUpdate(addLeadQuery);
    }

    int removeLead(String username)throws Exception{
        String removeLeadQuery="delete from team_leads where username=" + username;
        connection();
        Statement stmt=dbConnection.createStatement();
        return stmt.executeUpdate(removeLeadQuery);
    }
}