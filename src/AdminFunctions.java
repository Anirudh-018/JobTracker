import java.sql.*;

class AdminFunctions {
    private AdminDao adminDao = new AdminDao();

    // check for login
    boolean authorised(String username, String password) throws Exception {
        ResultSet result = adminDao.authSelector(username);
        if (!result.next()) {
            System.out.println("no data found");
            return false;
        } else {
            if (result.getString("password").equals(password)) {
                return true;
            } else {
                System.out.println("wrong username or password");
                return false;
            }
        }
    }

    void viewAllJobs() throws Exception {
        ResultSet jobs = adminDao.selector();
        if (!jobs.next()) {
            System.out.println("no jobs registered");
        } else {
            System.out.println("jobId jobName Head StartedAt EstimatedTime Status commentsByAdmin clinetName");
            jobs.beforeFirst();
            while (jobs.next()) {
                String output = jobs.getString("jobId") + " " + jobs.getString("jobName") + " " + jobs.getString("head")
                        + " " + jobs.getDate("startedAt") + " " +
                        jobs.getDate("estimatedTime") + " " + jobs.getString("status") + " "
                        + jobs.getString("commentsByAdmin") + " " + jobs.getString("clientName");
                System.out.println(output);
            }
        }
    }

    void updateLeadName(int id, String newName) throws Exception {
        String field = "head";
        int result = adminDao.updater(id, field, newName);
        if (result == 1) {
            System.out.println("succesfully updated");
        } else {
            System.out.println("something went wrong");
        }
    }

    void updateClient(int id, String updatedClient) throws Exception {
        String field = "clientName";
        int result = adminDao.updater(id, field, updatedClient);
        if (result == 1) {
            System.out.println("succesfully updated");
        } else {
            System.out.println("something went wrong");
        }
    }

    void updateCommentsByAdmin(int id, String comment) throws Exception {
        String field = "commentsByAdmin";
        int result = adminDao.updater(id, field, comment);
        if (result == 1) {
            System.out.println("succesfully updated");
        } else {
            System.out.println("something went wrong");
        }
    }

    void addJob(String jobName, String head, String clientName) throws Exception {
        int result = adminDao.adderJob(jobName, head, clientName);
        if (result == 1) {
            System.out.println("job succesfully created");
        } else {
            System.out.println("something went wrong");
        }
    }

    void addTeamLead(String username) throws Exception {
        int result = adminDao.adderTeamLead(username);
        if (result == 1) {
            System.out.println("team lead created");
        } else {
            System.out.println("something went wrong");
        }
    }
}