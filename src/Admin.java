class Admin {
    static int id;
    private String username;
    private String password;

    // get username
    Admin(String username, String password) {
        id++;
        this.username = username;
        this.password = password;
    }
}