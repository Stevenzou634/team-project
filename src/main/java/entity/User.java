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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}