package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Cass extends InteractiveTileObject {
    public Cass(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
