package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;

import at.compus02.swd.ss2022.repository.AssetRepository;
import at.compus02.swd.ss2022.repository.AssetRepository.TextureType;

public class Player extends MovingGameObject {
    private boolean berserkerModeActive;

    public Player() {
        super();
        Texture texture = AssetRepository.getInstance().getTexture(TextureType.PLAYER);
        setSprite(new Sprite(texture));
        setBerserkerModeActive(false);
    }

    public boolean isBerserkerModeActive() {
        return this.berserkerModeActive;
    }

    public boolean getBerserkerModeActive() {
        return this.berserkerModeActive;
    }

    public void setBerserkerModeActive(boolean berserkerModeActive) {
        this.berserkerModeActive = berserkerModeActive;
        if (this.berserkerModeActive == false) {
            this.setParticleEffect(null);
        } else {
            ParticleEffect particleEffect = new ParticleEffect();
            particleEffect.load(Gdx.files.internal("flames.particles"), Gdx.files.internal(""));
            particleEffect.getEmitters().first().setPosition(this.getSprite().getX(), this.getSprite().getY());
            particleEffect.start();
            this.setParticleEffect(particleEffect);
        }
    }
}
