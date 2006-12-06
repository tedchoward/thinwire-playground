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

import thinwire.ui.Component;
import thinwire.ui.Panel;
import thinwire.ui.TextField;
import thinwire.ui.Tree;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

public class ExampleRoundedBorders extends Example {

    @Override
    Component getContent() {
        Panel container = new Panel();
        
        container.getStyle().getBackground().setColor(Color.WINDOW);
        container.getStyle().getBorder().setImage(Main.RES_PATH + "BorderSize3.png");
        container.getStyle().getBorder().setSize(3);
        container.setLayout(new TableLayout(new double[][] {{.75, .25}, {0, 25}}, 10, 5));
        
        Panel bluePanel = (Panel) new Panel().setLimit("0, 0");
        bluePanel.getStyle().getBorder().setImage(Main.RES_PATH + "BorderSize10.png");
        bluePanel.getStyle().getBorder().setSize(10);
        bluePanel.getStyle().getBackground().setColor(Color.TRANSPARENT);
        container.getChildren().add(bluePanel);
        
        Tree tree = (Tree) new Tree().setLimit("1, 0");
        tree.getStyle().getBorder().setImage(Main.RES_PATH + "BorderSize28.png");
        tree.getStyle().getBorder().setSize(28);
        
        Tree.Item root = tree.getRootItem();
        root.setText("Root");
        root.setImage(Main.RES_PATH + "Folder.png");
        tree.setRootItemVisible(true);
        
        String IMG_FILE = Main.RES_PATH + "File.png"; 
        String IMG_FOLDER = Main.RES_PATH + "Folder.png"; 

        for (int ri = 0; ri < 5; ri++) {                
            Tree.Item L1 = new Tree.Item();
            L1.setText("Level 1 > Item " + ri);
            
            if (ri == 2 || ri == 3 || ri == 5) {
                for (int i1 = 0; i1 < 4; i1++) {
                    Tree.Item L2 = new Tree.Item();
                    L2.setText("Level 2 > Item " + i1);
                    
                    if (i1 == 1 || i1 == 4) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            Tree.Item L3 = new Tree.Item();
                            L3.setText("Level 3 > Item " + i2);
                            L3.setImage(IMG_FILE);
                            L2.getChildren().add(L3);
                        }
                    }
                    
                    L2.setImage(L2.hasChildren() ? IMG_FOLDER : IMG_FILE);
                    L1.getChildren().add(L2);
                }
            }

            L1.setImage(L1.hasChildren() ? IMG_FOLDER : IMG_FILE);
            root.getChildren().add(L1);
        }
        container.getChildren().add(tree);
        
        TextField tf = (TextField) new TextField("Type in me").setLimit("0, 1, 2, 1");
        tf.getStyle().getBorder().setImage(Main.RES_PATH + "BorderSize2.png");
        tf.getStyle().getBorder().setSize(2);
        tf.getStyle().getBackground().setColor(Color.TRANSPARENT);
        container.getChildren().add(tf);
        
        
        return container;
    }

    @Override
    String getName() {
        return "Rounded Borders";
    }

}
