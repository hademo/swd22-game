package at.compus02.swd.ss2022.game.gameobjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.compus02.swd.ss2022.game.gameobjects.MovingGameObject.MoveDirection;
import at.compus02.swd.ss2022.game.gameobjects.factories.GameObjectFactory.GameObjectType;
import at.compus02.swd.ss2022.game.observers.GameObjectObserver;
import at.compus02.swd.ss2022.game.observers.GameObjectSubject;
import at.compus02.swd.ss2022.game.observers.position.PositionObserver;
import at.compus02.swd.ss2022.game.observers.position.PositionSubject;

public abstract class GameObject implements PositionSubject, GameObjectSubject {
    private Sprite sprite;
    private ParticleEffect particleEffect;

    protected final List<PositionObserver> positionObservers = new ArrayList<>();
    protected final List<GameObjectObserver> gameObjectObservers = new ArrayList<>();

    public abstract void act(float delta);

    public abstract GameObjectType getGameObjectType();

    public void setPosition(float x, float y) {
        if (sprite != null) {
            sprite.setPosition(x, y);
            this.notifyPositionObservers();
        } else {
            System.out.println("GameObject Sprite is not set");
        }
    }

    public void draw(SpriteBatch batch) {
        if (sprite != null) {
            if (particleEffect != null) {
                particleEffect.draw(batch);
            }
            sprite.draw(batch);
        } else {
            System.out.println("GameObject Sprite is not set");
        }
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setColor(Color color) {
        sprite.setColor(color);
    }

    public ParticleEffect getParticleEffect() {
        return this.particleEffect;
    }

    public void setParticleEffect(ParticleEffect particleEffect) {
        this.particleEffect = particleEffect;
    }

    @Override
    public void registerObserver(PositionObserver observer) {
        positionObservers.add(observer);
        notifyPositionObserver(observer);
    }

    @Override
    public void unregisterObserver(PositionObserver observer) {
        positionObservers.remove(observer);
    }

    @Override
    public void notifyPositionObservers() {
        for (PositionObserver observer : positionObservers) {
            notifyPositionObserver(observer);
        }
    }

    private void notifyPositionObserver(PositionObserver observer) {
        observer.update(getX(), getY(), MoveDirection.NONE);
    }

    @Override
    public void registerObserver(GameObjectObserver observer) {
        gameObjectObservers.add(observer);
        observer.update(this);
    }

    @Override
    public void unregisterObserver(GameObjectObserver observer) {
        gameObjectObservers.remove(observer);
    }

    @Override
    public void notifyGameObjectObservers() {
        for (GameObjectObserver observer : gameObjectObservers) {
            observer.update(this);
        }
    }
}
