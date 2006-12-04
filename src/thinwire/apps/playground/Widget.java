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

import java.util.HashMap;
import java.util.Map;

import thinwire.ui.*;

/**
 * @author Joshua J. Gertzen
 */
enum Widget {    
    LABEL(Label.class),
    IMAGE(Image.class, 250, 50),
    DIVIDER_HORIZONTAL(Divider.class, 200, 30),
    DIVIDER_VERTICAL(Divider.class, 30, 150, "Vertical"),
    BUTTON(Button.class, 90, 25),
    CHECK_BOX(CheckBox.class),
    RADIO_BUTTON(RadioButton.class),
    HYPERLINK(Hyperlink.class),
    TEXT_FIELD(TextField.class),
    TEXT_AREA(TextArea.class, 250, 150),
    DATE_BOX(DateBox.class, 250, 200),
    DROP_DOWN_DATE_BOX(DropDownDateBox.class),
    DROP_DOWN_GRID_BOX(DropDownGridBox.class),
    DROP_DOWN_GRID_BOX_EDIT(DropDownGridBox.class, -1, -1, "Edit Allowed"),
    DROP_DOWN_GRID_BOX_CHECK(DropDownGridBox.class, -1, -1, "CheckBoxes"),
    DROP_DOWN_GRID_BOX_MULTI(DropDownGridBox.class, -1, -1, "Multi-Tiered"),
    GRID_BOX(GridBox.class, 250, 150),
    GRID_BOX_CHECK(GridBox.class, 250, 150, "CheckBoxes"),
    //GRID_BOX_MULTI(GridBox.class, 250, 150, "Multi-Tiered"),
    TREE(Tree.class, 150, 250),
    MENU(Menu.class, 400, 25),
    WEB_BROWSER(WebBrowser.class, 350, 300),
    TAB_FOLDER(TabFolder.class, 350, 250),
    TAB_SHEET(TabSheet.class, 350, 250),
    PANEL(Panel.class, 350, 250),
    SLIDER_HORIZONTAL(Slider.class),
    SLIDER_VERTICAL(Slider.class, 25, 150, "Vertical"),
    PROGRESS_BAR_HORIZONTAL(ProgressBar.class),
    PROGRESS_BAR_VERTICAL(ProgressBar.class, 25, 150, "Vertical"),
    FILE_CHOOSER(FileChooser.class, 250, 25);
    
    private Class type;
    private int defaultWidth;
    private int defaultHeight;
    private String sideText;
    private Map<String, Object> defaults;
    
    Widget(Class type) { this(type, -1, -1, null); }
    Widget(Class type, int defaultWidth, int defaultHeight) { this(type, defaultWidth, defaultHeight, null); }
    Widget(Class type, int defaultWidth, int defaultHeight, String sideText) {
        this.type = type;
        this.defaultWidth = defaultWidth < 0 ? 150 : defaultWidth;
        this.defaultHeight = defaultHeight < 0 ? 25 : defaultHeight;
        this.sideText = sideText;
        this.defaults = new HashMap<String, Object>();

        Component comp = newRawInstance();
        
        for (Property prop : Property.values()) {                        
            if (prop.isValidFor(comp.getClass())) {
                defaults.put(prop.getName(), prop.getValue(comp));
            }
        }
    }
    
    private Component newRawInstance() {
        try {
            return (Component)getType().newInstance();
        } catch (Exception e) {
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            throw new RuntimeException(e);
        }        
    }
    
