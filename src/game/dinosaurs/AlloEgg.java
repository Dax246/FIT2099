package game.dinosaurs;

public class AlloEgg extends Egg{

    public AlloEgg(){
        super("allosaur egg", 'e', true);
        super.turnsUntilHatch = 10;
    }
}
