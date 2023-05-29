package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collided : world.getEntities()) {
                LifePart life = entity.getPart(LifePart.class);
                if (entity.getID().equals(collided.getID())) {
                    continue;
                }

                if (this.collides(entity, collided)) {
                    if (life.getLife() > 0) {
                        life.setLife(life.getLife() - 1);
                        life.setIsHit(true);
                        if (life.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float dx = (float) entMov.getX() - (float) entMov2.getX();
        float dy = (float) entMov.getY() - (float) entMov2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            return true;
        }
        return false;
    }
}
