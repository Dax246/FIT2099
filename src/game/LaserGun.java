package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see WeaponItem
 * The class for weapon that Player can shoot
 */
public class LaserGun extends WeaponItem {
    /**
     * Constructor
     */
    public LaserGun(){
        super("laser gun", 'G', 30, "shoot");
    }

}
