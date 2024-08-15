package UserInterface;

import Constants.MusicConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashMap;
import Constants.ConstraintsConstants;
import Constants.AlertsConstants;
import Constants.TextConstants;
import MusicManager.Music;
import MusicManager.MusicPlayer;
import Services.MusicValidationService;
import java.io.File;

public class Controller {
    @FXML
    private TextField fileInput;

    @FXML
    private TextArea textInput;


    private final Music music = new Music();
    private final MusicPlayer player = new MusicPlayer();
    private final HashMap<String, Integer> instrumentHashMap = new HashMap<>();

    @FXML
    private void OnGenerateMusicButtonClicked() {
        MusicValidationService musicValidationService = new MusicValidationService();
        Inputs inputs = new Inputs(getTextInput());

        if(validateUserInputs(musicValidationService, inputs)) {
            music.createMusicFromText(inputs.getTextInput(), inputs.getInitialBPM(), MusicConstants.HARPSICORD_VALUE);

            if(music.musicString != null){
                createSuccessAlert(AlertsConstants.SUCCESSFUL_MUSIC_CREATION);
            } else {
                createErrorAlert(AlertsConstants.UNSUCCESSFUL_MUSIC_CREATION);
            }
        } else {
            createErrorAlert(MusicValidationService.errorMessage);
        }
    }

    private boolean validateUserInputs(MusicValidationService musicValidationService, Inputs inputs) {
        return musicValidationService.validateString(inputs.getTextInput())
                && musicValidationService.validateInstrument(inputs.getInitialInstrument())
                && inputs.getInitialBPM() != ConstraintsConstants.INVALID_BPM;
    }

    @FXML
    private void OnPlayButtonClicked() {
        if(music.musicString != null){
            player.playMusic(music.musicString);
        } else {
            createErrorAlert(AlertsConstants.MUSIC_NOT_GENERATED);
        }
    }

    @FXML
    private void OnDownloadButtonClicked() {
        String fileName = getFileName();

        if(fileName.equals(TextConstants.EMPTY_STRING)) {
            fileName = ConstraintsConstants.DEFAULT_FILE_NAME;
        }

        if(music.musicString != null) {
            if(player.downloadMusic(music.musicString, fileName)) {
                createSuccessAlert(AlertsConstants.SUCCESSFUL_DOWNLOAD);
            } else {
                createErrorAlert(AlertsConstants.UNSUCCESSFUL_DOWNLOAD);
            }
        } else {
            createErrorAlert(AlertsConstants.MUSIC_NOT_GENERATED);
        }

    }

    private String getFileName() {
        String fileName = getFileInput();

        if(fileName.equals(TextConstants.EMPTY_STRING)) {
            fileName = ConstraintsConstants.DEFAULT_FILE_NAME;
        }

        return fileName;
    }

    @FXML
    private String getFileInput() {
        return fileInput.getText();
    }

    @FXML
    private String getTextInput() {
        return textInput.getText();
    }

    private void createErrorAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.ERROR);
        empty_string.setTitle(AlertsConstants.ERROR_MESSAGE);
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }

    private void createSuccessAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.CONFIRMATION);
        empty_string.setTitle(AlertsConstants.SUCCESS_MESSAGE);
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }

    @FXML
    private void chooseInputFile() {
        final Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(AlertsConstants.OPEN_TEXT_FILE);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String stringRead;
            while ((stringRead = bufferedReader.readLine()) != null)
                stringBuilder.append(stringRead).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalString = stringBuilder.toString();
        textInput.setText(finalString);
    }
}
