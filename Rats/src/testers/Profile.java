package testers;

/**
 * Player Profiles for the Rats game. Each profile has a name, maximum level achieved, and their highest score.
 *
 */

public class Profile {
    private String playerName;
    private int maxLevelAchieved = 0;
    private int score;

    /**
     * Create a new profile and sets their maximum level achieved and score to a default of 0.
     *
     * @param playerName The name of the player.
     */
    public Profile(String playerName) {
        this.playerName = playerName;
        this.maxLevelAchieved = 0;
        this.score = 0;
    }

    /**
     * gets the name of the profile's player.
     *
     * @return the name of the profile's player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * sets the name of the players profile
     *
     * @param playerName the name of the profile
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the max level achieved by the profile
     *
     * @return the profile's MaxLevelAchieved
     */
    public int getMaxLevelAchieved() {
        return maxLevelAchieved;
    }

    /**
     * Sets the maximum level a player has achieved.
     *
     * @param maxLevelAchieved The new highest level the player has achieved.
     */
    public void setMaxLevelAchieved(int maxLevelAchieved) {
        this.maxLevelAchieved = maxLevelAchieved;
    }

    /**
     * Gets the score attribute of the player.
     *
     * @return the score attribute of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score attribute of the player.
     *
     * @param score the new score attribute.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gives a short description of a profile object.
     *
     * @return The players name, highest achieved level and their score separated by hyphens.
     */
    public String getProfileDescription() {
        return playerName + " - " + maxLevelAchieved + " - " + score;
    }
}
