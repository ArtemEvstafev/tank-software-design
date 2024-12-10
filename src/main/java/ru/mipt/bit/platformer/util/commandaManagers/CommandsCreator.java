package ru.mipt.bit.platformer.util.commandaManagers;

import ru.mipt.bit.platformer.commands.Command;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class CommandsCreator {
    public static Queue<Command> createCommands(CommandsGenerator... commandsGenerators) {
        Queue<Command> commands = new ArrayDeque<>();
        for (CommandsGenerator commandsGenerator : commandsGenerators) {
            commands.addAll(commandsGenerator.generateCommands());
        }
        return commands;
    }
}
