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
import thinwire.ui.layout.*;
import thinwire.ui.style.Color;

/**
 * @author Joshua J. Gertzen
 */
public class Main {
    static final int GAP = 2;
    static final String RES_PATH = "class:///" + Main.class.getName() + "/resources/";
    static final String DOC_PATH = "http://www.thinwire.com/api/thinwire/ui/";
    //static final String DOC_PATH = "doc/thinwire/ui/";
    static final String[] SIZE_ARY = new String[]{Component.PROPERTY_WIDTH, Component.PROPERTY_HEIGHT};
    
    static String getSimpleClassName(Class type) {
        String text = type.getName();
        text = text.substring(text.lastIndexOf('.') + 1);
        return text;
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        Tree tree = initTree();
        Frame f = Application.current().getFrame();
        f.setTitle("ThinWire Playground Demo");
        f.getStyle().getBackground().setColor(Color.DIMGRAY);
        f.getChildren().add(getLeftPanel(tree));
        f.getChildren().add(new MainTabFolder(tree));
        new SplitLayout(f, SplitLayout.SplitType.VERTICAL, .25);
    }
    
    public Panel getLeftPanel(final Tree tree) {
        final Panel p = new Panel();
        final Image img = new Image(RES_PATH + "PlaygroundDemoLogo.png");
        p.getChildren().add(img);
        p.getChildren().add(tree);
        
        p.addPropertyChangeListener(SIZE_ARY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                img.setSize(p.getInnerWidth(), 46);
                tree.setBounds(0, img.getHeight(), p.getInnerWidth(), p.getInnerHeight() - img.getHeight());
            }
        });
        
        return p;
    }
    
    public Tree initTree() {
        Tree tree = new Tree();
        tree.setRootItemVisible(true);
        Tree.Item root = tree.getRootItem();        
        root.setText("ThinWire Components");
        root.setImage(RES_PATH + "Tutorial.png");
        
        for (Widget w : Widget.values()) {
            Tree.Item item = new Tree.Item(w.getDisplayName(), RES_PATH + getSimpleClassName(w.getType()) + ".png");
            item.setUserObject(w);
            root.getChildren().add(item);
        }
        
        return tree;
    }
}
