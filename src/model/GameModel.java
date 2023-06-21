package model;

import object.Pacman;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class GameModel {
    private final JTable table;
    private final Pacman pacman;
    private boolean isSoundPlaying;
    public GameModel(Pacman pacman, JTable table) {
        this.pacman = pacman;
        this.table = table;
    }
    public void playSound(String soundName) {
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
    public int getRandomIntWithinRows() {
        Random random = new Random();
        return random.nextInt(table.getRowCount());
    }
    public int getRandomIntWithinColumns() {
        Random random = new Random();
        return random.nextInt(table.getColumnCount());
    }
}

