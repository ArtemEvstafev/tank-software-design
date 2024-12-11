package ru.mipt.bit.platformer.util.commandaManagers;

import ru.mipt.bit.platformer.commands.Command;

import java.util.Queue;

public class CommandsExecuter {
    public static void executeCommands(Queue<Command> commands) {
        while (commands.peek() != null) {
            commands.poll().execute();
        }
    }
}
