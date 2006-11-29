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

public class ExampleTableLayoutSimple extends Example {
    String getName() {
        return "TableLayout (Simple)";
    }
    
    String getDescription() {
        return "<b>Resize the <font color='rgb(255,128,0)'>Playground</font> window to see the layout recalculate</b>";
    }
    
    Component getContent() {
        Panel container = new Panel();
        
        container.getStyle().getBackground().setColor(Color.SLATEGRAY);
        container.setLayout(new TableLayout(new double[][]{
            {10, .1, 20, 0, 20, .2, 10},
            {10, .2, 20, 0, 20, .2, 10}}
        ));
            
        List<Component> l = container.getChildren();
        l.add(new Button("Overlap").setLimit("3, 3, 1, 3"));
        l.add(new Button("Center").setSize(100, 25).setLimit("3, 3, c, c"));
        l.add(new Button("Right").setLimit("5, 3, 1, 1"));
        l.add(new Button("Left").setLimit("1, 3, 1, 1"));
        l.add(new Button("Bottom").setLimit("1, 5, 5, 1"));
        l.add(new Button("Top").setLimit("1, 1, 5, 1"));
        
        return container;
    }
}
