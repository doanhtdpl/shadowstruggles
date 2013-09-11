package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

public class SecondaryQuest extends Requirement {
	public Quest quest;
	
	public SecondaryQuest() {
		super();
		
		this.quest = new Quest();
	}
}
