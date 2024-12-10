package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.util.HealthBarSetting;

public class ShowHealthCommand implements Command {
    @Override
    public void execute() {
        HealthBarSetting.showHealthBar = !HealthBarSetting.showHealthBar;
    }
}
