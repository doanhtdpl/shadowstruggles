package br.edu.ifsp.pds.shadowstruggles.screens.utils;

import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

// Based on: http://blog.gemserk.com/2012/03/05/implementing-transitions-between-screens/.

public class TransitionEffect {

	protected float alpha;
	protected float duration;
	protected boolean finished;
	// returns a value between 0 and 1 representing the level of completion of the transition.
	protected float getAlpha() {
		return alpha;
	}

	public void update(float delta) {
		
		
	} 

	public void render(BaseScreen current, BaseScreen next,float delta)
	{}

	boolean isFinished() {
		return finished;
	}

	public TransitionEffect(float duration) {
		this.duration=duration;
	}
}
