package MusicManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements IMusicPlayer {

    public void playMusic(String musicString) {
        startMusicThread(musicString);
    }

    private void startMusicThread(String musicString) {
        new Thread(createMusicRunnable(musicString)).start();
    }

    public boolean downloadMusic(String musicString, String filename) {
        try {
            saveMusicToFile(musicString, filename);
            return true;
        } catch (IOException e) {
            handleIOException(e);
            return false;
        }
    }

    private void saveMusicToFile(String musicString, String filename) throws IOException {
        File file = new File(filename + ".mid");
        org.jfugue.pattern.Pattern pattern = new org.jfugue.pattern.Pattern();
        pattern.add(musicString);
        org.jfugue.midi.MidiFileManager.savePatternToMidi(pattern, file);
    }

    private void handleIOException(IOException e) {
        e.printStackTrace();
    }

    private Runnable createMusicRunnable(String musicString) {
        return () -> {
            Player player = new Player();
            Pattern pattern = new Pattern(musicString);
            player.play(pattern);
        };
    }
}