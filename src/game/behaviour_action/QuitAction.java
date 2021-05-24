package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see game.Player
 * A class that will allow the user to quit the game if user chooses to
 */
public class QuitAction extends Action {

    /**
     * Quit the current version of the game
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return null to indicate to quit the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Returns string to print on menu
     * @param actor The actor performing the action.
     * @return String to print on menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Exit Game";
    }
}
