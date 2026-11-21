package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A registered user and the results they have saved.
 */
public class User {
    private final String username;
    private final String password;
    private final List<TrackedLocation> savedLocations;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.savedLocations = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<TrackedLocation> getSavedLocations() {
        return Collections.unmodifiableList(savedLocations);
    }

    /**
     * Adds a result to the saved list.
     */
    public void saveLocation(TrackedLocation result) {
        savedLocations.add(result);
    }

    /**
     * Removes a result from the saved list.
     */
    public void removeLocation(TrackedLocation result) {
        savedLocations.remove(result);
    }
}
