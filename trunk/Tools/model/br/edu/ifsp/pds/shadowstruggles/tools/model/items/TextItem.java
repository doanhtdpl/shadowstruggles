package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

public class TextItem extends Item {
	public String text;
	
	public TextItem() {
		super();
		
		this.text = "";
	}
	
	public TextItem(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, String text) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		
		this.text = text;
	}
}
