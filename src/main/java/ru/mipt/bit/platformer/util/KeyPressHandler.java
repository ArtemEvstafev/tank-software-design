package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.keys.Key;
import ru.mipt.bit.platformer.util.commandaManagers.CommandsGenerator;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class KeyPressHandler implements CommandsGenerator {
    private Key[] keys;

    public KeyPressHandler(Key[] keys) {
        this.keys = keys;
    }

    @Override
    public Queue<Command> generateCommands() {
        Queue<Command> commands = new ArrayDeque<>();
        for (Key k : keys) {
            if (k.isPressed())
                commands.addAll(k.makeCommands());
        }
        return commands;
    }
}
