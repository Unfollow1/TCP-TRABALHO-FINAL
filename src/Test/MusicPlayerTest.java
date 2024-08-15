package Test;

import Musica.MusicPlayer;
import static org.junit.Assert.*;
import org.junit.Test;

public class MusicPlayerTest {
    @Test
    public void testSuccessfulDownload() {
        MusicPlayer player = new MusicPlayer();
        String musicString = "A B C D";
        String fileName = "saveTest";

        assertTrue(player.downloadMusic(musicString, fileName));
    }
}
