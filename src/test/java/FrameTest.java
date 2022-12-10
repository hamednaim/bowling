import Utils.FrameBuilder;
import Utils.RollBuilder;
import com.onepoint.bowling.exceptions.NotValidFrameException;
import com.onepoint.bowling.exceptions.NotValidRollException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class FrameTest {

    @Test
    void expectNothingWhenValidFrame(){
        assertDoesNotThrow(()-> FrameBuilder.buildFrame("X"));
        assertDoesNotThrow(()-> FrameBuilder.buildFrame("5/"));
        assertDoesNotThrow(()-> FrameBuilder.buildFrame("9-"));
        assertDoesNotThrow(()-> FrameBuilder.buildFrame("--"));
        assertDoesNotThrow(()-> FrameBuilder.buildFrame("-3"));
    }

    @Test
    void expectNotValidFrameExceptionWhenBadFrame(){
        assertThrowsExactly(NotValidFrameException.class,()-> FrameBuilder.buildFrame("DL"),"Bad Frame Symbols !");
        assertThrowsExactly(NotValidFrameException.class,()-> FrameBuilder.buildFrame("-X"),"Bad Frame Symbols !");
        assertThrowsExactly(NotValidFrameException.class,()-> FrameBuilder.buildFrame(""),"Empty Frame !");
        assertThrowsExactly(NotValidFrameException.class,()-> FrameBuilder.buildFrame("XXX"),"Bad Frame Size !");
        assertThrowsExactly(NotValidFrameException.class,()-> FrameBuilder.buildFrame("99"),"Incorrect score for frame !");
        assertThrowsExactly(NotValidFrameException.class,()-> FrameBuilder.buildFrame("X8"),"Second shot must not be played if Strike !");
    }
}
