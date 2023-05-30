module Core {
    exports dk.sdu.mmmi.cbse.main;
    requires java.desktop;
    requires Common;
    requires com.badlogic.gdx;
    requires spring.context;
    requires spring.beans;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
    opens dk.sdu.mmmi.cbse.main to spring.core;
}
