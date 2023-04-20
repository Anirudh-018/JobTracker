import java.sql.*;
class AdminFunctions {
    private Connection dbConnection = null;
    private AdminDao adminDao=new AdminDao();
    private 
    void connection() throws Exception {
        DbConnect con = new DbConnect();
        dbConnection = con.getConnection();
    }

    // check for login
    boolean authorised(String username,String password) throws Exception{
        ResultSet result=adminDao.authSelector(username);
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

    void viewAllJobs() throws Exception{
        ResultSet jobs=adminDao.selector();
        if(!jobs.next()){
            System.out.println("no jobs registered");
        }
        else{
            System.out.println("jobId jobName Head StartedAt EstimatedTime Status commentsByAdmin clinetName");
            jobs.beforeFirst();
            while(jobs.next()){
                String output=jobs.getString("jobId")+" "+jobs.getString("jobName")+" "+jobs.getString("head")+" "+jobs.getDate("startedAt")+" "+
                jobs.getDate("estimatedTime")+" "+jobs.getString("status")+" "+jobs.getString("commentsByAdmin")+" "+jobs.getString("clientName");
                System.out.println(output);
            }
        }
    }

    void updateLeadName(int id,String newName) throws Exception{
        String field="head";
        int result=adminDao.updater(id, field, newName);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }
    void updateClient(int id,String updatedClient) throws Exception{
        String updateNameQuery="update jobs set clientName='"+updatedClient+"' where jobId="+id;
        connection();
        Statement stmt=dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        int result=stmt.executeUpdate(updateNameQuery);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }
    void updateCommentsByAdmin(int id,String comment) throws Exception{
        String updateNameQuery="update jobs set commentsByAdmin='"+comment+"' where jobId="+id;
        connection();
        Statement stmt=dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        int result=stmt.executeUpdate(updateNameQuery);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }
}