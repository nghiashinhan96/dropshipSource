package Application.userException;

public class StageHandlerException extends Exception {
    private String stage;
    private String message;

    public StageHandlerException(String argStage, String argMessage) {
        this.stage = argStage;
        this.message = argMessage;

    }
    @Override
    public String toString() {
        return this.message+" StageHandlerException";
    }
}
