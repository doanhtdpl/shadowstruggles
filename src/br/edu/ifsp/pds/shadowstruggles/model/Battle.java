package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.utils.Array;

public class Battle {
	
	private BattlePlatform platform;
	private ShadowStruggles game;
	
	private int prevTime;
	private float timeElapsed;
	private float timeDelay;
	private float time;
	private boolean inicializado = false;
	private Player player1;
	private Player player2;
	private boolean isInCampaign;
	private Map map;
	
	
	
	public Battle(ShadowStruggles game, Player player1, Player player2, boolean isInCampaign) {
		this.platform = new BattlePlatform(player1.getDeck(), player2.getDeck(), new Map(""), 
				new DefaultRules(game.getManager().getSettings()));
		this.game=game;
		this.map=platform.getMap();
		this.player1=player1;
		this.player2=player2;
		this.isInCampaign=isInCampaign;
		start();
	}
	
	public void update(float delta){
		this.time+= delta;
		if (this.platform.getRules().gameState()
				.equals(DefaultRules.ENEMY_VICTORY)) {
			end();
		} else if (this.platform.getRules().gameState()
				.equals(DefaultRules.PLAYER_VICTORY)) {
			
		}	

		this.timeDelay -= delta;		
		
		if (prevTime != (int) timeElapsed) {
			player1.energyChange(1);
			player2.energyChange(1);
		}
		this.prevTime = (int) timeElapsed;
		this.timeElapsed += delta;		
		Array<Card> cardsinField = new Array<Card>();		
		cardsinField.addAll(player1.getCardsInField());
		cardsinField.addAll(player2.getCardsInField());
		for (Card card : cardsinField) {			
			if (card.getClass().equals(Fighter.class))
				((Fighter)card).act(delta);
			else if (card.getClass().equals(Effect.class))
				((Effect) (card)).act(delta);
			else if (card.getClass().equals(Trap.class))
				((Trap) (card)).act(delta);
		}
	}
	
	public void start(){
		timeElapsed=0;
		timeDelay=0;	
		this.player1.getDeck().shuffle();
		this.player2.getDeck().shuffle();	
		inicializado=true;
	}
	
	public void end(){
		
	}
	public Map getMap() {
		return map;
	}
}
