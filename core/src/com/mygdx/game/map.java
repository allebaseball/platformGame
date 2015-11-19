package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.FileInputStream;
import java.io.IOException;

public class map {
    static final int tWidth = 40;
    static final int tHeight = 40;
    static Texture blue = new Texture("sprites/blueRekt.png");
    static Texture red = new Texture("sprites/redRekt.png");

    public static void mapRead (String mapName, char[][] m)
    {

        try {
            FileInputStream fileInput = new FileInputStream(mapName);
            int i=0,j=0,r;
            while((r = fileInput.read())!= -1){
                if(r == 13) {
                    j++;
                    i = 0;
                }
                else if(r != 10){
                    m[i][j] = (char) r;
                    i++;
                }
            }
            fileInput.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void mapDraw (SpriteBatch batch, char[][] m) {


        for(int j=0; j < 6; j++) {
            for(int i=0; i < 40; i++) {
                switch(m[i][j]) {
                    case '0':
                        batch.draw(blue,i*tWidth,(5-j)*tHeight);
                        break;
                    case '1':
                        batch.draw(red,i*tWidth, (5-j)*tHeight);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
