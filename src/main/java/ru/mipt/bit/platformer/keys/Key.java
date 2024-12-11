package ru.mipt.bit.platformer.keys;

import ru.mipt.bit.platformer.commands.Command;

import java.util.Queue;

public interface Key {
    boolean isPressed();
    Queue<Command> makeCommands();
}

