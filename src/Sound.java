package src;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;

public class Sound {

    public static void main(String[] args) {
        // List of sound file paths (relative paths)
        List<String> sounds = Arrays.asList(
            "res/get out.wav",
            "res/boom.wav",
            "res/meow.wav",
            "res/prowler.wav",
            "res/wow.wav",
            "res/rizz.wav",
            "res/brainrot.wav"
        );

        while (true) {
            Collections.shuffle(sounds);
            for (String sound : sounds) {
                playSound(sound);
            }
        }
    }

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            while (!clip.isRunning()) Thread.sleep(10);
            while (clip.isRunning()) Thread.sleep(10);
            clip.close();
        } catch (Exception e) {
            System.err.println("Failed to play: " + filePath);
            e.printStackTrace();
        }
    }
}
