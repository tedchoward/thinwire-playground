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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thinwire.ui.Component;
import thinwire.ui.DropDown;
import thinwire.ui.Image;
import thinwire.ui.Panel;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

/**
 * @author Joshua J. Gertzen
 */
public class ExampleCustomDropDown extends Example {
    
    static class ImageChooser extends Panel {
        public static final String PROPERTY_IMAGE_SELECTED = "imageSelected";
        
        private Image selectedImage;
        private Map<String, Image> imageMap;
        private int imgHeight;
        
        private ActionListener imageClickListener = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setSelectedImage((Image) ev.getSource());
            }
        };
        
        ImageChooser() {
            setHeight(100);
            setScrollType(ImageChooser.ScrollType.AS_NEEDED);
            getStyle().getBackground().setColor(Color.WINDOW);
            imgHeight = 0;
            imageMap = new HashMap<String, Image>();
            Widget[] values = Widget.values();
            addImage(Main.RES_PATH + "File.png");
            addImage(Main.RES_PATH + "Folder.png");
            
            for (int i = 0; i < values.length; i++) {
                addImage(Main.RES_PATH + Main.getSimpleClassName(values[i].getType()) + ".png");
            }
        }
        
        void addImage(String fileName) {
            Image img = new Image(fileName);
            img.setPosition(5, imgHeight + 5);
            if (getInnerWidth() < img.getWidth()) setWidth(img.getWidth() + 10);
            imgHeight += 5 + img.getHeight();
            img.addActionListener(Image.ACTION_CLICK, imageClickListener);
            getChildren().add(img);
            imageMap.put(img.getImage(), img);
            setSelectedImage(img);
        }
        
        public void setSelectedImage(Image selectedImage) {
            Image oldImage = this.selectedImage;
            if (oldImage != null) oldImage.getStyle().getBackground().setColor(Color.TRANSPARENT);
            this.selectedImage = selectedImage;
            this.selectedImage.getStyle().getBackground().setColor(Color.HIGHLIGHT);
            firePropertyChange(this, PROPERTY_IMAGE_SELECTED, oldImage, this.selectedImage);
        }
        
        public Image getSelectedImage() {
            return selectedImage;
        }
        
        public Image getImage(String imageName) {
            return imageMap.get(imageName); 
        }
    }
    
    static class DropDownImageChooser extends DropDown<ImageChooser> {
        
        private static class DefaultView extends DropDown.AbstractView<ImageChooser> {
            
            void init(DropDownImageChooser ddic, ImageChooser ic) {
                super.init(ddic, ic);
                List<Component> kids = ddc.getChildren();
                for (Component c : kids) {
                    if (c instanceof Image) {
                        Image img = (Image) c;
                        addCloseComponent(img);
                    }
                }
                ddc.addPropertyChangeListener(ImageChooser.PROPERTY_IMAGE_SELECTED, new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent ev) {
                        dd.setText(getValue().toString());
                    }
                });
            }

            public DropDown<ImageChooser> getDropDown() {
                return (DropDownImageChooser)dd;
            }

            public int getOptimalHeight() {
                return ddc.getHeight();
            }

            public Object getValue() {
                return ddc.getSelectedImage().getImage();
            }

            public void setValue(Object value) {
                String s;
                if (value == null) {
                    s = "";
                } else if (value instanceof String) {
                    s = (String) value;
                } else {
                    s = value.toString();
                }
                ddc.setSelectedImage(ddc.getImage(s));
            }
            
        }

        DropDownImageChooser() {
            super(new DefaultView(), new ImageChooser());
            ((DefaultView) getView()).init(this, getComponent());
        }
        
    }

    @Override
    Component getContent() {
        Panel p = new Panel();
        //p.getStyle().getBackground().setColor(PlayTabSheet.BACKGROUND);
        p.setLayout(new TableLayout(new double[][] {{0, 400, 0}, {0, 25, 0}}));
        p.getChildren().add(new DropDownImageChooser().setLimit("1, 1"));
        return p;
    }

    @Override
    String getName() {
        return "Custom DropDown";
    }

}
