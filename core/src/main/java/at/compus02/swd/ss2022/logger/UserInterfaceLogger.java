package at.compus02.swd.ss2022.logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserInterfaceLogger implements Logger {
    private final BitmapFont font;
    private float positionX;
    private float positionY;
    private String message = "";

    public UserInterfaceLogger(Color color, float positionX, float positionY) {
        font = new BitmapFont();
        font.setColor(color);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    @Override
    public void log(String message) {
        this.message = message;
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, message, getPositionX(), getPositionY());
    }
}
