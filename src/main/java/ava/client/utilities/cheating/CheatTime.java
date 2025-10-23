package ava.client.utilities.cheating;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Represents a class that holds cheat time information with an exact time or time delta.
 * This is used to simulate time advancements for testing purposes in non-production environments.
 *
 * This class is only intended for use in non-production environments.
 */
public class CheatTime {

    /**
     * The exact cheat time.
     */
    private LocalDateTime cheatTimeExact;

    /**
     * The cheat time delta as a duration.
     */
    private Duration cheatTimeDelta;

    /**
     * Initializes a new instance of the CheatTime class.
     *
     * @param cheatTimeExact An optional exact cheat time.
     * @param cheatTimeDelta An optional cheat time delta as a duration.
     */
    public CheatTime(LocalDateTime cheatTimeExact, Duration cheatTimeDelta) {
        this.cheatTimeExact = cheatTimeExact;
        this.cheatTimeDelta = cheatTimeDelta;
    }

    /**
     * Gets the exact cheat time.
     *
     * @return The exact cheat time, or null if not set.
     */
    public LocalDateTime getCheatTimeExact() {
        return cheatTimeExact;
    }

    /**
     * Sets the exact cheat time.
     *
     * @param cheatTimeExact The exact cheat time to set.
     */
    public void setCheatTimeExact(LocalDateTime cheatTimeExact) {
        this.cheatTimeExact = cheatTimeExact;
    }

    /**
     * Gets the cheat time delta as a duration.
     *
     * @return The cheat time delta, or null if not set.
     */
    public Duration getCheatTimeDelta() {
        return cheatTimeDelta;
    }

    /**
     * Sets the cheat time delta as a duration.
     *
     * @param cheatTimeDelta The cheat time delta to set.
     */
    public void setCheatTimeDelta(Duration cheatTimeDelta) {
        this.cheatTimeDelta = cheatTimeDelta;
    }
}
