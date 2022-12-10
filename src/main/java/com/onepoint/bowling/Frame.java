package com.onepoint.bowling;

import com.onepoint.bowling.enums.ScoreIndicators;
import com.onepoint.bowling.exceptions.NotValidFrameException;


/**
 * This class is a representation of a frame ( a list of two shots or less)
 */
public class Frame {

    private final static String NOT_PLAYED = "0";
    private final static int MAXIMAL_FRAME_SIZE = 2;

    private final String firstShot;
    private final String secondShot;

    public Frame (final String frame) throws NotValidFrameException {
        checkIsFrameValid(frame);
        this.firstShot = extractFirstShot(frame);
        this.secondShot = extractSecondShot(frame);
    }

    /**
     * @return true if a frame is a spare
     */
    public boolean isSpare() {
        return ScoreIndicators.SPARE.indicator.equals(this.secondShot);
    }

    /**
     * @return true if a frame is a strike
     */
    public boolean isStrike() {
        return ScoreIndicators.STRIKE.indicator.equals(this.firstShot);
    }

    /**
     * @return frame score
     */
    public int computeFrameScore(){
        if(isStrike()) return 10;
        if(isSpare()) return 10;
        else return calculate();
    }

    /**
     * @return score of first shot
     */
    public int getFirstShotScore(){
        if(isStrike()) return 10;
        else if (ScoreIndicators.MISS.indicator.equals(this.firstShot)) return 0;
        else return Integer.parseInt(this.firstShot);
    }

    private String extractFirstShot(String frame) {
        return String.valueOf(frame.charAt(0));
    }

    private String extractSecondShot(final String frame) {
        if (ScoreIndicators.STRIKE.indicator.equals(this.firstShot)) {
            return  NOT_PLAYED; }
        else if (frame.length()<2 || ScoreIndicators.MISS.indicator.equals(String.valueOf(frame.charAt(1)))) {
            return "0";}
        else {
            return String.valueOf(frame.charAt(1));
        }
    }

    private int calculate() {
        final int firstShotScore = ScoreIndicators.MISS.indicator.equals(this.firstShot)?  0:Integer.parseInt(this.firstShot);
        final int secondShotScore = ScoreIndicators.MISS.indicator.equals(this.secondShot)?  0:Integer.parseInt(this.secondShot);
        return firstShotScore + secondShotScore;
    }

    private void checkIsFrameValid(final String frame) throws NotValidFrameException {
        checkThatAppropriateNumberOfShotsWerePlayed(frame);
        checkThatFrameHasValidPattern(frame);
        checkThatScoreIsAtMostTen(frame);
    }

    private void checkThatFrameHasValidPattern(final String frame) throws NotValidFrameException {
        checkThatFrameSymbolsAreValid(frame);
    }

    private void checkThatFrameSymbolsAreValid(final String frame) throws NotValidFrameException {
        final String numbersSequence = "0123456789";
        boolean condition = false;
        for (int i =0; i<frame.length(); i++){
            String pickedChar = String.valueOf(frame.charAt(i));
            if (!ScoreIndicators.STRIKE.indicator.equalsIgnoreCase(pickedChar)
                    && (!ScoreIndicators.SPARE.indicator.equals(pickedChar)
                    && (!ScoreIndicators.MISS.indicator.equals(pickedChar)
                    && !numbersSequence.contains(pickedChar)))) {
                condition = true;
                break;
            }
        }
        if (frame.length()>1 && ScoreIndicators.STRIKE.indicator.equals(String.valueOf(frame.charAt(1)))) condition = true;
        if (condition) throw new NotValidFrameException("Bad Frame Symbols !");
    }


    private void checkThatAppropriateNumberOfShotsWerePlayed(final String frame) throws NotValidFrameException {
        checkThatFrameIsNotEmpty(frame);
        checkThatShotsAreAtMostTwo(frame);
        checkThatSecondShotIsNotPlayedWhenStrike(frame);
    }

    private void checkThatFrameIsNotEmpty(final String frame) throws NotValidFrameException {
        final boolean condition = frame.length()==0;
        if (condition) throw new NotValidFrameException("Empty Frame !");
    }

    private void checkThatSecondShotIsNotPlayedWhenStrike(final String frame) throws NotValidFrameException {
        final String firstShot = String.valueOf(frame.charAt(0));
        final boolean condition = ScoreIndicators.STRIKE.indicator.equals(firstShot) && frame.length()!=1;
        if (condition) throw new NotValidFrameException("Second shot must not be played if Strike !");
    }

    private void checkThatScoreIsAtMostTen(final String frame) throws NotValidFrameException {
        int score;
        String firstShot = ScoreIndicators.MISS.indicator.equals(String.valueOf(frame.charAt(0)))?"0":String.valueOf(frame.charAt(0));
        String secondShot;

        if (frame.length() <2 ||
            ScoreIndicators.STRIKE.indicator.equals(firstShot) ||
            ScoreIndicators.MISS.indicator.equals(String.valueOf(frame.charAt(1)))) {
            secondShot = "0";
        } else {
            secondShot = String.valueOf(frame.charAt(1));
        }

        if (ScoreIndicators.STRIKE.indicator.equals(firstShot) || ScoreIndicators.SPARE.indicator.equals(secondShot)) score = 10;
        else score = Integer.parseInt(firstShot) + Integer.parseInt(secondShot);
        final boolean condition = score>10;
        if(condition) throw new NotValidFrameException("Incorrect score for frame !");
    }

    private void checkThatShotsAreAtMostTwo(final String frame) throws NotValidFrameException {
        final boolean condition = frame.length()>MAXIMAL_FRAME_SIZE;
        if (condition) throw new NotValidFrameException("Bad Frame Size !");
    }

}
