package Utils;

import com.onepoint.bowling.Frame;
import com.onepoint.bowling.exceptions.NotValidFrameException;

public class FrameBuilder {
    public static Frame buildFrame(final String shots) throws NotValidFrameException {
        return new Frame(shots);
    }
}
