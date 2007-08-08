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
  developer via email at "Joshua Gertzen" <josh@truecode.org>.
*/
package thinwire.apps.playground;

import static thinwire.apps.playground.Main.GAP;

import java.util.ArrayList;
import java.util.List;

import thinwire.apps.playground.EventTabSheet.EventDetail;
import thinwire.ui.*;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.style.Border;
import thinwire.ui.style.Font;

/**
 * @author Joshua J. Gertzen
 */
class CodeTabSheet extends TabSheet {
    static void getSourceCode(StringBuilder sb, String var, Component comp, List<EventDetail> led) {
        String tab = "    ";
        String compType = Main.getSimpleClassName(comp.getClass());
        sb.append(compType).append(' ').append(var).append(" = new ").append(compType).append('(');
        boolean textComp = comp instanceof TextComponent;
        if (textComp) sb.append('"').append(((TextComponent)comp).getText()).append('"');
        sb.append(");\r\n");
        sb.append(var).append(".setBounds(")
            .append(Property.X.getValue(comp)).append(", ")
            .append(Property.Y.getValue(comp)).append(", ")
            .append(Property.WIDTH.getValue(comp)).append(", ")
            .append(Property.HEIGHT.getValue(comp)).append(");\r\n");

        if (comp instanceof TabFolder) {
            for (TabSheet ts : ((TabFolder)comp).getChildren()) {
                sb.append(var).append(".getChildren().add(new TabSheet(");
                sb.append('"').append(ts.getText()).append('"');
                if (ts.getImage().length() > 0) sb.append(", \"").append(ts.getImage()).append('"');
                sb.append("));\r\n");
            }
        }
        
        for (Property prop : Property.values()) {                        
            if (prop.isValidFor(comp.getClass())) {
                String name = prop.getName();
                if (name == PROPERTY_X || name == PROPERTY_Y || name == PROPERTY_WIDTH || name == PROPERTY_HEIGHT) continue;
                if (textComp && name == PROPERTY_TEXT) continue;                                
                Object defaultValue = prop.getDefaultValue(comp);
                Object value = prop.getValue(comp);
                if (defaultValue == value || (defaultValue != null && defaultValue.equals(value)) || (value != null && value.equals(defaultValue))) continue;
                Class type = prop.getType();
               
                
                sb.append(var).append('.');
                
                if (prop.isStyleProperty()) {          
                    if (prop.getName().equals(Border.PROPERTY_BORDER_TYPE) && value == Border.Type.IMAGE) continue;
                    String styleGroup = Main.getSimpleClassName(prop.getObjectType());
                    String styleName = prop.getName().substring(styleGroup.length());
                    sb.append("getStyle().get").append(styleGroup).append("().set").append(styleName);
                } else {
                    sb.append("set").append(Character.toUpperCase(prop.getName().charAt(0))).append(prop.getName().substring(1));
                }
                
                sb.append('(');

                if (type == String.class) {
                    sb.append('"').append(value).append('"');
                } else if (type.isPrimitive()) {
                    sb.append(value);
                } else {
                    sb.append(Main.getSimpleClassName(type).replace('$', '.')).append('.').append(value.toString().toUpperCase());
                }
                
                sb.append(");\r\n");
            }
        }
        
        if (comp instanceof DropDownGridBox || comp instanceof GridBox) {
            sb.append("\r\n//ADD COLUMNS AND ROWS TO THE GRID, JAVA 5 MAY USE VARARG CONSTRUCTORS\r\n");
            GridBox gb;
            String gbVar;
            
            if (comp instanceof DropDownGridBox) {
                gb = ((DropDownGridBox)comp).getComponent();
                gbVar = "gb";
                sb.append("GridBox ").append(gbVar).append(" = ").append(var).append(".getComponent();\r\n");
            } else {
                gb = (GridBox)comp;
                gbVar = var;
            }
            
            final String colVar = "col";
            sb.append("GridBox.Column ").append(colVar).append(";\r\n");
            List<GridBox> genList = new ArrayList<GridBox>();
            genList.add(gb);
            int gbVarNum = 0;
                        
            while (genList.size() > 0) {
                if (gbVarNum != 0) {
                    gbVar = "gb" + gbVarNum;
                    sb.append("GridBox ").append(gbVar).append(" = ").append("new GridBox();\r\n");
                }
                
                genGridBox(sb, genList, gbVar, colVar);
                gbVarNum++;
            }
        } else if (comp instanceof Tree || comp instanceof Menu) {
            String itemClass = comp instanceof Tree ? "Tree.Item" : "Menu.Item";
            List<String> imgs = new ArrayList<String>();
            String rootVar = "root"; 
            sb.append("\r\n//ADD ITEMS TO THE HIERARCHY\r\n");
            HierarchyComponent.Item root = ((HierarchyComponent)comp).getRootItem();
            sb.append(itemClass).append(' ').append(rootVar).append(" = ").append(var).append(".getRootItem();\r\n");
            if (root.getText().length() > 0) sb.append(rootVar).append(".setText(\"").append(root.getText()).append("\");\r\n");
            if (root.getImage().length() > 0) sb.append(rootVar).append(".setImage(").append(getImageVar(imgs, root.getImage())).append(");\r\n");
            if (root instanceof Tree.Item && ((Tree.Item)root).isExpanded()) sb.append(rootVar).append(".setExpanded(true);\r\n");
            int itemNum = 1;
            if (root.hasChildren()) genBranch(itemClass, rootVar, sb, root, itemNum, imgs);
            
            for (int i = imgs.size(); --i >= 0;) {
                sb.insert(0, "String IMG" + (i + 1) + " = \"" + imgs.get(i) + "\";\r\n");
            }
        }
        
        if (!led.isEmpty()) {            
            for (EventDetail ed : led) {
                String ln = Main.getSimpleClassName(ed.listener);
                sb.append("\r\n");
                sb.append(var).append(".add").append(ln).append('(');                
                if (ed.subType != null) sb.append('"').append(ed.subType).append('"').append(", ");                            
                sb.append("new ").append(ln).append("() {\r\n");
                sb.append(tab).append("public void ").append(ed.getMethodName()).append('(').append(Main.getSimpleClassName(ed.event)).append(" ev) {\r\n");
                sb.append(tab).append(tab).append("//ADD CODE HERE TO HANDLE EVENT\r\n");
                sb.append(tab).append("}\r\n");
                sb.append("});\r\n");
            }
        }
    }
    
