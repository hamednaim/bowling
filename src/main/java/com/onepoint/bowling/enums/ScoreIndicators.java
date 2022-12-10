package com.onepoint.bowling.enums;

/**
 * This Enum class describes used symbols entered by the user
 */
public enum ScoreIndicators {
    STRIKE("X"),
    SPARE("/"),
    MISS("-"),
    SEPARATOR("\\s+");

    public final String indicator;

    ScoreIndicators(final String indicator) {
        this.indicator = indicator;
    }
}
