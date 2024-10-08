package Services;

import Constants.ConstraintsConstants;
import Constants.AlertsConstants;
import Constants.TextConstants;

public class MusicValidationService implements IMusicValidationService {
    public static String errorMessage = null;

    public boolean validateString(String text) {
        if (isEmptyString(text)) {
            setErrorMessage(AlertsConstants.EMPTY_STRING_MESSAGE);
            return false;
        } else if (isTooLongString(text)) {
            setErrorMessage(AlertsConstants.TOO_LONG_STRING_MESSAGE);
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
            setErrorMessage(AlertsConstants.NULL_INSTRUMENT);
            return false;
        }
        return true;
    }

    private boolean isNullInstrument(String instrument) {
        return instrument == null;
    }

    private void setErrorMessage(String message) {
        errorMessage = message;
    }
}
