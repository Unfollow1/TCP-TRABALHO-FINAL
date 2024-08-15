package GUI;

public class UserInputs {
    private final String textInput;
    private final String initialInstrument = "Harpsichord";
    private final int initialBPM = 80;

    public UserInputs(String textInput) {
        this.textInput = textInput;
    }

    public String getTextInput() {
        return textInput;
    }

    public String getInitialInstrument() {
        return initialInstrument;
    }

    public int getInitialBPM() {
        return initialBPM;
    }
}
