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
import thinwire.ui.style.Background.Position;

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
        f.setLayout(new SplitLayout(.25, true));
    }
    
    public Panel getLeftPanel(final Tree tree) {
        Panel p = new Panel();
        p.setLayout(new TableLayout(new double[][]{{0},{46, 0}}));
        Label banner = new Label();
        banner.getStyle().getBackground().setImage(RES_PATH + "PlaygroundDemoLogo.png");
        banner.getStyle().getBackground().setPosition(Position.CENTER);
        p.getChildren().add(banner);
        p.getChildren().add(tree.setLimit("0, 1"));
        return p;
    }
    
    public Tree initTree() {
        Tree tree = new Tree();
        Tree.Item root = tree.getRootItem();
        
        Tree.Item tiComp = new Tree.Item("Components", RES_PATH + "Tutorial.png");
        tiComp.setExpanded(true);
        root.getChildren().add(tiComp);
        
        for (Widget w : Widget.values()) {
            Tree.Item item = new Tree.Item(w.getDisplayName(), RES_PATH + getSimpleClassName(w.getType()) + ".png");
            item.setUserObject(w);
            tiComp.getChildren().add(item);
        }

        Tree.Item tiLayout = new Tree.Item("Examples", RES_PATH + "Tutorial.png");
        tiLayout.setExpanded(true);
        root.getChildren().add(tiLayout);
        
        for (Example e : Example.getExamples()) {
            Tree.Item item = new Tree.Item(e.getName(), RES_PATH + "Tutorial.png");
            item.setUserObject(e);
            tiLayout.getChildren().add(item);
        }
        
        Tree.Item tiDialog = new Tree.Item("Switch To Dialog", RES_PATH + "Tutorial.png");
        root.getChildren().add(tiDialog);
        return tree;
    }
}
