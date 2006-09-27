/*
 *                        ThinWire(TM) Playground Demo
 *                 Copyright (C) 2006 Custom Credit Systems
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Users wishing to use this demo in proprietary products which are not 
 * themselves to be released under the GNU Public License should contact Custom
 * Credit Systems for a license to do so.
 * 
 *               Custom Credit Systems, Richardson, TX 75081, USA.
 *                          http://www.thinwire.com
 */
package thinwire.apps.playground;

import thinwire.ui.*;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.style.Color;

/**
 * @author Joshua J. Gertzen
 */
class AboutTabSheet extends TabSheet {    
    AboutTabSheet() {
        super("About this Tutorial");
        getStyle().getBackground().setColor(Color.INDIANRED);        
        getStyle().getFont().setBold(true);
        getChildren().add(new WebBrowser(Main.RES_PATH + "About.html"));
        
        addPropertyChangeListener(Main.SIZE_ARY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                int width = getInnerWidth() - Main.GAP * 2;
                int height = getInnerHeight() - Main.GAP * 2;
                if (width >= 0 && height >= 0) getChildren().get(0).setBounds(Main.GAP, Main.GAP, width, height);            
            }
        });
    }
}
