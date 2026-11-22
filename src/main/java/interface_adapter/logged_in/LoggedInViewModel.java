package interface_adapter.logged_in;

import java.util.ArrayList;
import java.util.List;

/**
 * The View Model shared by the views shown while a user is logged in (MainView and UserView).
 */
public class LoggedInViewModel {

    private final List<LoggedInViewModelListener> listeners = new ArrayList<>();

    /** The main view, shown right after logging in. */
    public static final String VIEW_NAME = "main";

    /** The user view, with account actions such as changing the password and logging out. */
    public static final String USER_VIEW_NAME = "user";

    private LoggedInState state = new LoggedInState();

    public LoggedInState getState() {
        return this.state;
    }

    public void setState(LoggedInState state) {
        this.state = state;
    }

    public void addListener(LoggedInViewModelListener listener) {
        listeners.add(listener);
    }

    public void notifyStateChanged() {
        for (LoggedInViewModelListener listener : listeners) {
            listener.stateChanged();
        }
    }
}
