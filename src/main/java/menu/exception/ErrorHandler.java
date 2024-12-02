package menu.exception;

public class ErrorHandler {
    public static void handleUserError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}
