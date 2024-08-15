package Services;

import Constants.ConstraintsConstants;
import Constants.MessagesToUserConstants;
import Constants.TextConstants;

public class MusicValidationService implements IMusicValidationService {
    public static String errorMessage = null;

    public boolean validateString(String text) {
        if (isEmptyString(text)) {
            setErrorMessage(MessagesToUserConstants.EMPTY_STRING_MESSAGE);
            return false;
        } else if (isTooLongString(text)) {
            setErrorMessage(MessagesToUserConstants.TOO_LONG_STRING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isEmptyString(String text) {
        return text.trim().equals(TextConstants.EMPTY_STRING);
    }

    private boolean isTooLongString(String text) {
        return text.length() > ConstraintsConstants.MAX_TEXT_SIZE;
    }

    public boolean validateInstrument(String instrument) {
        if (isNullInstrument(instrument)) {
            setErrorMessage(MessagesToUserConstants.NULL_INSTRUMENT);
            return false;
        }
        return true;
    }

    private boolean isNullInstrument(String instrument) {
        return instrument == null;
    }

    public int parseBPM(String bpmString) {
        int bpm;
        try {
            bpm = parseBPMValue(bpmString);
            if (isInvalidBPM(bpm)) {
                setErrorMessage(MessagesToUserConstants.INVALID_BPM_VALUE);
                bpm = ConstraintsConstants.INVALID_BPM;
            }
        } catch (NumberFormatException exception) {
            setErrorMessage(MessagesToUserConstants.INVALID_BPM_TYPE);
            bpm = ConstraintsConstants.INVALID_BPM;
        }
        return bpm;
    }

    private int parseBPMValue(String bpmString) {
        return Integer.parseInt(bpmString);
    }

    private boolean isInvalidBPM(int bpm) {
        return bpm < ConstraintsConstants.MIN_BPM || bpm > ConstraintsConstants.MAX_BPM;
    }

    private void setErrorMessage(String message) {
        errorMessage = message;
    }
}
