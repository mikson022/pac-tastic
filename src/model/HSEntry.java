package model;

public class HSEntry {
    private String name;
    private int score;

    HSEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
    int getScore() {
        return score;
    }
    public String getNameAndScore() {
        return name + " " + score;
    }
}
