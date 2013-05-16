package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.EnemyDefeat;

public class EnemyDefeatRequirement extends Requirement {
	public EnemyDefeat enemyDefeat;
	
	public EnemyDefeatRequirement() {
		super();
		
		this.enemyDefeat = new EnemyDefeat();
	}
	
	@Override
	public boolean checkCompleted() {
		// TODO Auto-generated method stub
		return false;
	}

}
