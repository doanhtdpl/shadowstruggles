package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

public class MoneyRequirement extends Requirement {
	public int money;
	
	public MoneyRequirement() {
		super();
		
		this.money = 0;
	}
	
	public MoneyRequirement(String name, String description, int money) {
		super(name, description);
		
		this.money = money;
	}
}
