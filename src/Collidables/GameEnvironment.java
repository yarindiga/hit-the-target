package Collidables;
import Shapes.*;
import ShownObject.*;
import java.util.ArrayList;
import java.util.List;

 */
public class GameEnvironment {
    private List<Collidable> list = new ArrayList<Collidable>();

    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo cf;
        int count = 0;
        int index = -1;
        double min = 100;
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()) != null) {
                if (count == 0) {
                    min = trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()).distance(trajectory.start());
                    index = i;
                } else if (min < trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()).distance(trajectory.start())) {
                    min = trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()).distance(trajectory.start());
                    index = i;
                }
                count++;
            }
        }
        if (count == 0)
            return null;
        cf = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(list.get(index).getCollisionRectangle()), list.get(index));
        return cf;
    }
    public void removeCollid(Collidable c){
      list.remove(c);
    }
}
