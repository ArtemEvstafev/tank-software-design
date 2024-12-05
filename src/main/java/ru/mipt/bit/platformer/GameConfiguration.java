package ru.mipt.bit.platformer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mipt.bit.platformer.keys.*;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.util.gameLoaders.FromFileGameLoader;
import ru.mipt.bit.platformer.util.gameLoaders.GameLoader;
import ru.mipt.bit.platformer.util.gameLoaders.RandomGeneratedGameLoader;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Collection;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.SPACE;


@Configuration
@ComponentScan
public class GameConfiguration {
    @Bean
    public GameLoader gameLoader() {
        return new RandomGeneratedGameLoader();
//        return new FromFileGameLoader();
    }

    @Bean
    public Batch batch(GameLoader gameLoader) {
        return gameLoader.getBatch();
    }

    @Bean
    public DrawableLevel level(GameLoader gameLoader) {
        return gameLoader.getLevel();
    }

    @Bean
    public Collection<GameObjectAbt> allObjects(GameLoader gameLoader) {
        return gameLoader.getObjects();
    }

    @Bean
    public Key[] keys(Collection<GameObjectAbt> allObjects) {
        return new Key[]{
                new MovementKey
                        (
                                allObjects,
                                new int[]{UP, W},
                                Direction.UP
                        ),
                new MovementKey
                        (
                                allObjects,
                                new int[]{DOWN, S},
                                Direction.DOWN
                        ),
                new MovementKey
                        (
                                allObjects,
                                new int[]{LEFT, A},
                                Direction.LEFT
                        ),
                new MovementKey
                        (
                                allObjects,
                                new int[]{RIGHT, D},
                                Direction.RIGHT
                        ),
                new HealthToggleKey
                        (
                                new int[]{L}
                        ),
                new ShootKey
                        (
                                new int[]{SPACE},
                                allObjects
                        )
        };
    }
}