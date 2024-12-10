package ru.mipt.bit.platformer.util.commandaManagers;

import ru.mipt.bit.platformer.commands.Command;

import java.util.Queue;

public interface CommandsGenerator {
    public Queue<Command> generateCommands();
}
