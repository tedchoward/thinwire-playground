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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import thinwire.ui.*;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

/**
 * @author Joshua J. Gertzen
 */
abstract class Example {
    static final Example[] getExamples() {
        return new Example[] {
            new ExampleCustomDropDown(),
            new ExampleDragAndDrop(),
            new ExampleLayoutGridInterface(),
            new ExampleOpacity(),
            new ExampleRoundedBorders(),
            new ExampleSplitLayoutHorizontal(),
            new ExampleSplitLayoutVertical(),
            new ExampleTableLayoutSimple(),
            new ExampleTableLayoutComplex(),
        };
    }

    Panel example;
    Component content;
    
    Component getExample() {
        if (example == null) {
            example = new Panel();
            String desc = getDescription();
            //example.getStyle().getBackground().setColor(Color.TRANSPARENT);
            example.setUserObject(this);
            
            content = getContent();

            if (desc == null) {
                example.setLayout(new TableLayout(new double[][]{{0},{0}}));
            } else {
                example.setLayout(new TableLayout(new double[][]{{0},{30, 0}}));
                Label lbl = new Label(desc);
                lbl.setWrapText(true);
                example.getChildren().add(lbl);
                content.setLimit("0, 1");
            }

            example.getChildren().add(content);
        }
        
        return example;
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
    
    Panel getCommands() {
    	return null;
    }
    
    boolean hasCommands() {
        return false;
    }
    
    abstract String getName();
    abstract Component getContent();
}
