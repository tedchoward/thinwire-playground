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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import thinwire.ui.*;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

abstract class Example {
    static final Example[] getExamples() {
        return new Example[] {
                new ExampleSplitLayoutHorizontal(),
                new ExampleSplitLayoutVertical(),
                new ExampleTableLayoutSimple(),
                new ExampleTableLayoutComplex(),
        };
    }

    Component getExample() {
        Panel p = new Panel();
        String desc = getDescription();
        p.getStyle().getBackground().setColor(Color.TRANSPARENT);
        Component example = getContent();
        example.setUserObject(this);
        p.getChildren().add(example);
        
        if (desc == null) {
            p.setLayout(new TableLayout(new double[][]{{0},{0}}));
        } else {
            p.setLayout(new TableLayout(new double[][]{{0},{30, 0}}));
            Label lbl = new Label(desc);
            lbl.setWrapText(true);
            p.getChildren().add(lbl);
            example.setLimit("0, 1");
        }
        
        return p;
    }

    String getDescription() {
        return null;
    }
    
    String getSourceFileName() {
        return Main.getSimpleClassName(this.getClass()) + ".txt";
    }
    
    String getSourceCode() {
        StringBuilder sb = new StringBuilder();
        InputStream is = CodeTabSheet.class.getResourceAsStream("resources/" + getSourceFileName());
        
        if (is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (Exception e) {
                sb.setLength(0);
            }
        }
        
        if (sb.length() == 0) sb.append("Unable to locate source file: " + getSourceFileName());
        return sb.toString();
    }
    
    abstract String getName();
    abstract Component getContent();
}
