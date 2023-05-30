import dk.sdu.mmmi.cbse.bullet.BulletPlugin;
import dk.sdu.mmmi.cbse.bullet.BulletSystem;
import dk.sdu.mmmi.cbse.common.services.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    provides IEntityProcessingService with BulletSystem;
    provides BulletSPI with BulletSystem;
    provides IGamePluginService with BulletPlugin;
}