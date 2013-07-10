package uk.ac.brighton.ab607.jelly.graphics.hud;

import uk.ac.brighton.ab607.jelly.graphics.Renderer;

public class HudText extends HudObject {
	public HudText(int x, int y, String text) {
		super(x, y, Renderer.createGraphics(text));
	}
	
	public void setText(String text) {
	    this.image = Renderer.createGraphics(text);
	}
}
