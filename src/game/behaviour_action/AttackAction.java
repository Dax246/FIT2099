package game.behaviour_action;

import java.util.HashMap;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.Player;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Corpse;
import game.dinosaurs.Stegosaur;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String result;
		if (actor instanceof Allosaur && target instanceof Stegosaur) {
			result = actor + " attacks " + target + " for 20 damage.";
			target.hurt(20);
			actor.heal(20);
			if (target.isConscious()) {
				HashMap<Stegosaur, Integer> cannotAttack = ((Allosaur) actor).getCannotAttack();
				cannotAttack.put((Stegosaur) target, 20);
				((Allosaur) actor).setCannotAttack(cannotAttack);
			}
		}
		else if (actor instanceof Player) {
			Weapon weapon = actor.getWeapon();

			if (rand.nextBoolean()) {
				return actor + " misses " + target + ".";
			}

			int damage = weapon.damage();
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
		}
		else {
			throw new AssertionError("Unexpected class attacking");
		}

		if (!target.isConscious()) {
			Corpse corpse = new Corpse(1);
			map.locationOf(target).addItem(corpse);
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
