package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Team3;

public class Player extends Sprite {
    public World world;
    public Body b2body;

    public BodyDef bdef;

    private Texture red = new Texture("sprites/redRekt.png");
    private Texture yell = new Texture("sprites/yellRekt.png");
    private Texture green = new Texture("sprites/greenRekt.png");

    private int pNum = 1;

    private boolean leftMove = false;
    private boolean rightMove = false;
    private boolean jumpMove = false;
    private boolean first = false;

    public Player(World world) {
        super(new Texture("sprites/redRekt.png"));
        this.world = world;
        definePlayer();
        setBounds(0, 0, 26 / Team3.PPM, 50 / Team3.PPM);
    }

    public void update (float dt) {
        setPosition(
                b2body.getPosition().x - getWidth() / 2,
                b2body.getPosition().y - getHeight() / 2
        );
    }
    public void updateMotion (float dt){
        if (leftMove && b2body.getLinearVelocity().x >= -2) {
            if (first) {
                b2body.setLinearVelocity(-2f, b2body.getLinearVelocity().y);
                first = false;
            } else {
                b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
            }
        }

        if (rightMove && b2body.getLinearVelocity().x <= 2) {
            if (first) {
                b2body.setLinearVelocity(2f, b2body.getLinearVelocity().y);
                first = false;
            } else {
                b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
            }
        }

        if (jumpMove) {
            b2body.setLinearVelocity(new Vector2(b2body.getLinearVelocity().x, 0f));
            b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
        }
    }

    public void setLeftMove(boolean flag) {
        leftMove = flag;
        first = flag;
    }

    public void setRightMove(boolean flag) {
        rightMove = flag;
        first = flag;
    }

    public void setJump(boolean flag) {
        jumpMove = flag;
    }

    public void resetMove() {
        leftMove = false;
        rightMove = false;
        first = false;

        b2body.setLinearVelocity(0f, b2body.getLinearVelocity().y);
    }

    public void switchPlayer(int verse) {
        if (verse == 1)
            if (++pNum > 3) pNum = 1;
        if (verse == -1)
            if (--pNum < 1) pNum = 3;

        switch(pNum) {
            case 1:
                setTexture(red);
                break;
            case 2:
                setTexture(yell);
                break;
            case 3:
                setTexture(green);
                break;
            default:
                break;
        }

        System.out.println(pNum);
    }

    public void definePlayer() {
        bdef = new BodyDef();
        bdef.position.set(100 / Team3.PPM, 300 / Team3.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape rektPlayer = new PolygonShape();
        rektPlayer.setAsBox(13 / Team3.PPM, 25 / Team3.PPM);

        fdef.shape = rektPlayer;
        b2body.createFixture(fdef);
    }
}
