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

import java.util.List;

import thinwire.ui.Button;
import thinwire.ui.Component;
import thinwire.ui.Panel;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

public class ExampleTableLayoutComplex extends Example {
    String getName() {
        return "TableLayout (Complex)";
    }
    
    String getDescription() {
        return "<b>Resize the <font color='rgb(255,128,0)'>Playground</font> window to see the layout recalculate</b>";
    }
    
    Component getContent() {
        Panel container = new Panel();

        container.getStyle().getBackground().setColor(Color.SLATEGRAY);
        container.setLayout(new TableLayout(new double[][]{
            {0, 0, 0, 0}, //Column Widths
            {0, 0, 0, 0, 0}}, //Row Heights
            10, //Margin around edge of container
            5 //Spacing between cells
        ));
        
        List<Component> l = container.getChildren();
        l.add(new Button("Button 1").setLimit("0, 0, 1, 1"));
        l.add(new Button("Button 2").setLimit("1, 0, 1, 1")); 
        l.add(new Button("Button 3").setLimit("2, 0, 1, 1"));
        l.add(new Button("Button 4").setLimit("3, 0, 1, 1"));
        l.add(new Button("Button 5").setLimit("0, 1, 4, 1"));
        l.add(new Button("Button 6").setLimit("0, 2, 3, 1"));
        l.add(new Button("Button 7").setLimit("3, 2, 1, 1"));
        l.add(new Button("Button 8").setLimit("0, 3, 1, 2"));
        l.add(new Button("Button 9").setLimit("1, 3, 3, 1"));
        l.add(new Button("Button 10").setLimit("1, 4, 3, 1"));
        
        return container;
    }
}
