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

import java.util.ArrayList;
import java.util.List;

import thinwire.ui.*;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.layout.SplitLayout;
import thinwire.ui.layout.TableLayout;

/**
 * @author Joshua J. Gertzen
 */
class PlayAreaPanel extends Panel {
    Dialog d1;
    Dialog d2;
    
    PlayAreaPanel(final PlayTabSheet parent, Tree tree) {
        setScrollType(ScrollType.AS_NEEDED);
        getStyle().getBackground().setColor(PlayTabSheet.BACKGROUND);
        
        final PropertyChangeListener switchToFrame = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                Frame f = Application.current().getFrame();
                d1.removePropertyChangeListener(this);
                d2.removePropertyChangeListener(this);
                d1.setVisible(false);
                d2.setVisible(false);
                Component left = d1.getChildren().remove(0);
                Component right = d2.getChildren().remove(0);
                Tree.Item item = (Tree.Item)d1.getUserObject();
                d1.setUserObject(null);
                d2.setUserObject(null);
                d1.setLayout(null);
                d2.setLayout(null);
                d1 = d2 = null;
                
                f.getChildren().add(left);
                f.getChildren().add(right);
                f.setLayout(new SplitLayout(.25, true));
                item.setText("Switch to Dialog");
            }
        };
        
        tree.addPropertyChangeListener(Tree.Item.PROPERTY_ITEM_SELECTED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (ev.getNewValue() == Boolean.FALSE) return;
                setLayout(null);
                getChildren().clear();
                
                Object o = ((Tree.Item)ev.getSource()).getUserObject();
                
                if (o instanceof Widget) {
                    parent.setVisibleComponentEditor(true, false);
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
                            getChildren().add(rb);
                        }
                    } else {
                        Component comp = w.newInstance();
                        comp.setUserObject(w);
                        comp.setSize(w.getDefaultWidth(), w.getDefaultHeight());
                        int x = getInnerWidth() / 2 - comp.getWidth() / 2;
                        int y = getInnerHeight() / 2 - comp.getHeight() / 2;
                        if (x >= 0 && y >= 0) comp.setPosition(x, y);
                        getChildren().add(comp);
                    }
                } else if (o instanceof Example) {
                    Example e = (Example) o;
                    parent.setVisibleComponentEditor(false, e.hasCommands());
                    Component comp = e.getExample();
                    setLayout(new TableLayout(new double[][]{{0},{0}}, 10));
                    getChildren().add(comp);
                } else if (((Tree.Item)ev.getSource()).getText().indexOf("Dialog") > 0) {
                    Frame f = Application.current().getFrame();
                    f.setLayout(null);
                    List<Component> lst = new ArrayList<Component>(f.getChildren());
                    f.getChildren().clear();

                    d1 = new Dialog(f.getTitle() + " Tree");
                    d1.setLayout(new TableLayout(new double[][]{{0},{0}}));
                    d1.setUserObject(ev.getSource());
                    d1.setResizeAllowed(true);
                    d1.setModal(false);
                    d1.setBounds(25, 25, 250, 500);
                    d1.getChildren().add(lst.get(0));
                    d1.setVisible(true);
                    d1.addPropertyChangeListener(Dialog.PROPERTY_VISIBLE, switchToFrame);

                    d2 = new Dialog(f.getTitle() + " Content");
                    d2.setLayout(new TableLayout(new double[][]{{0},{0}}));
                    d2.setUserObject(ev.getSource());
                    d2.setResizeAllowed(true);
                    d2.setModal(false);
                    d2.setBounds(d1.getX() + d1.getWidth() + 10, 25, 400, 500);
                    d2.getChildren().add(lst.get(1));
                    d2.setVisible(true);
                    d2.addPropertyChangeListener(Dialog.PROPERTY_VISIBLE, switchToFrame);
                    
                    ((Tree.Item)ev.getSource()).setText("Switch to Frame");
                } else if (((Tree.Item)ev.getSource()).getText().indexOf("Frame") > 0) {
                    switchToFrame.propertyChange(
                            new PropertyChangeEvent(Dialog.PROPERTY_VISIBLE, true, false, Application.current().getFrame().getDialogs().get(0)));
                }
            }
        });
    }
}
