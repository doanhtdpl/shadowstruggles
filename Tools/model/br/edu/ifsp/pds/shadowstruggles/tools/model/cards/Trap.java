package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;



/***
 * A representation of an trap card on the field.
 */

public class Trap extends Card {

	private float duration;
	private boolean hasImmediateEffect;
	private boolean isActivated;

	public Trap() {
		this.isActivated = false;
	}


	public float getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isHasImmediateEffect() {
		return hasImmediateEffect;
	}

	public void setHasImmediateEffect(boolean hasImmediateEffect) {
		this.hasImmediateEffect = hasImmediateEffect;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}



}
