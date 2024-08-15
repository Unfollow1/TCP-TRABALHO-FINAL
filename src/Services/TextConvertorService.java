package Services;

import Constants.ConstraintsConstants;
import Constants.JFugueMusicConstants;
import Constants.TextConstants;
import Musica.NoteEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class TextConvertorService implements ITextConvertorService {
    private int currentOctave;
    private final HashMap<String, Integer> instrumentHashMap = new HashMap<>();

    public TextConvertorService() {
        currentOctave = 1;
        populateInstrumentHashMap();
    }

    private void populateInstrumentHashMap() {
        instrumentHashMap.put(TextConstants.AGOGO_CHARACTER, 113);
        instrumentHashMap.put(TextConstants.TUBULAR_BELLS_CHARACTER, 14);
        instrumentHashMap.put(TextConstants.PAN_FLUTE_CHARACTER, 75);
        instrumentHashMap.put(TextConstants.CHURCH_ORGAN_CHARACTER, 19);
        instrumentHashMap.put(TextConstants.HARPSICHORD_CHARACTERS, 6);
    }

    public String convert(String rawText, int initialBpm, int initialInstrument) {
        if (isInvalidText(rawText)) {
            return null;
        }

        String resultString = processText(rawText, initialBpm, initialInstrument);
        return resultString;
    }

    private boolean isInvalidText(String rawText) {
        return rawText.trim().isEmpty() || !verifyNotesExistence(rawText);
    }

    private String processText(String rawText, int initialBpm, int initialInstrument) {
        String resultString = cleansString(rawText);
        ArrayList<String> manipulationArray = setVolume(resultString.toCharArray());
        manipulationArray = setInstruments(manipulationArray, initialInstrument);
        manipulationArray = setNotesOnOctaves(manipulationArray);

        resultString = convertArrayToString(manipulationArray);
        return JFugueMusicConstants.BPM + initialBpm + TextConstants.BLANK_SPACE + resultString;
    }

    private boolean verifyNotesExistence(String rawText) {
        return Pattern.compile("[A-G]").matcher(rawText).find();
    }

    private String convertArrayToString(ArrayList<String> manipulationArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String substring : manipulationArray) {
            stringBuilder.append(substring).append(TextConstants.BLANK_SPACE);
        }
        return stringBuilder.toString();
    }

    private String cleansString(String text) {
        text = text.replaceAll("[RT]", TextConstants.GENERIC_CONSONANT);
        text = text.replaceAll("[ouIOU]", TextConstants.GENERIC_VOCAL);
        return text;
    }

    private ArrayList<String> setVolume(char[] text) {
        int currentVolume = ConstraintsConstants.MAX_VOLUME;
        ArrayList<String> resultArray = new ArrayList<>();

        for (char character : text) {
            if (character == ' ') {
                currentVolume = adjustVolume(currentVolume);
                resultArray.add(JFugueMusicConstants.VOLUME + currentVolume + JFugueMusicConstants.CLOSE_PARENTHESIS);
            } else {
                resultArray.add(String.valueOf(character));
            }
        }

        return resultArray;
    }

    private int adjustVolume(int currentVolume) {
        if (currentVolume * 2 < ConstraintsConstants.MAX_VOLUME) {
            return currentVolume * 2;
        } else {
            return ConstraintsConstants.DEFAULT_VOLUME;
        }
    }

    private ArrayList<String> setInstruments(ArrayList<String> text, int initialInstrument) {
        int currentInstrument = initialInstrument;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);

        for (String substring : text) {
            if (instrumentHashMap.containsKey(substring)) {
                currentInstrument = instrumentHashMap.get(substring);
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else if (TextConstants.DIGIT_CHARACTERS.contains(substring)) {
                currentInstrument = adjustInstrument(currentInstrument, substring);
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else {
                arrayList.add(substring);
            }
        }

        return arrayList;
    }

    private int adjustInstrument(int currentInstrument, String substring) {
        currentInstrument += Integer.parseInt(substring);
        if (currentInstrument > 127) {
            currentInstrument -= 127;
        }
        return currentInstrument;
    }

    private ArrayList<String> setNotesOnOctaves(ArrayList<String> text) {
        ArrayList<String> arrayList = new ArrayList<>();
        NoteEnum lastNote = null;

        for (String substring : text) {
            if (isVolumeOrInstrument(substring)) {
                arrayList.add(substring);
            } else if (TextConstants.NOTES_CHARACTERS.contains(substring)) {
                lastNote = processNoteCharacter(arrayList, substring);
            } else {
                lastNote = processNonNoteCharacter(arrayList, substring, lastNote);
            }
        }

        return arrayList;
    }

    private boolean isVolumeOrInstrument(String substring) {
        return substring.contains(JFugueMusicConstants.VOLUME) || substring.contains(JFugueMusicConstants.INSTRUMENT);
    }

    private NoteEnum processNoteCharacter(ArrayList<String> arrayList, String substring) {
        NoteEnum lastNote;
        lastNote = NoteEnum.valueOf(substring);
        arrayList.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
        return lastNote;
    }

    private NoteEnum processNonNoteCharacter(ArrayList<String> arrayList, String substring, NoteEnum lastNote) {
        if (TextConstants.A_TO_G_LOWERCASE.contains(substring) && lastNote != null) {
            arrayList.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
        } else if (TextConstants.INCREASE_OCTAVE_CHARACTERS.contains(substring)) {
            currentOctave = adjustOctave();
        } else {
            arrayList.add(JFugueMusicConstants.PAUSE);
        }
        return null;
    }

    private int adjustOctave() {
        if (currentOctave > ConstraintsConstants.MIN_OCTAVE) {
            return currentOctave - 1;
        } else {
            return ConstraintsConstants.MAX_OCTAVE;
        }
    }
}