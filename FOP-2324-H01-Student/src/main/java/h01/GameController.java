package h01;

import fopbot.Robot;
import fopbot.World;
import h01.template.GameControllerBase;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link GameController} controls the game loop and the {@link Robot}s and checks the win condition.
 */
public class GameController extends GameControllerBase {

    /**
     * Creates a new {@link GameControllerBase}.
     */
    public GameController() {
        setup();
    }

    @Override
    public void checkWinCondition() {
        // TODO: H3
        boolean cleanerWon = (getContaminant1().isTurnedOff() && getContaminant2().isTurnedOff()) ||
            (Utils.getCoinAmount(0, World.getHeight()-1) >= 200);

        int amountsOfFieldsWithCoins = 0;
        for (int i=0; i<World.getWidth(); i++) for (int j=0; j<World.getHeight(); j++) if (Utils.getCoinAmount(i, j)>0)
            amountsOfFieldsWithCoins++;
        boolean contaminantsWon = amountsOfFieldsWithCoins >= (World.getWidth()*World.getHeight())*0.5;

        if (cleanerWon && contaminantsWon) {
            System.out.println("Cleaning robot won!");
            stopGame();
        }
        else if (cleanerWon) {
            System.out.println("Cleaning robot won!");
            stopGame();
        }
        else if (contaminantsWon) {
            System.out.println("Contaminants won!");
            stopGame();
        }
    }
}
