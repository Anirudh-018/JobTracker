import java.sql.*;
import java.util.*;

public class App {
    private static Connection dbConnection = null;
    private static Scanner s = new Scanner(System.in);
    private static AdminFunctions admin = new AdminFunctions();
    private static TeamLeadFunctions teamLead=new TeamLeadFunctions();
    static void connection() throws Exception {
        DbConnect con = new DbConnect();
        dbConnection = con.getConnection();
    }

    static void updateLogicAdmin() throws Exception {
        int choice = 1;
        while (choice > 0) {
            System.out.println(
                    "1 for update lead name\n2 for adding comments\n3 for updating client\n0 to return to the home page");
            choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("all the jobs are");
                    admin.viewAllJobs();
                    System.out.println("enter job id");
                    int id = s.nextInt();
                    s.nextLine();
                    System.out.println("enter new team lead name");
                    String newName = s.nextLine();
                    admin.updateLeadName(id, newName);
                    break;
                case 2:
                    System.out.println("all the jobs are");
                    admin.viewAllJobs();
                    System.out.println("enter job id");
                    id = s.nextInt();
                    s.nextLine();
                    System.out.println("enter comments");
                    String comment = s.nextLine();
                    admin.updateCommentsByAdmin(id, comment);
                    break;
                case 3:
                    System.out.println("all the jobs are");
                    admin.viewAllJobs();
                    System.out.println("enter job id");
                    id = s.nextInt();
                    s.nextLine();
                    System.out.println("enter new client name");
                    String client = s.nextLine();
                    admin.updateClient(id, client);
                    break;
                case 0:
                    System.out.println("exiting update");
                    return;
            }
        }
    }

    static void updateLogicTeamLead(String username) throws Exception{
        int choice=1;
        while(choice>0){
            System.out.println("1 for update current project status\n2 for updating cam access\n0 to return to the home page");
            choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("all the jobs are");
                    teamLead.viewJob(username);
                    System.out.println("enter job id");
                    int id = s.nextInt();
                    s.nextLine();
                    System.out.println("enter current project status");
                    String status = s.nextLine();
                    teamLead.updateStatus(id, status);
                    break;
                case 2:
                    System.out.println("all the jobs are");
                    teamLead.viewJob(username);
                    System.out.println("enter job id");
                    id = s.nextInt();
                    s.nextLine();
                    System.out.println("enter current cam user");
                    String newCamUser = s.nextLine();
                    teamLead.updateStatus(id, newCamUser);
                case 0:
                    System.out.println("exiting update");
                    return;
            }
        }
    }

    void adminLogin() throws Exception {
        System.out.println("this is the job tracker admin login");
        System.out.println("enter username:");
        String username = s.nextLine();
        System.out.println("enter password");
        String password = s.nextLine();
        if (admin.authorised(username, password)) {
            System.out.println("welcome");
            int choice = 1;
            while (choice > 0) {
                System.out.println(
                        "1 for view all jobs \n2for update details\n3 for approve a job\n4 for delete a job\n5 to add a new team lead\n6 to add a new job\n0 to exit the menu");
                choice = s.nextInt();
                s.nextLine();
                switch (choice) {
                    case 1:
                        admin.viewAllJobs();
                        break;
                    case 0:
                        System.out.println("thanks for using");
                        System.out.println("logging out admin");
                        return;
                    case 2:
                        updateLogicAdmin();
                        break;
                    // case 3:
                    // System.out.println("approve or delete job");
                    // admin.viewAllJobs();
                    // System.out.println("enter job id");
                    // int id=s.nextInt();
                    case 5:
                    System.out.println("add new team lead");
                    System.out.println("enter username");
                    String teamLeadUsername=s.nextLine();
                    admin.addTeamLead(teamLeadUsername);
                    break;
                }
            }

        }
    }

    void teamLeadLogin() throws Exception{
        System.out.println("this is the job tracker team lead login");
        System.out.println("enter username:");
        String username = s.nextLine();
        System.out.println("enter password");
        String password = s.nextLine();
        TeamLead lead=teamLead.authorized(username, password);
        if (lead.team_lead_id!=0) {
            System.out.println("welcome");
            int choice = 1;
            while (choice > 0) {
                System.out.println(
                        "1 for view all jobs \n2for update details\n0 for logging out");
                choice = s.nextInt();
                switch (choice) {
                    case 1:
                        teamLead.viewJob(lead.getUserName());
                        break;
                    case 0:
                        System.out.println("thanks for using");
                        System.out.println("logging out team lead");
                        return;
                    case 2:
                        updateLogicTeamLead(lead.getUserName());
                        break;
                }
            }

        }
    }
}
