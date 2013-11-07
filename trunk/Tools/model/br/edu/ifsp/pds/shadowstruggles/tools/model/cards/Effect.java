package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;



/***
 * A representation of an effect card on the field.
 */

public class Effect extends Card {
	private float duration;
	private boolean isImmediateEffect;
	private boolean isActivated = false;
	private boolean onFighter;

	public Effect() {
	}

	public boolean isImmediateEffect() {
		return isImmediateEffect;
	}

	public void setImmediateEffect(boolean isImmediateEffect) {
		this.isImmediateEffect = isImmediateEffect;
	}

	public boolean isOnFighter() {
		return onFighter;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	
}
