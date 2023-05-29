package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;

    public AsteroidPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {

        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {

        Entity asteroid = new Asteroid();
        float radians = (float)Math.random()*2*3.145f;
        float speed = (float) Math.random()*10f+20f;
        float x = gameData.getDisplayWidth() / 2+ 100;
        float y= gameData.getDisplayHeight() / 2 + 50;
        asteroid.add(new MovingPart(0,speed, speed,0));
        asteroid.add(new PositionPart(x,y,radians));
        asteroid.add(new LifePart(10));
        asteroid.setRadius(25);
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
}
