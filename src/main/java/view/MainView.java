package view;

import interface_adapter.ViewSwitchController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in.LoggedInViewModelListener;

import javax.swing.*;
import java.awt.*;

/**
 * The main view of the app, shown after the user logs in.
 * The top bar holds the title and the button to the user view;
 * the content panel in the middle holds the search features.
 */
public class MainView extends JPanel implements LoggedInViewModelListener {

    private static final float TITLE_FONT_SCALE = 1.5f;
    private static final int PADDING = 16;

    private final LoggedInViewModel loggedInViewModel;
    private final JLabel username = new JLabel();
    private final JButton userButton = new JButton("User");
    private final JPanel contentPanel = new JPanel();

    private ViewSwitchController viewSwitchController;

    public MainView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addListener(this);

        final JLabel title = new JLabel("Astronomy Tracker");
        title.setFont(title.getFont().deriveFont(Font.BOLD, title.getFont().getSize() * TITLE_FONT_SCALE));

        final JPanel userArea = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userArea.add(username);
        userArea.add(userButton);

        final JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        topBar.add(title, BorderLayout.WEST);
        topBar.add(userArea, BorderLayout.EAST);

        // Search features (Use Cases 4 and 5) are added to this panel.
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        final JLabel placeholder = new JLabel("Search features will appear here.");
        placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(placeholder);

        userButton.addActionListener(evt -> viewSwitchController.switchTo(LoggedInViewModel.USER_VIEW_NAME));

        this.setLayout(new BorderLayout());
        this.add(topBar, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
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
