package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.objects.interfaces.Shootable;
import ru.mipt.bit.platformer.util.ObjectsUpdateListener;

public class ShootCommand implements Command {

    Shootable shootable;

    public ShootCommand(Shootable shootable) {
        this.shootable = shootable;
    }

    @Override
    public void execute() {
        ObjectsUpdateListener.addObject(shootable.shoot());
    }
}
