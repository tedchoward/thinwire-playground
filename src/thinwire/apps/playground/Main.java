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
import thinwire.ui.layout.*;
import thinwire.ui.style.Color;
import thinwire.ui.style.Background;
import thinwire.ui.style.Font;
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
    
    public Main() {
        Tree tree = initTree();
        Frame f = Application.current().getFrame();
        f.setTitle("ThinWire Playground Demo");
        f.setLayout(new TableLayout(new double[][] {{0}, {84, 0}}));
        Panel header = new Panel();
        header.getStyle().getBackground().setImage(RES_PATH + "headerbg.gif");
        header.getStyle().getBackground().setRepeat(Background.Repeat.X);
        header.setLayout(new TableLayout(new double[][] {{70, 600, 0}, {54, 0}}));
        Label logo = new Label();
        logo.getStyle().getBackground().setImage(RES_PATH + "logo.png");
        header.getChildren().add(logo.setLimit("1, 0"));
        
        Label slogan = new Label("Open Source Ajax RIA Framework");
        slogan.setWrapText(true);
        slogan.getStyle().getFont().setColor(Color.valueOf("#FFFFFF"));
        slogan.getStyle().getFont().setFamily(Font.Family.valueOf("Trebuchet MS,Arial,Sans-serif"));
        slogan.getStyle().getFont().setBold(true);
        slogan.getStyle().getFont().setSize(11);
        header.getChildren().add(slogan.setLimit("1, 1"));
        
        f.getChildren().add(header.setLimit("0, 0"));
        
        Panel main = new Panel();
        f.getChildren().add(main.setLimit("0, 1"));
        //main.getStyle().getBackground().setColor(Color.DIMGRAY);
        main.getChildren().add(tree);
        main.getChildren().add(new MainTabFolder(tree));
        main.setLayout(new SplitLayout(.25, true, 5));
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
        String version = Application.getPlatformVersionInfo().get("productVersion");
        root.setText("ThinWire Platform v" + version);
        tree.setRootItemVisible(true);
        
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
        
        Tree.Item tiHandbook = new Tree.Item("Handbook Examples", RES_PATH + "Tutorial.png");
        root.getChildren().add(tiHandbook);
        
        for (HandbookExampleChapter chapter: HandbookExampleChapter.values()) {
        	Tree.Item tiChapter = new Tree.Item(chapter.toString(), RES_PATH + "Folder.png");
        	for (Class example : chapter.getExamples()) {
        		Tree.Item item = new Tree.Item(example.getSimpleName(), RES_PATH + "Tutorial.png");
        		item.setUserObject(example);
        		tiChapter.getChildren().add(item);
        	}
        	tiHandbook.getChildren().add(tiChapter);
        }
        
        Tree.Item tiDialog = new Tree.Item("Switch To Dialog", RES_PATH + "Tutorial.png");
        root.getChildren().add(tiDialog);
        Tree.Item tiTerminate = new Tree.Item("Terminate Session", RES_PATH + "Tutorial.png");
        root.getChildren().add(tiTerminate);
        return tree;
    }
}
