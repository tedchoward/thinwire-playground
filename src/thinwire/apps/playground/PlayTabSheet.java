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
import thinwire.ui.event.ItemChangeEvent;
import thinwire.ui.event.ItemChangeListener;
import thinwire.ui.layout.SplitLayout;
import thinwire.ui.style.Color;

/**
 * @author Joshua J. Gertzen
 */
class PlayTabSheet extends TabSheet {
    static final Color BACKGROUND = Color.LIGHTSTEELBLUE;
    static final Color SUBTAB_BACKGROUND = Color.SILVER;
    
    static void addColumn(GridBox gb, String name) { addColumn(gb, name, true); }
    static void addColumn(GridBox gb, String name, boolean visible) {        
        GridBox.Column gbc = new GridBox.Column();
        gbc.setName(name);
        if (!visible) gbc.setVisible(false);
        gb.getColumns().add(gbc);
    }    
    
    PlayTabSheet(Tree tree) {        
        super("Playground");
        getStyle().getBackground().setColor(BACKGROUND);        
        getStyle().getFont().setBold(true);
        PlayAreaPanel panel = new PlayAreaPanel(tree);
        getChildren().add(panel);
        getChildren().add(initPlayTabFolder(panel));
        new SplitLayout(this, SplitLayout.SplitType.HORIZONTAL, .50);
    }   
    
    Container initPlayTabFolder(final PlayAreaPanel panel) {
        final TabFolder tf = new TabFolder();
        tf.getStyle().getBackground().setColor(SUBTAB_BACKGROUND);
        tf.getChildren().add(new PropertyTabSheet(panel, false));
        tf.getChildren().add(new PropertyTabSheet(panel, true));
        EventTabSheet ets = new EventTabSheet(panel);
        tf.getChildren().add(ets);
        tf.getChildren().add(new CodeTabSheet(tf, panel, ets));

        panel.addItemChangeListener(new ItemChangeListener() {
            public void itemChange(ItemChangeEvent ev) {
                if (!panel.getChildren().isEmpty()) tf.setCurrentIndex(0);                
            }
        });

        return tf;
    }
}
