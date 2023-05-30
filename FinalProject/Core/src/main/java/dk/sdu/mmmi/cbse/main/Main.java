package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {


		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids game");
		config.setWindowSizeLimits(800,600,800,600);

		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(SpringConfig.class);
		new Lwjgl3Application(spring.getBean(Game.class), config);
	}
}
