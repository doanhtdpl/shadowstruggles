package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * A specific stage for the {@link BattleScreen}, providing access to a list of
 * Actors in order to deal mainly with resizing and manipulation of 2D objects.
 */
public class MyStage extends Stage {

	private Array<Actor> currentActors;

	public MyStage(float width, float height, boolean stretch, SpriteBatch batch) {
		super(width, height, stretch, batch);
		currentActors = new Array<Actor>();
	}

	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		currentActors.add(actor);
	}

	// TODO: verificar e documentar diferen�a entre addActor e insertActor
	public void insertActor(Actor actor) {
		super.addActor(actor);
	}

	public void removeActor(Actor actor) {
		actor.remove();
		currentActors.removeValue(actor, true);
	}

	public Array<Actor> getCurrentActors() {
		return currentActors;
	}

}
