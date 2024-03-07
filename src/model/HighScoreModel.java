package model;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HighScoreModel implements Serializable {
    private List<HSEntry> highScoreLogs;
    private String filenameForLogs = "high-scores.dat";

    public HighScoreModel() {
        highScoreLogs = deserializeHSLogs();
    }

    public List<HSEntry> getHighScoreLogs() {
        return highScoreLogs;
    }

    public void addHighScoreLog(String name, int score) {
        HSEntry entry = new HSEntry(name, score);
        highScoreLogs.add(entry);
        sortHighScoreLogs();
        try {
            serializeHSLogs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void sortHighScoreLogs() {
        int n = highScoreLogs.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (highScoreLogs.get(j).getScore() < highScoreLogs.get(j + 1).getScore()) {
                    Collections.swap(highScoreLogs, j, j + 1);
                }
            }
        }
    }

    public void serializeHSLogs() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filenameForLogs, false);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(highScoreLogs);
        objectOut.close();
        fileOut.close();
    }

    public List<HSEntry> deserializeHSLogs() {
        try {
            File file = new File(filenameForLogs);
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(filenameForLogs);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                @SuppressWarnings("unchecked")
                List<HSEntry> logs = (List<HSEntry>) objectIn.readObject();
                objectIn.close();
                fileIn.close();
                return logs;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }
}

