package uk.ac.brighton.ab607.jelly.graphics.hud;

import uk.ac.brighton.ab607.jelly.graphics.Renderer;

public class HudText extends HudObject {
    private boolean changed = false;
    private String text = "";
    
	public HudText(int x, int y, String text) {
		super(x, y, Renderer.createGraphics(text));
	}
	
	public void setText(String text) {
	    if (!this.text.equals(text)) {
	        this.text = text;
	        changed = true;
	    }
	}

    @Override
    public boolean hasChanged() {
        return changed;
    }

    @Override
    public void updateGraphics() {
        if (hasChanged()) {
            this.image = Renderer.createGraphics(text);  
            changed = false;
        }
    }
}