    private static void genGridBox(StringBuilder sb, List<GridBox> genList, String var, final String colVar) {
        GridBox gb = genList.remove(0);
        
        for (GridBox.Column col : gb.getColumns()) {
            sb.append(var).append(".getColumns().add(").append(colVar).append(" = new GridBox.Column()").append(");\r\n");
            if (!col.getName().equals("")) sb.append(colVar).append(".setName(\"").append(col.getName()).append("\");\r\n");
        }
        
        for (GridBox.Row row : gb.getRows()) {
            sb.append(var).append(".getRows().add(new GridBox.Row(new String[]{");
            
            for (Object o : row) {
                sb.append('"').append(o).append("\", ");
            }
            
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}));\r\n");
            
            if (row.getChild() != null) genList.add(row.getChild());
        }
    }
    
    private static int genBranch(String itemClass, String parent, StringBuilder sb, HierarchyComponent.Item branch, int nextItemNum, List<String> imgs) {
        for (HierarchyComponent.Item item : (List<HierarchyComponent.Item>)branch.getChildren()) {
            if (item.hasChildren()) {
                String itemVar = "item" + nextItemNum++;
                sb.append(itemClass).append(' ').append(itemVar).append(" = new ").append(itemClass).append('(');
                sb.append('"').append(item.getText()).append('"');
                if (item.getImage().length() > 0) sb.append(", ").append(getImageVar(imgs, item.getImage()));
                sb.append(");\r\n");
                if (item instanceof Tree.Item && ((Tree.Item)item).isExpanded()) sb.append(itemVar).append(".setExpanded(true);\r\n");
                sb.append(parent).append(".getChildren().add(").append(itemVar).append(");\r\n");
                nextItemNum = genBranch(itemClass, itemVar, sb, item, nextItemNum, imgs);
            } else {
                sb.append(parent).append(".getChildren().add(new ").append(itemClass).append('(');
                sb.append('"').append(item.getText()).append('"');
                if (item.getImage().length() > 0) sb.append(", ").append(getImageVar(imgs, item.getImage()));
                sb.append("));\r\n");
            }
        }
        
        return nextItemNum;
    }
    
    private static String getImageVar(List<String> imgs, String img) {
        int index = imgs.indexOf(img);
        
        if (index == -1) {
            imgs.add(img);
            index = imgs.size() - 1;
        }
        
        index++;
        return "IMG" + index;
    }
    
    private TextArea ta;
    private Panel panel;
    private EventTabSheet eventTab;
    
    CodeTabSheet(final TabFolder tf, Panel panel, EventTabSheet eventTab) {
        super("Source Code");
        this.panel = panel;
        this.eventTab = eventTab;
        
        ta = new TextArea();
        ta.getStyle().getFont().setFamily(Font.Family.MONOSPACE);
        ta.setPosition(GAP, GAP);
        
        addPropertyChangeListener(Main.SIZE_ARY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (getInnerHeight() > 20 && getInnerWidth() > 20) {
                    ta.setSize(getInnerWidth() - GAP * 2, getInnerHeight() - GAP * 2);
                }
            }
        });        
        
        tf.addPropertyChangeListener(TabFolder.PROPERTY_CURRENT_INDEX, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (((Integer)ev.getNewValue()) == tf.getChildren().indexOf(CodeTabSheet.this)) {
                    loadCode();
                }
            }
        });
        
        getChildren().add(ta);
    }
    
    void loadCode() {
        List<Component> children = panel.getChildren();
        
        if (!children.isEmpty()) {
            if (children.size() == 1 && children.get(0).getUserObject() instanceof Example) {
                Example example = (Example)children.get(0).getUserObject();
                ta.setText(example.getSourceCode());
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = 0, cnt = children.size(); i < cnt; i++) {
                    Component comp = children.get(i);
                    if (comp instanceof RadioButton && i == 0) sb.append("RadioButton.Group rbg = new RadioButton.Group();\r\n");
                    String var = i == 0 ? "comp" : "comp" + i;
                    getSourceCode(sb, var, comp, eventTab.getCheckedEventDetails());
                    if (comp instanceof RadioButton) sb.append("rbg.add(").append(var).append(");\r\n");
                }

                ta.setText(sb.toString());
            }
        }
    }
}
