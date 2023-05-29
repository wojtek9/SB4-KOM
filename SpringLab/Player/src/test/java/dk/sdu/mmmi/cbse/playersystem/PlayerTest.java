package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerTest {

    @Test
    public void playerPosition() {
        PlayerPlugin playerPlugin = new PlayerPlugin();
        World world = new World();
        GameData gameData = new GameData();
        Entity player = playerPlugin.addPlayerShip();

        playerPlugin.start(gameData, world);

        world.addEntity(player);

        MovingPart movingPart = player.getPart(MovingPart.class);

        PositionPart ppart = player.getPart(PositionPart.class);
        float x = ppart.getX();
        System.out.println("x1: " + x);
        float y = ppart.getY();
        System.out.println("y1: " + y);

        movingPart.setUp(true);

        for(int i= 0;i<100; i++){
            movingPart.update(player);
        }
        movingPart.setUp(false);
        movingPart.update(player);

        float xtest = ppart.getX();
        System.out.println("xtest: " + xtest);
        float ytest = ppart.getY();
        System.out.println("ytest: " + ytest);
        assertEquals(x , xtest,0.1);
        assertFalse(y== ytest);
    }
}
