package easv_2nd_term_exam.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ValidationUtility {

    // Regular Expressions for validation
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String DANISH_ADDRESS_REGEX = "[\\p{L}\\s\\d]+,\\s*\\d{3,4}\\s*\\p{L}\\s*\\p{L}+";
    private static final String NAME_REGEX = "[a-zA-Z\\s]+";

    // Username, Password, DeviceName, InstallationName can use this
    public static boolean isNotEmpty(TextField field) {
        return field.getText() != null && !field.getText().trim().isEmpty();
    }


    // Email validation
    public static boolean isValidEmail(TextField field) {
        return field.getText().toUpperCase().matches(EMAIL_REGEX);
    }

    // Full name validation
    public static boolean isValidName(TextField field) {
        return field.getText().matches(NAME_REGEX);
    }

    // Danish address validation
    public static boolean isValidDanishAddress(TextField field) {
        return field.getText().matches(DANISH_ADDRESS_REGEX);
    }

    // Check if ComboBox is not empty
    public static <T> boolean isComboBoxNotEmpty(ComboBox<T> comboBox) {
        return comboBox.getValue() != null;
    }

    // Check if TextArea is not empty
    public static boolean isTextAreaNotEmpty(TextArea textArea) {
        return !textArea.getText().trim().isEmpty();
    }

}
