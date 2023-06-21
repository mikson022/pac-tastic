package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.util.Objects;

public class SoundModel {
    private String soundName;
    private boolean isSoundPlaying;
    public SoundModel(String soundName){
        this.soundName = soundName;
    }
    public void playSound() {
        if (!isSoundPlaying) {
            isSoundPlaying = true;
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/" + soundName)));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        isSoundPlaying = false;
                        clip.close();
                    }
                });
                clip.start();
            } catch (Exception ex) {
                ex.printStackTrace();
                isSoundPlaying = false;
            }
        }
    }
}
