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

import thinwire.ui.AlignTextComponent.AlignX;
import thinwire.ui.Component;
import thinwire.ui.Label;
import thinwire.ui.Panel;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.layout.SplitLayout;
import thinwire.ui.style.Color;

public class ExampleSplitLayoutVertical extends Example {
    String getName() {
        return "SplitLayout (Vertical & Fixed)";
    }
    
    String getDescription() {
        return "<b>Resize the <font color='rgb(255,128,0)'>Playground</font> window to see the layout recalculate</b>";
    }
    
    Component getContent() {
        Panel container = new Panel();
        
        //Split the layout vertically, giving 100 pixels to the left component
        //and the remainder to the right component
        container.setLayout(new SplitLayout(100, true));
        container.getStyle().getBackground().setColor(Color.SLATEGRAY);
        Label first = new Label();
        Label second = new Label();
        container.getChildren().add(first);
        container.getChildren().add(second);
        
        //The rest of this is just to make the example interactive
        //and demonstrate the maximize feature of SplitLayout
        final String MAXIMIZE_FIRST = "Click to Maximize Left"; 
        final String MAXIMIZE_SECOND = "Click to Maximize Right"; 
        final String RESTORE = "Click to Restore";

        first.setText(MAXIMIZE_FIRST);
        first.setAlignX(AlignX.CENTER);
        first.getStyle().getBackground().setColor(Color.LIGHTCORAL);
        
        first.addActionListener("click", new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Label lbl = (Label)ev.getSource();
                
                if (lbl.getText().equals(MAXIMIZE_FIRST)) {
                    lbl.setText(RESTORE);
                    ((SplitLayout)lbl.getContainer().getLayout()).setMaximize(SplitLayout.Maximize.FIRST);
                } else {
                    lbl.setText(MAXIMIZE_FIRST);
                    ((SplitLayout)lbl.getContainer().getLayout()).setMaximize(SplitLayout.Maximize.NONE);
                }
            }
        });

        second.setText(MAXIMIZE_SECOND);
        second.setAlignX(AlignX.CENTER);
        second.getStyle().getBackground().setColor(Color.LIGHTBLUE);
        
        second.addActionListener("click", new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Label lbl = (Label)ev.getSource();
                
                if (lbl.getText().equals(MAXIMIZE_SECOND)) {
                    lbl.setText(RESTORE);
                    ((SplitLayout)lbl.getContainer().getLayout()).setMaximize(SplitLayout.Maximize.SECOND);
                } else {
                    lbl.setText(MAXIMIZE_SECOND);
                    ((SplitLayout)lbl.getContainer().getLayout()).setMaximize(SplitLayout.Maximize.NONE);
                }
            }
        });
        
        return container;
    }
}
