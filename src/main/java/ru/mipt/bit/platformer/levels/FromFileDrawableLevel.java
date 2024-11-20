package ru.mipt.bit.platformer.levels;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.GDX.TankAIGDX;
import ru.mipt.bit.platformer.objects.GDX.TankGDX;
import ru.mipt.bit.platformer.objects.GDX.TreeGDX;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.Tank;
import ru.mipt.bit.platformer.objects.physical.TankAI;
import ru.mipt.bit.platformer.objects.physical.Tree;
import ru.mipt.bit.platformer.util.files.FileParser;

import java.util.Collection;
import java.util.Map;

public class FromFileDrawableLevel extends EmptyDrawableLevel {

    public FromFileDrawableLevel(TiledMap level,
                                 Batch batch,
                                 FileParser fileParser,
                                 String fileName,
                                 Collection<? super GameObjectAbt> destination) {
        super(level, batch);
        Map<GridPoint2, Character> objectCoordinates = fileParser.parseCoordinatesFromFile(fileName);
        CreateObjectsFromCoordinates(destination, objectCoordinates);
    }

    private void CreateObjectsFromCoordinates(Collection<? super GameObjectAbt> destination, Map<GridPoint2, Character> objectCoordinates) {
        for (GridPoint2 coordinate : objectCoordinates.keySet()) {
            Character character = objectCoordinates.get(coordinate);
            GridPoint2 correctedCoordinate = new GridPoint2(coordinate.x, height - 1 - coordinate.y);

                   if (character == TankGDX.getDrawableCharacterStatic()) {
                destination.add(  Tank.getDefaultTankFromCoordinatesAndTMTLayer  (correctedCoordinate, getGroundLayer()));

            } else if (character == TreeGDX.getDrawableCharacterStatic()) {
                destination.add(  Tree.getDefaultTreeFromCoordinatesAndTMTLayer  (correctedCoordinate, getGroundLayer()));

            } else if (character == TankAIGDX.getDrawableCharacterStatic()) {
                destination.add(TankAI.getDefaultTankAIFromCoordinatesAndTMTLayer(correctedCoordinate, getGroundLayer()));
            }
        }
    }
}
