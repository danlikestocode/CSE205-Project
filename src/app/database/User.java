package app.database;

public class User {
    private static String username;
    private static int designation;

    public static String getUsername() { return username; }
    public static int getDesignation() { return designation; }

    public static void setUsername(String newUsername) {
        username = newUsername;
        System.out.println("Logged in as user " + username);
    }
    public static void setDesignation(int newDesignation) {
        designation = newDesignation;
    }
}
