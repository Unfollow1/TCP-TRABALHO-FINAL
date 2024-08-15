package GUI;

public class UserInputs {
    private final String textInput;

    public UserInputs(String textInput) {
        this.textInput = textInput;
    }

    public String getTextInput() {
        return textInput;
    }

    public String getInitialInstrument() {
        String initialInstrument = "Harpsichord";
        return initialInstrument;
    }

    public int getInitialBPM() {
        int initialBPM = 80;
        return initialBPM;
    }
}
