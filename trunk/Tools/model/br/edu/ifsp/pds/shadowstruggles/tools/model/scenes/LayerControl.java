package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

public class LayerControl extends SceneItem {
	public String nextLayer;
	public String map;
	
	public LayerControl() {
		this.nextLayer = "";
		this.map = "";
	}
	
	public LayerControl(String nextLayer, String map) {
		this.nextLayer = nextLayer;
		this.map = map;
	}
}
