package com.mygdx.game.Tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Screens.PlayScreen;

public class MyInputProcessor implements InputProcessor {
    private PlayScreen playS;

    public MyInputProcessor(PlayScreen playS) {
        this.playS = playS;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                playS.p1.setLeftMove(true);
                break;
            case Input.Keys.RIGHT:
                playS.p1.setRightMove(true);
                break;
            case Input.Keys.UP:
                playS.p1.setJump(true);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                playS.p1.resetMove();
                break;
            case Input.Keys.RIGHT:
                playS.p1.resetMove();
                break;
            case Input.Keys.UP:
                playS.p1.setJump(false);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        switch (character) {
            case 'q':
            case 'Q':
                playS.p1.switchPlayer(-1);
                break;
            case 'e':
            case 'E':
                playS.p1.switchPlayer(1);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
