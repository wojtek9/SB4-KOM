package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);

            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(gameData, bullet);
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            updateShape(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        PositionPart ePos = e.getPart(PositionPart.class);

        float x = ePos.getX();
        float y = ePos.getY();
        float radians = ePos.getRadians();
        float speedBullet = 400;

        Entity bullet = new Bullet();
        bullet.setRadius(2);

        float bx = (float) cos(radians) * e.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * e.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speedBullet, 5));
        bullet.add(new TimerPart(1));
        updateShape(bullet);


        return bullet;
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians)*4);
        shapey[0] = (float) (y + Math.sin(radians)*4);

        shapex[1] = (float) (x + Math.cos(radians)*5);
        shapey[1] = (float) (y + Math.sin(radians)*5);

        shapex[2] = (float) (x + Math.cos(radians)*6);
        shapey[2] = (float) (y + Math.sin(radians)*6);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);

    }
}
