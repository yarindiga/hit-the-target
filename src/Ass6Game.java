import Interfaces.LevelInformation;
import Levels.*;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;
public class Ass6Game {

    public static void main(String[] args) {
        List<LevelInformation> list = new ArrayList<>();
        GUI gui = new GUI("bar", 800, 600);
        DirectHit directHit = new DirectHit();
        WideEazy wideEazy = new WideEazy();
        Green3 green3 = new Green3();
        FinalFour finalFour = new FinalFour();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                list.add(directHit);
            } else if (args[i].equals("2")) {
                list.add(wideEazy);
            } else if (args[i].equals("3")) {
                list.add(green3);
            } else if (args[i].equals("4")) {
                list.add(finalFour);
            }
        }
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(list);
    }
}
