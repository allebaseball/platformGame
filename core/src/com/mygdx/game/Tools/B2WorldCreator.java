package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Sprites.Cass;
import com.mygdx.game.Sprites.Gem;
import com.mygdx.game.Team3;

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();

        FixtureDef fdef = new FixtureDef();
        Body body;

        //Create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(
                    (rect.getX() + rect.getWidth() / 2) / Team3.PPM,
                    (rect.getY() + rect.getHeight() / 2) / Team3.PPM
            );

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / Team3.PPM, (rect.getHeight() / 2) / Team3.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Create casses bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Cass(world, map, rect);
        }

        //Create gems bodies/fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Gem(world, map, rect);
        }
    }
}
