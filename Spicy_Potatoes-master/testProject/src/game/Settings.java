package game;

public class Settings {

    private static double sceneWidth = 600;
    private static double sceneHeight = 400;

    private static double playerShipSpeed = 4.0;
    private static double playerShipHealth = 10;

    private static double playerMissileSpeed = 4.0;
    private static double playerMissileHealth = 200.0;

    private static int enemySpawnRandomness = 5;

    public Settings(double sceneWidth, double sceneHeight, double playerShipSpeed,
                    double playerShipHealth, double playerMissileSpeed,
                    double playerMissileHealth, int enemySpawnRandomness) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.playerShipSpeed = playerShipSpeed;
        this.playerShipHealth = playerShipHealth;
        this.playerMissileHealth = playerMissileHealth;
        this.playerMissileSpeed = playerMissileSpeed;
        this.enemySpawnRandomness = enemySpawnRandomness;

    }

    public static double getSceneWidth() {
        return sceneWidth;
    }

    public void setSceneWidth(double sceneWidth) {
        this.sceneWidth = sceneWidth;
    }

    public static double getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(double sceneHeight) {
        this.sceneHeight = sceneHeight;
    }

    public static double getPlayerShipSpeed() {
        return playerShipSpeed;
    }

    public void setPlayerShipSpeed(double playerShipSpeed) {
        this.playerShipSpeed = playerShipSpeed;
    }

    public static double getPlayerShipHealth() {
        return playerShipHealth;
    }

    public void setPlayerShipHealth(double playerShipHealth) {
        this.playerShipHealth = playerShipHealth;
    }

    public static double getPlayerMissileHealth() {
        return playerMissileHealth;
    }

    public void setPlayerMissileHealth(double playerMissileHealth) {
        this.playerMissileHealth = playerMissileHealth;
    }

    public static double getPlayerMissileSpeed() {
        return playerMissileSpeed;
    }

    public void setPlayerMissileSpeed(double playerMissileSpeed) {
        this.playerMissileSpeed = playerMissileSpeed;
    }

    public static int getEnemySpawnRandomness() {
        return enemySpawnRandomness;
    }

    public void setEnemySpawnRandomness(int enemySpawnRandomness) {
        this.enemySpawnRandomness = enemySpawnRandomness;
    }


}