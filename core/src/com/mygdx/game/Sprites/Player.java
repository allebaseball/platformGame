package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Team3;

public class Player extends Sprite {
    public World world;
    public Body b2body;

    public BodyDef bdef;

    private int pNum;

    public Player(World world) {
        super(new Texture("sprites/redRekt.png"));
        this.world = world;
        definePlayer();
        setBounds(0, 0, 13 / Team3.PPM, 25 / Team3.PPM);
    }

    public void update (float dt) {
        setPosition(
                b2body.getPosition().x - getWidth() / 2,
                b2body.getPosition().y - getHeight() / 2
        );
    }

    public void switchPlayer(boolean verse) {

        if(verse == true) {
            if(++pNum > 3) pNum = 1;

            setTexture(new Texture("sprites/yellRekt.png"));

        }

    }

    public void definePlayer() {
        bdef = new BodyDef();
        bdef.position.set(30 / Team3.PPM, 300 / Team3.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape rektPlayer = new PolygonShape();
        rektPlayer.setAsBox(13 / Team3.PPM, 25 / Team3.PPM);

        fdef.shape = rektPlayer;
        b2body.createFixture(fdef);
    }

}
