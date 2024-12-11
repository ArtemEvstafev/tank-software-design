package ru.mipt.bit.platformer.util.files;

import com.badlogic.gdx.math.GridPoint2;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

import static ru.mipt.bit.platformer.util.CharToDrawableConverter.isCharExists;

public class TxtParser implements FileParser{

    @Override
    public Map<GridPoint2, Character> parseCoordinatesFromFile(String filePath) {
        Map<GridPoint2, Character> objectCoordinates = new HashMap<>();
        String line;
        int y = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    char character = line.charAt(x);
                    if (isCharExists(character)) {
                        objectCoordinates.put(new GridPoint2(x, y), character);
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectCoordinates;
    }
}