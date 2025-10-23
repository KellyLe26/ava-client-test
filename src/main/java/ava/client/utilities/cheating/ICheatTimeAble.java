package ava.client.utilities.cheating;

/**
 * Defines the interface for an object that supports cheat time manipulation.
 */
public interface ICheatTimeAble {

    /**
     * Gets the cheat time.
     *
     * @return The current cheat time.
     */
    CheatTime getCheatTime();

    /**
     * Sets the cheat time.
     *
     * @param cheatTime The cheat time to set.
     */
    void setCheatTime(CheatTime cheatTime);

    /**
     * Applies the given cheat time to the object.
     *
     * @param cheatTime The CheatTime to apply.
     */
    void applyCheatTime(CheatTime cheatTime);
}