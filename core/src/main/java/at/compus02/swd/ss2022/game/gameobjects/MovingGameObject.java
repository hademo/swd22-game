package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.observers.position.PositionObserver;

public abstract class MovingGameObject extends GameObject {
    public enum MoveDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE,
    }

    private float movementSpeedMultiplier;
    private MoveDirection moveDirection;
    private MoveDirection moveDirectionBefore;

    public MovingGameObject() {
        this.moveDirection = MoveDirection.NONE;
        this.moveDirectionBefore = MoveDirection.NONE;
        this.movementSpeedMultiplier = 100;
    }

    public float getMovementSpeedMultiplier() {
        return this.movementSpeedMultiplier;
    }

    public void setMovementSpeedMultiplier(float movementSpeed) {
        this.movementSpeedMultiplier = movementSpeed;
    }

    public MoveDirection getMoveDirection() {
        return this.moveDirection;
    }

    public MoveDirection getMoveDirectionBefore() {
        return this.moveDirectionBefore;
    }

    public void setMoveDirection(MoveDirection moveDirection) {
        this.moveDirectionBefore = this.moveDirection;
        this.moveDirection = moveDirection;
        this.notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (PositionObserver observer : positionObservers) {
            observer.update(getX(), getY(), getMoveDirection());
        }
    }

    @Override
    public void act(float delta) {
        float movement = delta * movementSpeedMultiplier;
        switch (this.moveDirection) {
            case UP:
                this.setPosition(getX(), getY() + movement);
                break;
            case DOWN:
                this.setPosition(getX(), getY() - movement);
                break;
            case LEFT:
                this.setPosition(getX() - movement, getY());
                break;
            case RIGHT:
                this.setPosition(getX() + movement, getY());
                break;
            case NONE:
                break;
        }
    }
}
