package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.List;
import java.util.ServiceLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import static java.util.stream.Collectors.toList;

@Configuration
public class SpringConfig {

    @Bean
    public List<IEntityProcessingService> entityProcessors() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    @Bean
    public List<IPostEntityProcessingService> postEntityProcessors() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    @Bean
    public List<IGamePluginService> gamePlugins() {

        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    @Bean
    public Game game() {
        return new Game(gamePlugins(),entityProcessors(),postEntityProcessors());
    }
}
