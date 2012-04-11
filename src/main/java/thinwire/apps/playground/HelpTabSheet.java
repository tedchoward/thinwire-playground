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
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.style.Color;

/**
 * @author Joshua J. Gertzen
 */
class HelpTabSheet extends TabSheet {
    HelpTabSheet(final Tree tree, final TabFolder main) {
        super("Component Help");
        //getStyle().getBackground().setColor(Color.GOLD);        
        //getStyle().getFont().setBold(true);
        final WebBrowser wb = new WebBrowser(); 
        getChildren().add(wb);
        
        addPropertyChangeListener(Main.SIZE_ARY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                int width = getInnerWidth() - Main.GAP * 2;
                int height = getInnerHeight() - Main.GAP * 2;
                if (width >= 0 && height >= 0) getChildren().get(0).setBounds(Main.GAP, Main.GAP, width, height);            
            }
        });                
        
        main.addPropertyChangeListener(TabFolder.PROPERTY_CURRENT_INDEX, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (main.getChildren().get(main.getCurrentIndex()) == HelpTabSheet.this) {
                    Tree.Item item = tree.getSelectedItem();
                    
                    if (item != null && item.getUserObject() != null && item.getUserObject() instanceof Widget) {
                        Class type = ((Widget)item.getUserObject()).getType();
                        wb.setLocation(Main.DOC_PATH + Main.getSimpleClassName(type) + ".html");
                    }
                }
            }
        });
    }
}
