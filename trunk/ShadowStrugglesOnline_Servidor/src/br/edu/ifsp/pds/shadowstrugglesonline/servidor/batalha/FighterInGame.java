package br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha;

public class FighterInGame extends Fighter implements CardInGame {
    private int currentHealth;
    private int currentDamage;
    private float currentSpeed;
    private float currentAttackDelay;
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public int getCurrentDamage() {
		return currentDamage;
	}
	public void setCurrentDamage(int currentDamage) {
		this.currentDamage = currentDamage;
	}
	public float getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	public float getCurrentAttackDelay() {
		return currentAttackDelay;
	}
	public void setCurrentAttackDelay(float currentAttackDelay) {
		this.currentAttackDelay = currentAttackDelay;
	}

}
