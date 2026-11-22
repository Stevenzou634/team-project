package app;

import data_access.FileUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewSwitchController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.LoginView;
import view.MainView;
import view.SignupView;
import view.UserView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * Builds the application by creating and wiring together all views and use cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new UserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final FileUserDataAccessObject userDataAccessObject =
            new FileUserDataAccessObject("users.csv", userFactory);

    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private LoggedInViewModel loggedInViewModel;
    private MainView mainView;
    private UserView userView;
    private SignupViewModel signupViewModel;
    private SignupView signupView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(centered(loginView), LoginViewModel.VIEW_NAME);
        return this;
    }

    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(centered(signupView), SignupViewModel.VIEW_NAME);
        return this;
    }

    public AppBuilder addMainView() {
        loggedInViewModel = new LoggedInViewModel();
        mainView = new MainView(loggedInViewModel);
        // The main view fills the whole window, so it is not centered.
        cardPanel.add(mainView, LoggedInViewModel.VIEW_NAME);
        return this;
    }

    /**
     * Adds the user view. Must be called after addMainView(), since both share the LoggedInViewModel.
     * @return this builder
     */
    public AppBuilder addUserView() {
        userView = new UserView(loggedInViewModel);
        cardPanel.add(centered(userView), LoggedInViewModel.USER_VIEW_NAME);
        return this;
    }

    /**
     * Connects the navigation buttons (e.g. Sign up, User, Back) to the view manager.
     * @return this builder
     */
    public AppBuilder addNavigation() {
        final ViewSwitchController viewSwitchController = new ViewSwitchController(viewManagerModel);
        loginView.setViewSwitchController(viewSwitchController);
        mainView.setViewSwitchController(viewSwitchController);
        userView.setViewSwitchController(viewSwitchController);
        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary =
                new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor =
                new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary =
                new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        final SignupInputBoundary signupInteractor =
                new SignupInteractor(userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController signupController = new SignupController(signupInteractor);
        signupView.setSignupController(signupController);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary =
                new LogoutPresenter(viewManagerModel, loggedInViewModel, loginViewModel);
        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        userView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Wraps a view so it keeps its natural size and stays centered
     * when the window is resized, instead of stretching across the screen.
     */
    private static JPanel centered(JComponent view) {
        final JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(view);
        return wrapper;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Astronomy Tracker");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        viewManagerModel.setState(SignupViewModel.VIEW_NAME);
        viewManagerModel.notifyViewChanged();

        return application;
    }
}
