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

import thinwire.ui.Component;
import thinwire.ui.Panel;
import thinwire.ui.TabFolder;
import thinwire.ui.TabSheet;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.layout.TableLayout;

/**
 * @author Joshua J. Gertzen
 */
public class CommandTabSheet extends TabSheet {
	
	Panel panel;

	public CommandTabSheet(final TabFolder tf, final Panel panel) {
		super("Commands");
		this.panel = panel;
		setLayout(new TableLayout(new double[][] {{0}, {0}}, 5));
        List<Component> kids = this.panel.getChildren();

		tf.addPropertyChangeListener(TabFolder.PROPERTY_CURRENT_INDEX, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                 if (((Integer)ev.getNewValue()) == tf.getChildren().indexOf(CommandTabSheet.this)) {
                    List<Component> children = panel.getChildren();
                    
                    if (!children.isEmpty()) {
                        if (children.size() == 1 && children.get(0).getUserObject() instanceof Example) {
                            Example example = (Example)children.get(0).getUserObject();
                            Panel commands = example.getCommands();
        
                            if (commands != null) {
                            	commands.getStyle().getBackground().setColor(PlayTabSheet.SUBTAB_BACKGROUND);
                            	CommandTabSheet.this.getChildren().add(commands);
                            }
                        }
                    }
                }
            }
        });
	}

}
