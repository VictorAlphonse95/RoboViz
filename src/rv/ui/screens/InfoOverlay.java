/*
 *  Copyright 2011 RoboViz
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package rv.ui.screens;

import java.awt.Font;
import java.awt.geom.Rectangle2D;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import js.jogl.view.Viewport;
import rv.effects.EffectManager;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

public class InfoOverlay extends ScreenBase {

    private final TextRenderer tr;
    private final Rectangle2D  b;
    private String             message;

    public InfoOverlay(String message) {
        this.message = message;
        tr = new TextRenderer(new Font("Tahoma", Font.PLAIN, 24), true, true);
        b = tr.getBounds(this.message);
    }

    @Override
    public void render(GL2 gl, GLU glu, GLUT glut, Viewport vp) {
        gl.glColor4f(0, 0, 0, 0.7f);
        EffectManager.renderScreenQuad(gl);
        gl.glColor4f(1, 1, 1, 1);

        int x = (int) ((vp.w - b.getWidth()) / 2);
        int y = vp.h - (int) ((vp.h - b.getHeight()) / 2);
        tr.beginRendering(vp.w, vp.h);
        tr.setColor(0, 0, 0, 0);
        tr.draw(message, x - 1, y - 1);
        tr.setColor(1, 1, 1, 1);
        tr.draw(message, x, y);
        tr.endRendering();
    }
}