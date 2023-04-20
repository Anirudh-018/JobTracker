import java.sql.*;
class TeamLeadFunctions{
    private Connection dbConnection=null;
    void connection() throws Exception{
        DbConnect con = new DbConnect();
        dbConnection = con.getConnection();
    }
    
    boolean authorized(String username,String password) throws Exception{
        String exists="select * from team_leads where username="+username;
        connection();
        Statement stmt=dbConnection.createStatement();
        ResultSet result=stmt.executeQuery(exists);
        if(!result.next()){
            System.out.println("no data found");
            return false;
        }
        else{
            if(result.getString("password").equals(password)){
                return true;
            }
            else{
                System.out.println("wrong username or password");
                return false;
            }
        }
    }

    void viewJob(String username) throws Exception{
        String fetcher="select * from jobs where head="+username;
        Statement stmt=dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet jobs=stmt.executeQuery(fetcher);
        if(!jobs.next()){
            System.out.println("no jobs registered");
        }
        else{
            System.out.println("jobId jobName StartedAt EstimatedTime Status commentsByAdmin currentCamUser");
            jobs.beforeFirst();
            while(jobs.next()){
                String output=jobs.getString("jobId")+" "+jobs.getString("jobName")+" "+jobs.getString("head")+" "+jobs.getDate("startedAt")+" "+
                jobs.getDate("estimatedTime")+" "+jobs.getString("status")+" "+jobs.getString("commentsByAdmin")+" "+jobs.getString("currentCamUser");
                System.out.println(output);
            }
        }
    }

    void updateStatus(int jobId,String updatedStatus) throws Exception{
        String updater="update jobs set Status="+updatedStatus+"where jobId="+jobId;
        connection();
        Statement stmt=dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        int result=stmt.executeUpdate(updater);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }

    void updateCamUserAccess(int jobId,String newUser) throws Exception{
        String updater="update jobs set Status="+newUser+"where jobId="+jobId;
        connection();
        Statement stmt=dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        int result=stmt.executeUpdate(updater);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }

}