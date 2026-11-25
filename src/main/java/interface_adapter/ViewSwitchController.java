package interface_adapter;

/**
 * Switches the visible view, e.g. when the user clicks a navigation button.
 * Navigation has no business logic, so it does not go through a use case.
 */
public class ViewSwitchController {

    private final ViewManagerModel viewManagerModel;

    public ViewSwitchController(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Shows the view with the given name.
     * @param viewName the name the view was registered with in the AppBuilder
     */
    public void switchTo(String viewName) {
        viewManagerModel.setState(viewName);
        viewManagerModel.notifyViewChanged();
    }
}
