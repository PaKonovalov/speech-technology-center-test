package library.api.util;

public class AssertMessages {

    private static final String ERROR_DOES_NOT_MATCH_MESSAGE = " does not match the expected";

    public static String getAssertMatchMessage(String text) {
        return text + ERROR_DOES_NOT_MATCH_MESSAGE;
    }
}