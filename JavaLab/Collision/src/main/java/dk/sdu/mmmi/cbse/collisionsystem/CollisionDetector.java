package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity other : world.getEntities()) {
                // float distance = player.getPart(PositionPart.class)-enemy.getShapeX()
                PositionPart playerPosition = entity.getPart(PositionPart.class);
                PositionPart enemyPosition = other.getPart(PositionPart.class);
                float dx = playerPosition.getX() - enemyPosition.getX();
                float dy = playerPosition.getY() - enemyPosition.getY();
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                if (distance < entity.getRadius() + other.getRadius()) {
                    world.removeEntity(other);
                }

            }
        }
    }
}
