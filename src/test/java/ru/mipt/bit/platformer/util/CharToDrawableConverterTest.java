package ru.mipt.bit.platformer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharToDrawableConverterTest {

//    @Test
//    void getCharFromTree() {
//        CharToDrawableConverter converter = new CharToDrawableConverter();
//        Drawable tree = new Tree(new Texture("images/tank_blue.png"), new GridPoint2(0,0), null);
//        assertEquals('T', converter.getCharFromDrawable(tree));
//    }
//
//    @Test
//    void getCharFromTank() {
//        CharToDrawableConverter converter = new CharToDrawableConverter();
//        Drawable tank = new Tank(new Texture("images/tank_blue.png"), new GridPoint2(0,
//                0), 0.1f, 1f, 0f, null);
//        assertEquals('X', converter.getCharFromDrawable(tank));
//    }
//
//    @Test
//    void getCharFromTankAI() {
//        CharToDrawableConverter converter = new CharToDrawableConverter();
//        Drawable tankAI = new TankAI(new Texture("images/tank_blue.png"),
//                new GridPoint2(0,0),
//                0.1f,
//                1f,
//                0f,
//                null,
//                null);
//        assertEquals('A', converter.getCharFromDrawable(tankAI));
//    }

    @Test
    void isCharExists() {
        boolean res1 =  CharToDrawableConverter.isCharExists('A');
        boolean res2 =  CharToDrawableConverter.isCharExists('X');
        boolean res3 =  CharToDrawableConverter.isCharExists('T');
        assertTrue(res1 && res2 && res3);
    }
}