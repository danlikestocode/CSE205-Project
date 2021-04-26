package app.database;

//Creates a user based on username and position
public class User {
    private static String username;
    private static int designation;

    //Getters which return the user and position
    public static String getUsername() { return username; }
    public static int getDesignation() { return designation; }

    //Mutator which will Tell the user what account is logged in
    public static void setUsername(String newUsername) {
        username = newUsername;
        System.out.println("Logged in as user " + username);
    }
    public static void setDesignation(int newDesignation) {
        designation = newDesignation;
    }
}
