import Utils.RollBuilder;
import com.onepoint.bowling.exceptions.NotValidRollException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class RollTest {

    @Test
    void expectNothingWhenValidRoll(){
        assertDoesNotThrow(()-> RollBuilder.buildRoll("X X X X X X X X X X"));
        assertDoesNotThrow(()-> RollBuilder.buildRoll("5- 5- 9- 35 8/ X X X X X X X"));
    }

    @Test
    void expectNotValidRollExceptionWhenFramesNumberViolation(){
        assertThrowsExactly(NotValidRollException.class,()-> RollBuilder.buildRoll("X X X X X"),"Minimal frames number is not respected !" );
        assertThrowsExactly(NotValidRollException.class,()-> RollBuilder.buildRoll("X X X X X X X X X X X X X"),"Maximal frames number is not respected !" );
    }

    @Test
    void expectNotValidRollExceptionWhen11thAnd12thFramesNotLegitimate(){
        assertThrowsExactly(NotValidRollException.class,()-> RollBuilder.buildRoll("X X X X X X X X X 5- X X"),"Exceeded frames number !" );
        assertThrowsExactly(NotValidRollException.class,()-> RollBuilder.buildRoll("X X X X X X X X X 5- X"),"Exceeded frames number !" );
    }

}
