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

import static thinwire.apps.playground.Main.GAP;
import static thinwire.apps.playground.PlayTabSheet.addColumn;

import java.util.HashMap;
import java.util.Map;

import thinwire.ui.*;
import thinwire.ui.event.*;
import thinwire.ui.style.FX;

/**
 * @author Joshua J. Gertzen
 */
class PropertyTabSheet extends TabSheet {
    private static final String COL_TYPE = "Type";
    private static final String COL_NAME = "Name";
    private static final String COL_DEFAULT_VALUE = "Default Value";
    private static final String COL_VALUE = "Value";
    private static final String COL_PROPERTY = "property";
    private static final int EDITOR_HEIGHT = 25;
    
    private MaskEditorComponent defaultEditor;
    private MaskEditorComponent editor;
    private Map<Property, MaskEditorComponent> propToEditor = new HashMap<Property, MaskEditorComponent>();
    
    PropertyTabSheet(final Panel panel, final boolean styleProperties) {
        super(styleProperties ? "Styles" : "Properties");
        final GridBox gb = initGridBox();
        gb.setPosition(GAP, GAP);
        final Divider div = new Divider();
        div.setX(GAP);
        div.setHeight(6);
        final Label lbl = new Label("Edit Property Value:");
        lbl.setAlignX(AlignX.RIGHT);
        lbl.setX(GAP);
        lbl.setHeight(EDITOR_HEIGHT);
        editor = defaultEditor = new TextField();
        editor.setVisible(false);
        editor.setHeight(EDITOR_HEIGHT);
        final Button b = new Button("Apply");
        b.setEnabled(false);
        b.setSize(90, EDITOR_HEIGHT);
        b.setStandard(true);
        
        addPropertyChangeListener(Main.SIZE_ARY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (getInnerHeight() > 20 && getInnerWidth() > 20) {
                    gb.setSize(getInnerWidth() - GAP * 2, getInnerHeight() - GAP * 2 - div.getHeight() - editor.getHeight());
                    div.setY(gb.getY() + gb.getHeight());
                    div.setWidth(gb.getWidth());
                    int y = div.getY() + div.getHeight();
                    lbl.setY(y);
                    int width = (gb.getWidth() - GAP * 4 - b.getWidth()) / 2;
                    lbl.setWidth(width);
                    editor.setPosition(lbl.getX() + lbl.getWidth() + GAP, y);
                    editor.setWidth(width);
                    b.setPosition(editor.getX() + editor.getWidth() + GAP, y);
                }
            }
        });
        
        gb.addPropertyChangeListener(GridBox.Row.PROPERTY_ROW_SELECTED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                GridBox.Row row = gb.getSelectedRow();
                editor.setVisible(false);                
                MaskEditorComponent ed;
                String value;
                
                if (row == null) {
                    ed = defaultEditor;
                    value = "";
                    b.setEnabled(false);
                } else {
                    Property prop = (Property)row.get(COL_PROPERTY);
                    ed = propToEditor.get(prop);
                    if (ed == null) propToEditor.put(prop, ed = prop.newEditor());
                    value = row.get(COL_VALUE).toString();
                    b.setEnabled(true);
                }
                
                ed.setBounds(editor.getX(), editor.getY(), editor.getWidth(), editor.getHeight());
                ed.setText(value);
                ed.setVisible(true);
                
                if (ed.getParent() == null) {
                    int index = getChildren().indexOf(b);
                    getChildren().add(index, ed);
                }
                
                editor = ed;
            }
        });
                
        b.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                GridBox.Row row = gb.getSelectedRow();
                
                if (row != null) {
                    row.set(COL_VALUE, editor.getText());
                    Property prop = (Property)row.get(COL_PROPERTY);
                    
                    if (prop != null && panel.getChildren().size() > 0) {
                        for (int i = 0, cnt = panel.getChildren().size(); i < cnt; i++) {
                            Component comp = panel.getChildren().get(i);
                            String name = prop.getName();
                            
                            if (comp instanceof RadioButton) {
                                String value = editor.getText();
                                if (i > 0 && name.equals(RadioButton.PROPERTY_CHECKED)) continue;
                                else if (name.equals(TextComponent.PROPERTY_TEXT)) value += (i + 1);
                                else if (name.equals(Component.PROPERTY_X) || name.equals(name.equals(Component.PROPERTY_Y))) {
                                    int oldValue = (Integer)prop.getValue(comp);
                                    oldValue += Integer.parseInt(value) - oldValue;
                                    value = String.valueOf(oldValue);
                                }
                                
                                prop.setValue(comp, value);
                            } else {
                                prop.setValue(comp, editor.getText());
                            }
                        }
                    }
                }
            }
        });
        
        panel.addItemChangeListener(new ItemChangeListener() {
            public void itemChange(ItemChangeEvent ev) {
                if (ev.getType() == ItemChangeEvent.Type.ADD && panel.getChildren().size() == 1) {
                    gb.getRows().clear();
                    Component comp = panel.getChildren().get(0);
                    
                    for (Property prop : Property.values()) {                        
                        if (prop.isValidFor(comp) && prop.isStyleProperty() == styleProperties) {
                            GridBox.Row row = new GridBox.Row();
                            row.add(Main.getSimpleClassName(prop.getType()).replace('$', '.'));
                            row.add(prop.getName());
                            row.add(prop.getDefaultValue(comp));
                            row.add(prop == Property.FX_VISIBLE_CHANGE ? FX.Type.NONE : prop.getValue(comp));
                            row.add(prop);
                            gb.getRows().add(row);
                        }
                    }                    
                }
            }
        });
                        
        getChildren().add(gb);
        getChildren().add(div);
        getChildren().add(lbl);
        getChildren().add(editor);
        getChildren().add(b);
    }
    
    private GridBox initGridBox() {
        GridBox gb = new GridBox();
        gb.setVisibleHeader(true);
        addColumn(gb, COL_TYPE);
        addColumn(gb, COL_NAME);        
        addColumn(gb, COL_DEFAULT_VALUE);
        addColumn(gb, COL_VALUE);
        addColumn(gb, COL_PROPERTY, false);
        return gb;
    }
}