    Component newInstance() {
        Component comp = newRawInstance();
        
        if (comp instanceof TextComponent) {
            ((TextComponent)comp).setText(Main.getSimpleClassName(comp.getClass()) + " Text");
        } else if (comp instanceof Menu) {
            Menu menu = (Menu)comp;            
            Menu.Item root = menu.getRootItem();
            
            String IMG_MENU = Main.RES_PATH + "Menu.png"; 
            String IMG_FILE = Main.RES_PATH + "File.png"; 
            String IMG_FOLDER = Main.RES_PATH + "Folder.png"; 

            for (int ri = 0; ri < 3; ri++) {                
                Menu.Item L1 = new Menu.Item("Level 1 > Item " + ri, IMG_MENU);
                
                for (int i1 = 0; i1 < 4; i1++) {
                    Menu.Item L2 = new Menu.Item();
                    L2.setText("Level 2 > Item " + i1);
                    
                    if (i1 == 1) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            Menu.Item L3 = new Menu.Item();
                            
                            if (i2 != 2) {
                                L3.setText("Level 3 > Item " + i2);
                                L3.setImage(IMG_FILE);
                            }

                            L2.getChildren().add(L3);
                        }
                        
                        if (ri == 2) L2.setEnabled(false);
                    } else if (ri == 0 && i1 == 2) {
                        L2.setEnabled(false);
                        L2.setKeyPressCombo("Ctrl-Shift-K");
                    }
                    
                    L2.setImage(L2.hasChildren() ? IMG_FOLDER : IMG_FILE);
                    L1.getChildren().add(L2);
                }

                root.getChildren().add(L1);
            }            
        } else if (comp instanceof Tree) {
            Tree tree = (Tree)comp;            
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
        } else if (comp instanceof RangeComponent) {
            ((RangeComponent)comp).setCurrentIndex(50);
        }
        
        if (comp instanceof Image) {
            ((Image)comp).setImage(Main.RES_PATH + "PlaygroundDemoLogo.png");
        } else if (comp instanceof Hyperlink) {
            ((Hyperlink)comp).setLocation("http://www.thinwire.com");
        } else if (comp instanceof WebBrowser) {
            ((WebBrowser)comp).setLocation("http://www.thinwire.com");
        } else if (comp instanceof TabSheet) {
            TabFolder tf = (TabFolder)(comp = TAB_FOLDER.newRawInstance());
            
            for (int i = 1; i <= 3; i++) {            
                tf.getChildren().add(new TabSheet("Tab Sheet " + i));
            }
        } else if (comp instanceof TabFolder) {
            TabFolder tf = (TabFolder)comp;
            
            for (int i = 1; i <= 3; i++) {            
                tf.getChildren().add(new TabSheet("Tab Sheet " + i));
            }
        } else if (comp instanceof DropDownGridBox || comp instanceof GridBox) {
            GridBox gb;            
            int columnCount;
            
            if (comp instanceof DropDownGridBox) {
                ((DropDownGridBox)comp).setEditAllowed(sideText != null && sideText.equals("Edit Allowed"));
                gb = ((DropDownGridBox)comp).getComponent();
                columnCount = 2;
            } else {
                gb = (GridBox)comp;
                gb.setVisibleHeader(true);
                columnCount = 3;
            }
            
            if (sideText != null && sideText.equals("Multi-Tiered")) {
                populateGridBox(comp, gb, columnCount, 3, "");
                
                for (GridBox.Row row : gb.getRows()) {
                    GridBox child = new GridBox();
                    populateGridBox(comp, child, columnCount, 6, "R" + row.getIndex() + "C:");
                    row.setChild(child);
                }                
            } else {
                if (sideText != null && sideText.indexOf("CheckBoxes") >= 0) gb.setVisibleCheckBoxes(true);
                populateGridBox(comp, gb, columnCount, 15, "");
            }
        }
        
        return comp;
    }
    
    private void populateGridBox(Component comp, GridBox gb, int columnCount, int rowCount, String prefix) {
        for (int ci = 0; ci < columnCount; ci++) {
            GridBox.Column gbc = new GridBox.Column();
            if (comp instanceof GridBox) gbc.setName("Column " + ci);
            for (int i = 0; i < rowCount; i++) gbc.add(prefix + "C" + ci + ":R" + i);
            gb.getColumns().add(gbc);
        }
    }
    
    Object getDefaultValue(String propertyName) {
        return defaults.get(propertyName);
    }
    
    Class getType() {
        return type;
    }
    
    String getDisplayName() {
        String s = Main.getSimpleClassName(type);
        if (sideText != null) s += " (" + sideText + ")";
        return s;
    }

    int getDefaultWidth() {
        return defaultWidth;
    }

    int getDefaultHeight() {
        return defaultHeight;
    }
}
