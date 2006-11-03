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

import java.util.*;

import thinwire.ui.*;
import thinwire.ui.event.*;
import thinwire.ui.layout.SplitLayout;

/**
 * @author Joshua J. Gertzen
 */
class EventTabSheet extends TabSheet {
    static class EventDetail {
        Class listener;
        Class event;
        String subType;
        
        EventDetail(Class listener, Class event, String subType) {
            this.listener = listener;
            this.event = event;
            this.subType = subType;
        }
        
        String getMethodName() {
            if (listener == PropertyChangeListener.class) {
                return "propertyChange";
            } else if (listener == ActionListener.class) {
                return "actionPerformed";
            } else if (listener == ItemChangeListener.class) {
                return "itemChange";
            } else if (listener == KeyPressListener.class) {
                return "keyPress";
            } else {
                throw new IllegalStateException("unknown listener type");
            }
        }
    }
           
    private static final String COL_LISTENER_TYPE = "Listener Type";
    private static final String COL_EVENT_TYPE = "Event Type";
    private static final String COL_EVENT_SUB_TYPE = "Event Sub Type";
    private static final String COL_DETAIL = "detail";    
    private static final String CHECK_ROW = "[CHECK TO SPECIFY]";
    
    static GridBox.Row newRow(EventDetail ed) {
        GridBox.Row row = new GridBox.Row();
        row.add(Main.getSimpleClassName(ed.listener));
        row.add(Main.getSimpleClassName(ed.event));
        row.add(ed.subType == null ? "[N/A]" : ed.subType);
        row.add(ed);
        return row;        
    }
    
    private int countPCL;
    private int countAL;
    private int countKPL;
    private GridBox gb;
    private GridBox gbMsg;
    
