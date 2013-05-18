package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

public class SecondaryQuest extends Requirement {
	public Quest quest;
	
	public SecondaryQuest() {
		super();
		
		this.quest = new Quest();
	}
	
	public SecondaryQuest(String name, String description, Quest quest) {
		super(name, description);
		this.quest = quest;
	}
}
