package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.ShowHealthCommand;
import ru.mipt.bit.platformer.util.HealthBarSetting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class HealthToggleKey implements Key {

    private final int[] keys;

    public HealthToggleKey(int[] keys) {
        this.keys = keys;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public Queue<Command> makeCommands() {
        Queue<Command> commands = new ArrayDeque<>();
        commands.offer(new ShowHealthCommand());
        return commands;
    }
}
