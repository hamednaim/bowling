import Utils.RollBuilder;
import com.onepoint.bowling.Game;
import com.onepoint.bowling.Roll;
import com.onepoint.bowling.exceptions.NotValidRollException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void computeScore() throws NotValidRollException {
        // 1st test
        Roll roll = RollBuilder.buildRoll("X X X X X X X X X X X X");
        Game game = new Game(roll);
        final int result = game.calculateScore();
        Assertions.assertEquals(300,result);

        // 2nd test
        Roll roll2 = RollBuilder.buildRoll("9- 9- 9- 9- 9- 9- 9- 9- 9- 9/ 5");
        Game game2 = new Game(roll2);
        final int result2 = game2.calculateScore();
        Assertions.assertEquals(96,result2);

        // 3rd test
        Roll roll3 = RollBuilder.buildRoll("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-");
        Game game3 = new Game(roll3);
        final int result3 = game3.calculateScore();
        Assertions.assertEquals(90,result3);

        // 4th test
        Roll roll4 = RollBuilder.buildRoll("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5");
        Game game4 = new Game(roll4);
        final int result4 = game4.calculateScore();
        Assertions.assertEquals(150,result4);
    }
}
