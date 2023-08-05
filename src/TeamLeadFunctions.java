import java.sql.*;
class TeamLeadFunctions{
    private Connection dbConnection=null;
    private TeamLeadDao teamLeadDao=new TeamLeadDao();
    void connection() throws Exception{
        DbConnect con = new DbConnect();
        dbConnection = con.getConnection();
    }
    
    TeamLead authorized(String username,String password) throws Exception{
        ResultSet result=teamLeadDao.authSelector(username);
        if(!result.next()){
            System.out.println("no data found");
            return new TeamLead(0,null,null,null,null,null);
        }
        else{
            if(result.getString("password").equals(password)){
                TeamLead lead=new TeamLead(
                    result.getInt("team_lead_id"), 
                    result.getString("username"), 
                    result.getString("password"), 
                    result.getString("contact"), 
                    result.getString("task_ids"),
                    result.getString("department"));
                return lead;
            }
            else{
                System.out.println("wrong username or password");
                return new TeamLead(0,null,null,null,null,null);
            }
        }
    }

    void viewJob(String username) throws Exception{
        ResultSet jobs=teamLeadDao.selector(username);
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
        String field="Status";
        int result=teamLeadDao.updater(jobId, field, updatedStatus);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }

    void updateCamUserAccess(int jobId,String newUser) throws Exception{
        String field="currentCamUser";
        int result=teamLeadDao.updater(jobId, field, newUser);
        if(result==1){
            System.out.println("succesfully updated");
        }
        else{
            System.out.println("something went wrong");
        }
    }

}