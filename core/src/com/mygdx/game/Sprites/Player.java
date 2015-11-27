package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Team3;

public class Player extends Sprite {
    public World world;
    public Body b2body;

    public BodyDef bdef;

    public Player(World world) {
        this.world = world;
        definePlayer();
    }

    public void definePlayer() {
        bdef = new BodyDef();
        bdef.position.set(30 / Team3.PPM, 300 / Team3.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

//        CircleShape shape = new CircleShape();
//        shape.setRadius(10 / Team3.PPM);
//        fdef.shape = shape;

        PolygonShape rektPlayer = new PolygonShape();
        rektPlayer.setAsBox(13 / Team3.PPM, 25 / Team3.PPM);

        fdef.shape = rektPlayer;
        b2body.createFixture(fdef);
    }

}
