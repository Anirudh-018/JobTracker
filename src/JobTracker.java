import java.util.*;

public class JobTracker {
    private static App app=new App();
    public static void main(String[] args) throws Exception{
        Scanner s=new Scanner(System.in);
        int choice=1;
        while(choice>0){
            System.out.println("welcome to job tracker");
            System.out.println("enter 1 for admin\n2 for team lead\n0 for exit");
            choice=s.nextInt();
            switch(choice){
                case 1:
                    app.adminLogin();
                    break;
                case 2:
                    app.teamLeadLogin();
                    break;
                case 0:
                    System.exit(1);
                    break;
            }
        }
    }
}