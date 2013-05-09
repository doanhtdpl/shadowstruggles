package br.edu.ifsp.pds.shadowstruggles.screens.utils;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.utils.Array;

public class TransitionScreen extends BaseScreen {
	// Game game;

	private BaseScreen current;
	private BaseScreen next;

	private int currentTransitionEffect;
	private Array<TransitionEffect> transitionEffects;

	public TransitionScreen(ShadowStruggles game, BaseScreen current,
			BaseScreen next, Array<TransitionEffect> transitionEffects,
			Controller c) {
		super(game, c);
		this.current = current;
		this.next = next;
		this.transitionEffects = transitionEffects;
		this.currentTransitionEffect = 0;
	}

	@Override
	public void render(float delta) {
		if (currentTransitionEffect >= transitionEffects.size) {
			game.setScreen(next);
			return;
		}

		transitionEffects.get(currentTransitionEffect).update(delta);
		transitionEffects.get(currentTransitionEffect).render(current, next,
				delta);

		if (transitionEffects.get(currentTransitionEffect).isFinished()) {
			currentTransitionEffect++;
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
