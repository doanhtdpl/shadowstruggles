package br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha;

public class EffectInGame extends Effect implements CardInGame {
    private boolean isActivated;

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

}
