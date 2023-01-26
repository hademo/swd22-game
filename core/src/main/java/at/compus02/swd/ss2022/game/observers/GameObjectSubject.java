package at.compus02.swd.ss2022.game.observers;

public interface GameObjectSubject {
    public void registerObserver(GameObjectObserver observer);

    public void unregisterObserver(GameObjectObserver observer);

    public void notifyGameObjectObservers();
}
