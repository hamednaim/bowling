package com.onepoint.bowling;

import com.onepoint.bowling.enums.ScoreIndicators;
import com.onepoint.bowling.exceptions.NotValidFrameException;
import com.onepoint.bowling.exceptions.NotValidRollException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for representing the sequence of frames ( known as a roll) entered by the user
 */
public class Roll {

    private static final int MINIMAL_FRAMES_COUNT = 10;
    private static final int MAXIMAL_FRAMES_COUNT = 12;
    private final String sequence;

    public Roll(final String sequence) throws NotValidRollException {
        this.sequence = sequence;
        checkRollIsValid();
    }

    /**
     * @return list of frames
     */
    public List<Frame> extractFrames() {
        List<String> framesList = List.of(sequence.split(ScoreIndicators.SEPARATOR.indicator));
       return framesList.stream().map(frame-> {
            try {
                return new Frame(frame);
            } catch (NotValidFrameException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private void checkRollIsValid() throws NotValidRollException {
        checkFramesNumber();
    }

    private void checkFramesNumber() throws NotValidRollException {
        checkFramesNumberIsAboveTen();
        checkFramesNumberIsBelowTwelve();
        checkLegitimacyFor11thAnd12thFrames();

    }

    private void checkFramesNumberIsBelowTwelve() throws NotValidRollException {
        final List<Frame> frames = extractFrames();
        final boolean condition = frames.size()>MAXIMAL_FRAMES_COUNT;
        if (condition) throw new NotValidRollException("Maximal frames number is not respected !");
    }

    private void checkFramesNumberIsAboveTen() throws NotValidRollException {
        final List<Frame> frames = extractFrames();
        final boolean condition = frames.size()<MINIMAL_FRAMES_COUNT;
        if (condition) throw new NotValidRollException("Minimal frames number is not respected !");
    }

    private void checkLegitimacyFor11thAnd12thFrames() throws NotValidRollException {
        check12thFrameIsLegitimate();
        check11thFrameIsLegitimate();
    }

    private void check12thFrameIsLegitimate() throws NotValidRollException {
        final List<Frame> frames = extractFrames();
        if (frames.size()==MAXIMAL_FRAMES_COUNT){
            final boolean condition= !frames.get(MAXIMAL_FRAMES_COUNT - 3).isStrike() ;
            if (condition) throw new NotValidRollException("Exceeded frames number !");
        }
    }

    private void check11thFrameIsLegitimate() throws NotValidRollException {
        final List<Frame> frames = extractFrames();
        if (frames.size()==MAXIMAL_FRAMES_COUNT - 1){
            final boolean condition= !frames.get(MINIMAL_FRAMES_COUNT - 1).isSpare();
            if(condition) throw new NotValidRollException("Exceeded frames number !");
        }
    }

}
