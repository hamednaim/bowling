package Utils;

import com.onepoint.bowling.Roll;
import com.onepoint.bowling.exceptions.NotValidFrameException;
import com.onepoint.bowling.exceptions.NotValidRollException;

public class RollBuilder {
    public static Roll buildRoll(final String sequence) throws NotValidRollException {
        return new Roll(sequence);
    }
}
