package menu.exception;

public class ErrorHandler {
    public static void handleUserError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void handleSystemError(Exception e) {
        System.out.println("[ERROR][SYSTEM] " + e.getMessage());
    }
}
