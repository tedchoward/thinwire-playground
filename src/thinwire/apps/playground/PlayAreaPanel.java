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
import thinwire.ui.style.FX;
import thinwire.ui.layout.SplitLayout;
import thinwire.ui.layout.TableLayout;

/**
 * @author Joshua J. Gertzen
 */
class PlayAreaPanel extends Panel {    
    PlayAreaPanel(final PlayTabSheet parent, Tree tree) {
        setScroll(ScrollType.AS_NEEDED);
        getStyle().getBackground().setColor(PlayTabSheet.BACKGROUND);
        
        tree.addPropertyChangeListener(Tree.Item.PROPERTY_ITEM_SELECTED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                setLayout(null);
                getChildren().clear();
                
                Object o = ((Tree.Item)ev.getSource()).getUserObject();
                
                if (o instanceof Widget) {
                    parent.setVisibleComponentEditor(true);
                    Widget w = (Widget)o;
                    
                    if (w.getType() == RadioButton.class) {
                        int cnt = 3;
                        int width = w.getDefaultWidth();
                        int height = w.getDefaultHeight();
                        int x = getInnerWidth() / 2 - width / 2;
                        int y = getInnerHeight() / 2 - (height * 3) / 2;
                        RadioButton.Group rbg = new RadioButton.Group();
                        
                        for (int i = 0; i < cnt; i++) {
                            RadioButton rb = (RadioButton)w.newInstance();
                            rb.setUserObject(w);
                            rb.setText(rb.getText() + " " + (i + 1));
                            if (cnt == 0) rb.setChecked(true);
                            rbg.add(rb);
                            rb.setSize(width, height);
                            if (x >= 0 && y >= 0) rb.setPosition(x, y);
                            y += height;
                            rb.getStyle().getFX().setVisibleChange(FX.Type.SMOOTH);
                            getChildren().add(rb);
                            rb.getStyle().getFX().setVisibleChange(FX.Type.NONE);
                        }
                    } else {
                        Component comp = w.newInstance();
                        comp.setUserObject(w);
                        comp.setSize(w.getDefaultWidth(), w.getDefaultHeight());
                        int x = getInnerWidth() / 2 - comp.getWidth() / 2;
                        int y = getInnerHeight() / 2 - comp.getHeight() / 2;
                        if (x >= 0 && y >= 0) comp.setPosition(x, y);
                        comp.getStyle().getFX().setVisibleChange(FX.Type.SMOOTH);
                        getChildren().add(comp);
                        comp.getStyle().getFX().setVisibleChange(FX.Type.NONE);
                    }
                } else if (o instanceof Example) {
                    parent.setVisibleComponentEditor(false);
                    Component comp = ((Example)o).getExample();
                    comp.getStyle().getFX().setVisibleChange(FX.Type.SMOOTH);
                    setLayout(new TableLayout(new double[][]{{0},{0}}, 10));
                    getChildren().add(comp);
                    comp.getStyle().getFX().setVisibleChange(FX.Type.NONE);
                }
            }
        });
    }
}
