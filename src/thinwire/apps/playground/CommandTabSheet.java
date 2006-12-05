package thinwire.apps.playground;

import java.util.List;

import thinwire.ui.Component;
import thinwire.ui.Container;
import thinwire.ui.Panel;
import thinwire.ui.TabFolder;
import thinwire.ui.TabSheet;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.layout.TableLayout;

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
                            Panel commands = example.getCommands(((Container<Component>) children.get(0)).getChildren().get(0));
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
