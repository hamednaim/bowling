package com.onepoint.bowling;

import java.util.List;

/**
 * This class is a representation of a game, and it's responsible for calculating total game score
 */
public class Game {
    private static final int STANDARD_FRAMES_NUMBER = 10;
    private final Roll roll;
    public Game(final Roll roll) {
        this.roll = roll;
    }

    /**
     * @return Calculates score for game
     */
    public int calculateScore() {
        int score=0;
        final List<Frame> frames = roll.extractFrames();
        for (int i =0; i<STANDARD_FRAMES_NUMBER;i++ ){
            score += frames.get(i).computeFrameScore();
            if(frames.get(i).isStrike()) score+=frames.get(i+1).computeFrameScore() + frames.get(i+2).computeFrameScore();
            if(frames.get(i).isSpare()) score+=frames.get(i+1).getFirstShotScore();
        }
        return score;
    }
}
