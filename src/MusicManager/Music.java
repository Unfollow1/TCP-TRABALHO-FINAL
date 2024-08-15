package MusicManager;

import Services.TextConvertionService;

public class Music implements IMusic {
    public String musicString;

    public void createMusicFromText(String rawText, int initialBpm, int initialInstrument) {
        TextConvertionService textConvertor = new TextConvertionService();
        musicString = textConvertor.convert(rawText, initialBpm, initialInstrument);
    }
}