    EventTabSheet(final PlayAreaPanel panel) {
        super("Events");
        
        gb = initGridBox();
        gbMsg = initMsgGridBox();
        
        panel.addItemChangeListener(new ItemChangeListener() {
            public void itemChange(ItemChangeEvent ev) {
                gb.getRows().clear();
                gbMsg.getRows().clear();                

                if (!panel.getChildren().isEmpty()) {
                    Component comp = panel.getChildren().get(0);
                    
                    if (comp instanceof ActionEventComponent) {
                        gb.getRows().add(newRow(new EventDetail(ActionListener.class, ActionEvent.class, ActionEventComponent.ACTION_CLICK)));
                        if (!(comp instanceof Menu)) gb.getRows().add(newRow(new EventDetail(ActionListener.class, ActionEvent.class, ActionEventComponent.ACTION_DOUBLE_CLICK)));
                    }                    

                    if (comp instanceof DropEventComponent) {
                        gb.getRows().add(newRow(new EventDetail(DropListener.class, DropEvent.class, null)));
                    }

                    if (comp instanceof ItemChangeEventComponent) {
                        gb.getRows().add(newRow(new EventDetail(ItemChangeListener.class, ItemChangeEvent.class, null)));
                    }
                    
                    gb.getRows().add(newRow(new EventDetail(KeyPressListener.class, KeyPressEvent.class, CHECK_ROW)));
                    
                    for (Property prop : Property.values()) {                        
                        if (prop.isValidFor(comp)) {
                            gb.getRows().add(newRow(new EventDetail(PropertyChangeListener.class, PropertyChangeEvent.class, prop.getName())));
                        }
                    }                    
                }                
            }
        });
        
        final PropertyChangeListener PCL = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                StringBuilder sb = new StringBuilder();
                sb.append(Main.getSimpleClassName(ev.getClass())).append(" ( ");
                sb.append("source=").append(Main.getSimpleClassName(ev.getSource().getClass()));
                sb.append(", propertyName=").append(ev.getPropertyName());
                sb.append(", oldValue=").append(ev.getOldValue());
                sb.append(", newValue=").append(ev.getNewValue()).append(" )");
                printEventMessage(sb.toString());
            }
        };        

        final ActionListener AL = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                StringBuilder sb = new StringBuilder();
                sb.append(Main.getSimpleClassName(ev.getClass())).append(" ( ");
                sb.append("sourceComponent=").append(Main.getSimpleClassName(ev.getSource().getClass()));
                sb.append(", action=").append(ev.getAction());
                sb.append(", source=").append(Main.getSimpleClassName(ev.getSource().getClass())).append(" )");
                printEventMessage(sb.toString());
            }
        };                
        
        final DropListener DL = new DropListener() {
            public void dropPerformed(DropEvent ev) {
                StringBuilder sb = new StringBuilder();
                sb.append(Main.getSimpleClassName(ev.getClass())).append(" ( ");
                sb.append("sourceComponent=").append(Main.getSimpleClassName(ev.getSourceComponent().getClass()));
                sb.append(", source=").append(Main.getSimpleClassName(ev.getSource().getClass()));
                sb.append(", dragComponent=").append(Main.getSimpleClassName(ev.getDragComponent().getClass()));
                sb.append(", dragObject=").append(Main.getSimpleClassName(ev.getDragObject().getClass())).append(" )");
                printEventMessage(sb.toString());
            }
        };

        final ItemChangeListener ICL = new ItemChangeListener() {
            public void itemChange(ItemChangeEvent ev) {
                StringBuilder sb = new StringBuilder();
                sb.append(Main.getSimpleClassName(ev.getClass())).append(" ( ");
                sb.append("source=").append(Main.getSimpleClassName(ev.getSource().getClass()));
                sb.append(", type=").append(ev.getType());
                sb.append(", position=").append(ev.getPosition());
                sb.append(", oldValue=").append(ev.getOldValue());
                sb.append(", newValue=").append(ev.getNewValue()).append(" )");
                printEventMessage(sb.toString());
            }
        };                
        
        final KeyPressListener KPL = new KeyPressListener() {
            public void keyPress(KeyPressEvent ev) {
                StringBuilder sb = new StringBuilder();
                sb.append(Main.getSimpleClassName(ev.getClass())).append(" ( ");
                sb.append("source=").append(Main.getSimpleClassName(ev.getSource().getClass()));
                sb.append(", keyPressCombo=").append(ev.getKeyPressCombo()).append(" )");
                printEventMessage(sb.toString());
            }
        };
        
        gb.addPropertyChangeListener(GridBox.Row.PROPERTY_ROW_CHECKED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                GridBox.Row row = (GridBox.Row)ev.getSource();
                EventDetail ed = (EventDetail)row.get(COL_DETAIL);
                String keyPressCombo = null;
                
                for (int i = 0, cnt = panel.getChildren().size(); i < cnt; i++) {
                    Component comp = panel.getChildren().get(i);
                    
                    if (ed.listener == PropertyChangeListener.class) {
                        if (ev.getNewValue() == Boolean.TRUE) {
                            comp.addPropertyChangeListener(ed.subType, PCL);
                            countPCL++;
                        } else {
                            countPCL--;
                            
                            if (countPCL <= 0) {
                                comp.removePropertyChangeListener(PCL);
                                countPCL = 0;
                            }
                        }
                    } else if (ed.listener == ActionListener.class) {
                        if (ev.getNewValue() == Boolean.TRUE) {
                            ((ActionEventComponent)comp).addActionListener(ed.subType, AL);
                            countAL++;
                        } else {
                            countAL--;
                            
                            if (countAL <= 0) {
                                ((ActionEventComponent)comp).removeActionListener(AL);
                                countAL = 0;
                            }
                        } 
                    } else if (ed.listener == DropListener.class) {
                        DropEventComponent dComp = (DropEventComponent)comp;
                        
                        if (ev.getNewValue() == Boolean.TRUE) {
                            gbMsg.addDropListener(dComp, DL);
                            dComp.addDropListener(gb, DL);
                        } else {
                            gbMsg.removeDropListener(DL);
                            dComp.removeDropListener(DL);
                        }
                    } else if (ed.listener == ItemChangeListener.class) {
                        if (ev.getNewValue() == Boolean.TRUE) {
                            ((ItemChangeEventComponent)comp).addItemChangeListener(ICL);
                        } else {                        
                            ((ItemChangeEventComponent)comp).removeItemChangeListener(ICL);
                        }                    
                    } else if (ed.listener == KeyPressListener.class) {
                        if (ed.subType.equals(CHECK_ROW)) {
                            if (ev.getNewValue() == Boolean.TRUE) {
                                if (i == 0) keyPressCombo = getKeyPressCombo();
                                
                                if (keyPressCombo != null) {                        
                                    ed.subType = keyPressCombo;
                                    row.set(COL_EVENT_SUB_TYPE, ed.subType);
                                    gb.getRows().add(row.getIndex(), newRow(new EventDetail(KeyPressListener.class, KeyPressEvent.class, CHECK_ROW)));
                                    comp.addKeyPressListener(keyPressCombo, KPL);
                                    countKPL++;
                                } else {
                                    break;
                                }
                            }
                        } else {
                            countKPL--;
                            
                            if (countKPL <= 0) {
                                comp.removeKeyPressListener(KPL);
                                countKPL = 0;
                            }
                            
                            gb.getRows().remove(row);
                        }
                    }
                }
            }
        });
        
        final Panel split = new Panel();
        split.getStyle().getBackground().setColor(PlayTabSheet.SUBTAB_BACKGROUND);
        split.setPosition(GAP, GAP);
        split.getChildren().add(gb);
        split.getChildren().add(gbMsg);
        new SplitLayout(split, SplitLayout.SplitType.HORIZONTAL, .60);
                
        addPropertyChangeListener(Main.SIZE_ARY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (getInnerHeight() > 20 && getInnerWidth() > 20) {
                    split.setSize(getInnerWidth() - GAP * 2, getInnerHeight() - GAP * 2);
                }
            }
        });
        
        getChildren().add(split);
    }
        
    private void printEventMessage(String message) {
        List<GridBox.Row> rows = gbMsg.getRows();
        while (rows.size() > 25) rows.remove(0);            
        Calendar cal = GregorianCalendar.getInstance();
        rows.add(new GridBox.Row(cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "." + cal.get(Calendar.MILLISECOND) + " - " + message));
        rows.get(rows.size() - 1).setSelected(true);
    }
        
    private static final String[] keyNames = new String[] {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z",        
        "Dash", "=", "`", "[", "]", "\\", ";", "'", 
        ",", ".", "/", "PageUp", "PageDown",
        "End", "Home", "ArrowLeft", "ArrowRight", "ArrowDown",
        "ArrowUp", "Space", "Enter", "Esc",  "Tab", "BackSpace",
        "F1", "F2", "F3", "F4", "F5", "F6", "F7",
        "F8", "F9", "F10", "F11", "F12", 
        "Del", "Ins", "Pause", "ScrollLock", "NumLock",
        "Num0", "Num1", "Num2", "Num3", "Num4", "Num5",
        "Num6", "Num7", "Num8", "Num9", "Num*", "Num+",
        "NumDash", "Num/", "Num."
    };
    
    private String getKeyPressCombo() {
        final int pad = 2;
        final int width = 125;
        final int height = 22;
        Panel p = new Panel();
        CheckBox ctrl = new CheckBox("Ctrl");
        ctrl.setBounds(pad, pad, width, height);
        CheckBox alt = new CheckBox("Alt");
        alt.setBounds(pad, ctrl.getY() + ctrl.getHeight() + pad, width, height);
        CheckBox shift = new CheckBox("Shift");
        shift.setBounds(pad, alt.getY() + alt.getHeight() + pad, width, height);
        DropDownGridBox key = new DropDownGridBox();
        key.setEditAllowed(false);
        key.setBounds(pad, shift.getY() + shift.getHeight() + pad, width, height);        
        key.getComponent().getColumns().add(new GridBox.Column((Object[])keyNames));
        
        p.getChildren().add(ctrl);
        p.getChildren().add(alt);
        p.getChildren().add(shift);
        p.getChildren().add(key);
        p.setSize(key.getX() + key.getWidth() + pad, key.getY() + key.getHeight() + pad);
        
        if (MessageBox.confirm(null, "Choose Key Combination", p, "Ok|Cancel") == 0) {        
            return KeyPressEvent.encodeKeyPressCombo(ctrl.isChecked(), alt.isChecked(), shift.isChecked(), key.getText());
        } else {
            return null;
        }
    }
    
    List<EventDetail> getCheckedEventDetails() {
        List<EventDetail> al = new ArrayList<EventDetail>();
        
        for (GridBox.Row row : gb.getCheckedRows()) {
            al.add((EventDetail)row.get(COL_DETAIL));
        }
        
        return al;
    }
    
    private GridBox initMsgGridBox() {
        GridBox gb = new GridBox();
        gb.setVisibleHeader(true);
        addColumn(gb, "Event History");
        return gb;
    }

    private GridBox initGridBox() {
        GridBox gb = new GridBox();
        gb.setVisibleHeader(true);
        gb.setVisibleCheckBoxes(true);
        addColumn(gb, COL_LISTENER_TYPE);
        addColumn(gb, COL_EVENT_TYPE);
        addColumn(gb, COL_EVENT_SUB_TYPE);
        addColumn(gb, COL_DETAIL, false);
        return gb;
    }
}
