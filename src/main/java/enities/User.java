package enities;

import java.util.Objects;

/**
 * Represents a basic user in the support ticketing system.
 * Each user has a unique username and a password.
 */
public class User {
    private String username;
    private String password;

    /**
     * Constructs a new enities.User with the specific username and password.
     *
     * @param username The unique username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Determines if two enities.User objects are equal based on their usernames.
     *
     * @param obj The object to compare with this user.
     * @return true if the usernames match; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.username.equals(other.username);
    }

    /**
     * Generates a hash code for the enities.User object
     * that incorporates the username and password fields.
     * to reduce the chances of hash collisions.
     *
     * @return A hash code value for the object.
     */
    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash + (11 * Objects.hash(this.username));
        hash = hash + (11 * Objects.hash(this.password));
        return hash;
    }


    /**
     * Returns a string representation of the user.
     *
     * @return A string containing the user's username.
     */
    @Override
    public String toString() {
        return "enities.User{" + "username='" + username + '\'' + '}';
    }
}
