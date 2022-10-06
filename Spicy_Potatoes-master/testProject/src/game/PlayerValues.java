package game;

public class PlayerValues {
    private String name;
    private int money;
    private int difficulty;
    private String weapon;

    private int numRooms = -1;

    public PlayerValues() {
        this.money = 0;
        this.difficulty = 0;
        this.weapon = null;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int x) {
        numRooms = x;
    }

    public void incrementRoomNum() {
        numRooms++;
    }

    public void decrementRoomNum() {
        numRooms--;
    }

    public boolean isConfigured() {
        return (difficulty != 0) && (weapon != null) && (name != null) && !(name.trim().equals(""));
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
