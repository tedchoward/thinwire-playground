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

import java.util.List;

import thinwire.ui.*;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;

/**
 * @author Joshua J. Gertzen
 */
class MainTabFolder extends TabFolder {
    MainTabFolder(Tree tree) {
        tree.addPropertyChangeListener(Tree.Item.PROPERTY_ITEM_SELECTED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (ev.getNewValue() == Boolean.FALSE) return;
                
                if (((Tree.Item)ev.getSource()).getUserObject() == null) {
                    setCurrentIndex(0);
                } else {
                    setCurrentIndex(1);
                }
            }
        });
                
        List<TabSheet> l = getChildren();
        l.add(new AboutTabSheet());
        l.add(new PlayTabSheet(tree));
        l.add(new HelpTabSheet(tree, this));
    }
}
