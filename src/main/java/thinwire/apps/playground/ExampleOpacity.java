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

import thinwire.ui.Component;
import thinwire.ui.Panel;
import thinwire.ui.TextField;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Effect;

/**
 * @author Joshua J. Gertzen
 */
public class ExampleOpacity extends Example {

    @Override
    Component getContent() {
        Panel container = new Panel();
        
        //container.getStyle().getBackground().setColor(PlayTabSheet.BACKGROUND);
        TableLayout layout = new TableLayout(new double[][] {{0, 0}, {0, 0}}, 10, 5);
        container.setLayout(layout);
        
        PropertyChangeListener opacityListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (ev.getNewValue() == Boolean.TRUE) {
                    ev.getSourceComponent().getStyle().setOpacity(100);
                } else {
                    ev.getSourceComponent().getStyle().setOpacity(50);
                }
            }
        };
        
        int cnt = layout.getColumns().size();
        for (TableLayout.Row r : layout.getRows()) {
            for (int i = 0; i < cnt; i++) {
                TextField tf = new TextField();
                tf.getStyle().setOpacity(50);
                tf.getStyle().getFX().setOpacityChange(Effect.Motion.FAST_SMOOTH);
                tf.addPropertyChangeListener(TextField.PROPERTY_FOCUS, opacityListener);
                r.set(i, tf);
            }
        }
        
        return container;
    }

    @Override
    String getName() {
        return "Effect (Opacity Change)";
    }

    @Override
    String getDescription() {
        return "<b>Click on a TextField to give it focus and raise its <font color='rgb(255,128,0)'>Opacity</font></b>";
    }

}
