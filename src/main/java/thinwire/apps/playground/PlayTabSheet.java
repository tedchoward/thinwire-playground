/*
                           ThinWire(R) Playground Demo
                 Copyright (C) 2006-2007 Custom Credit Systems

  This library is free software; you can redistribute it and/or modify it under
  the terms of the GNU Lesser General Public License as published by the Free
  Software Foundation; either version 2.1 of the License, or (at your option) any
  later version.

  This library is distributed in the hope that it will be useful, but WITHOUT ANY
  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
  PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License along
  with this library; if not, write to the Free Software Foundation, Inc., 59
  Temple Place, Suite 330, Boston, MA 02111-1307 USA

  Users interested in finding out more about the ThinWire framework should visit
  the ThinWire framework website at http://www.thinwire.com. For those interested
  in discussing the details of how this demo was built, you can contact the 
  developer via email at "Joshua Gertzen" <josh at truecode dot org>.
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
    static void addColumn(GridBox gb, String name, boolean visible) { addColumn(gb, name, visible, -1); }
    static void addColumn(GridBox gb, String name, boolean visible, int width) {        
        GridBox.Column gbc = new GridBox.Column();
        gbc.setName(name);
        if (!visible) gbc.setVisible(false);
        if (width != -1) gbc.setWidth(width);
        gb.getColumns().add(gbc);
    }    
    
    private TabFolder tfEditor;
    private CodeTabSheet cts;
    private CommandTabSheet cmdTS;
    
    PlayTabSheet(Tree tree) {        
        super("Playground");
        //getStyle().getBackground().setColor(BACKGROUND);        
        //getStyle().getFont().setBold(true);
        PlayAreaPanel panel = new PlayAreaPanel(this, tree);
        getChildren().add(panel);
        getChildren().add(tfEditor = initPlayTabFolder(panel));
        setLayout(new SplitLayout(.50));
    }   
    
    void setVisibleComponentEditor(boolean visible, boolean command) {
        for (TabSheet ts : tfEditor.getChildren()) {
            if (ts == cts || ts == cmdTS) continue;
            ts.setVisible(visible);
        }
        
        cmdTS.setVisible(command);
        cts.setVisible(true);
    }
    
    TabFolder initPlayTabFolder(final PlayAreaPanel panel) {
        final TabFolder tf = new TabFolder();
        //tf.getStyle().getBackground().setColor(SUBTAB_BACKGROUND);
        tf.getChildren().add(new PropertyTabSheet(panel, false));
        tf.getChildren().add(new PropertyTabSheet(panel, true));
        EventTabSheet ets = new EventTabSheet(panel);
        tf.getChildren().add(ets);
        tf.getChildren().add(cts = new CodeTabSheet(tf, panel, ets));
        cmdTS = new CommandTabSheet(tf, panel);
        cmdTS.setVisible(false);
        tf.getChildren().add(cmdTS);

        panel.addItemChangeListener(new ItemChangeListener() {
            public void itemChange(ItemChangeEvent ev) {
                if (tf.getChildren().indexOf(cts) == tf.getCurrentIndex()) cts.loadCode();
            }
        });

        return tf;
    }
}
