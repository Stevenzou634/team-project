package app;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class Main {
    private static final int FONT_SIZE = 18;
    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(900, 650);
    private static final Dimension MIN_WINDOW_SIZE = new Dimension(600, 450);

    public static void main(String[] args) {
        setDefaultFont(new FontUIResource("SansSerif", Font.PLAIN, FONT_SIZE));

        final JFrame application = new AppBuilder()
                .addLoginView()
                .addSignupView()
                .addMainView()
                .addUserView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addNavigation()
                .build();

        application.setSize(DEFAULT_WINDOW_SIZE);
        application.setMinimumSize(MIN_WINDOW_SIZE);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

    /**
     * Sets the default font for all Swing components, so text is readable on large screens.
     * Must be called before any component is created.
     */
    private static void setDefaultFont(FontUIResource font) {
        final String[] keys = {
            "Label.font", "Button.font", "TextField.font", "PasswordField.font",
            "TextArea.font", "ComboBox.font", "List.font", "Table.font",
            "OptionPane.messageFont", "OptionPane.buttonFont",
        };
        for (String key : keys) {
            UIManager.put(key, font);
        }
    }
}
