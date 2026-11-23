package view;

import interface_adapter.ViewSwitchController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in.LoggedInViewModelListener;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;

/**
 * The user view: account actions for the logged-in user, such as logging out.
 */
public class UserView extends JPanel implements LoggedInViewModelListener {

    private static final float TITLE_FONT_SCALE = 1.5f;

    private final LoggedInViewModel loggedInViewModel;
    private final JLabel username = new JLabel();
    private final JButton logOut = new JButton("Log Out");
    private final JButton back = new JButton("Back");

    private LogoutController logoutController;
    private ViewSwitchController viewSwitchController;

    public UserView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addListener(this);

        final JLabel title = new JLabel("User");
        title.setFont(title.getFont().deriveFont(Font.BOLD, title.getFont().getSize() * TITLE_FONT_SCALE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel userInfo = new JPanel();
        userInfo.add(new JLabel("Logged in as: "));
        userInfo.add(username);

        final JPanel buttons = new JPanel();
        buttons.add(back);
        buttons.add(logOut);

        logOut.addActionListener(evt -> logoutController.execute());
        back.addActionListener(evt -> viewSwitchController.switchTo(LoggedInViewModel.VIEW_NAME));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(userInfo);
        this.add(buttons);
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setViewSwitchController(ViewSwitchController viewSwitchController) {
        this.viewSwitchController = viewSwitchController;
    }

    /**
     * The logged-in state was updated.
     */
    @Override
    public void stateChanged() {
        final LoggedInState state = loggedInViewModel.getState();
        username.setText(state.getUsername());
    }
}
