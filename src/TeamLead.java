import java.util.*;
class TeamLead{
    static int leads_count=0;
    int team_lead_id;
    private String username;
    private String password;
    private String contact;
    private String task_ids;
    private String department;
    //setter for the team lead invoked during login
    public TeamLead(int id,String username,String password,String contact,String task_ids,String department){
        this.team_lead_id=id;
        this.username=username;
        this.password=password;
        this.contact=contact;
        this.task_ids=task_ids;
        this.department=department;
    }
    //getters
    public String getUserName(){
        return this.username;
    }
    public String getTasks(){
        return this.task_ids;
    }
}