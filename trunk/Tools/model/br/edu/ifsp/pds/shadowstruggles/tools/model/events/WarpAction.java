package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

public class WarpAction extends EventAction {
	public int destinyX, destinyY;
	public String destinyMap;
	public String destinyLayer;
	public int targetId;

	public WarpAction() {
		this.destinyX = 0;
		this.destinyY = 0;
		this.destinyMap = "";
		this.destinyLayer = "";
		this.targetId = 0;
	}
}
