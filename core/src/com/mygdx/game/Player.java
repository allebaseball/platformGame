package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Player {
    int x = 150, y = 120;
    Texture img = new Texture("sprites/testChar2.png");

    public void RightMove () {
        x += 5;
    }

    public void LeftMove () {
        x -= 5;
    }
}
