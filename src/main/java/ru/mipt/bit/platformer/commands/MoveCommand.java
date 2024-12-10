package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.objects.interfaces.Movable;

public class MoveCommand implements Command {

    Movable movable;
    Direction direction;

    public MoveCommand(Movable movable, Direction direction) {
        this.movable = movable;
        this.direction = direction;
    }

    @Override
    public void execute() {
        movable.setDirection(direction);
    }
}
