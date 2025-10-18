package org.audioeditor.audiotrack;

import it.sauronsoftware.jave.AudioAttributes;

import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Mp3 extends Audiotrack {
    private AudioAttributes audioAttributes;
    private File fileLink;

    public Mp3(String filePath) {
        checkFileFormat(filePath);
        File audioFile = new File(filePath);
        fileLink = audioFile;
        audioAttributes = new AudioAttributes();
        audioAttributes.setCodec("libmp3lame");
        audioAttributes.setBitRate(128000);
        audioAttributes.setChannels(2);
        audioAttributes.setSamplingRate(44100);
    }


    private void checkFileFormat(String path) {
        try {
            if (!Files.probeContentType(Path.of(path)).equals("audio/mpeg")) {
                throw new IllegalArgumentException("Wrong audio format");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AudioAttributes getAudioAttributes() {
        return audioAttributes;
    }

    public File getFileLink() {
        return fileLink;
    }

    @Override
    public AudioInputStream getAudioInputStream() {
        return null;
    }

    @Override
    public Audiotrack copy() {
        return null;
    }
}