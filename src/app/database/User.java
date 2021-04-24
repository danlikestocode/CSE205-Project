package app.database;

public class User {
    private static String username;

    public static String getUsername() { return username; }

    public static void setUsername(String newUsername) {
        username = newUsername;
        System.out.println("Logged in as user " + username);
    }
}
