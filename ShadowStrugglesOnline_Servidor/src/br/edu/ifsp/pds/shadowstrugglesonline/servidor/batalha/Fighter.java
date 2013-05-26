package br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha;

public class Fighter implements Card {
    private int health;
    private int damage;
    private float speed;
    private int range;
    private int attackDelay;
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getAttackDelay() {
		return attackDelay;
	}
	public void setAttackDelay(int attackDelay) {
		this.attackDelay = attackDelay;
	}

}
