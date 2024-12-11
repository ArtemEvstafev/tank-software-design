package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

public class Wall extends GameObjectAbt implements GameObject {

    public Wall(GridPoint2 coordinates) {
        super(coordinates, 0f);
    }

}
