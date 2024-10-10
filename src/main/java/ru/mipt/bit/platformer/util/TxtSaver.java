package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Drawable;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtSaver implements FileSaver {

    private List<Character> field;
    private final DrawableLevel level;
    private final Collection<? extends Drawable> drawables;

    public TxtSaver(DrawableLevel level, Collection<? extends Drawable> drawables) {
        this.level = level;
        this.drawables = drawables;
    }

    @Override
    public void saveToFile() {
        initField(level);
        drawObjects(level, drawables);
        try {
            saveFieldToFile(level, "src/main/res/level.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawObjects(DrawableLevel level, Collection<? extends Drawable> drawables) {
        for (Drawable drawable : drawables) {
            GridPoint2 coordinates = drawable.getCoordinates();
            if (drawable instanceof Tank) {
                field.set(coordinates.x + level.getWidth() * coordinates.y, 'X');
            } else if (drawable instanceof Tree) {
                field.set(coordinates.x + level.getWidth() * coordinates.y, 'T');
            }
        }
    }

    private void initField(DrawableLevel level) {
        field = Stream.generate(() -> '_')
                .limit((long) level.getHeight() * level.getWidth())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void saveFieldToFile(DrawableLevel level, String fileName) throws IOException {
        File file = new File(fileName);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int y = level.getHeight() - 1; y > 0; y--) {
                String row = field.subList(y * level.getWidth(), (y + 1) * level.getWidth())
                                  .stream()
                                  .map(String::valueOf)
                                  .collect(Collectors.joining());
                writer.write(row);
                writer.newLine();
            }
        }
    }
}
