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

import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import thinwire.ui.*;
import thinwire.ui.AlignTextComponent.AlignX;
import thinwire.ui.Container.ScrollType;
import thinwire.ui.style.*;


/**
 * @author Joshua J. Gertzen
 */
enum Property {
    X(Component.class, Component.PROPERTY_X, int.class),
    Y(Component.class, Component.PROPERTY_Y, int.class),
    WIDTH(Component.class, Component.PROPERTY_WIDTH, int.class),
    HEIGHT(Component.class, Component.PROPERTY_HEIGHT, int.class),
    ENABLED(Component.class, Component.PROPERTY_ENABLED, boolean.class),
    VISIBLE(Component.class, Component.PROPERTY_VISIBLE, boolean.class),
    FOCUS_CAPABLE(Component.class, Component.PROPERTY_FOCUS_CAPABLE, boolean.class),
    TEXT(TextComponent.class, TextComponent.PROPERTY_TEXT, String.class),
    IMAGE(ImageComponent.class, ImageComponent.PROPERTY_IMAGE, String.class),
    ALIGN_X(AlignTextComponent.class, AlignTextComponent.PROPERTY_ALIGN_X, AlignX.class),
    MAX_LENGTH(EditorComponent.class, EditorComponent.PROPERTY_MAX_LENGTH, int.class),
    SELECTION_BEGIN_INDEX(EditorComponent.class, MaskEditorComponent.PROPERTY_SELECTION_BEGIN_INDEX, int.class),
    SELECTION_END_INDEX(EditorComponent.class, MaskEditorComponent.PROPERTY_SELECTION_END_INDEX, int.class),
    SELECTION_CURSOR_INDEX(EditorComponent.class, MaskEditorComponent.PROPERTY_CURSOR_INDEX, int.class),
    EDIT_MASK(MaskEditorComponent.class, MaskEditorComponent.PROPERTY_EDIT_MASK, String.class),
    FORMAT_TEXT(MaskEditorComponent.class, MaskEditorComponent.PROPERTY_FORMAT_TEXT, boolean.class),
    TITLE(Window.class, Window.PROPERTY_TITLE, String.class),
    WAIT_FOR_WINDOW(Dialog.class, Dialog.PROPERTY_WAIT_FOR_WINDOW, boolean.class),
    EDIT_ALLOWED(DropDown.class, DropDown.PROPERTY_EDIT_ALLOWED, boolean.class),
    INPUT_HIDDEN(TextField.class, TextField.PROPERTY_INPUT_HIDDEN, boolean.class),
    SCROLL_TYPE(Container.class, Container.PROPERTY_SCROLL_TYPE, ScrollType.class),
    STANDARD(Button.class, Button.PROPERTY_STANDARD, boolean.class),
    BUTTON_CHECKED(RadioButton.class, RadioButton.PROPERTY_CHECKED, boolean.class),
    BOX_CHECKED(CheckBox.class, CheckBox.PROPERTY_CHECKED, boolean.class),
    LINK_LOCATION(Hyperlink.class, Hyperlink.PROPERTY_LOCATION, String.class),
    LINK_TARGET(Hyperlink.class, Hyperlink.PROPERTY_TARGET, String.class),
    LINK_VISIBLE_CHROME(Hyperlink.class, Hyperlink.PROPERTY_VISIBLE_CHROME, boolean.class),
    LINK_RESIZE_ALLOWED(Hyperlink.class, Hyperlink.PROPERTY_RESIZE_ALLOWED, boolean.class),
    SELECTED_DATE(DateBox.class, DateBox.PROPERTY_SELECTED_DATE, Date.class),
    FULL_ROW_CHECK_BOX(GridBox.class, GridBox.PROPERTY_FULL_ROW_CHECK_BOX, boolean.class),
    VISIBLE_CHECK_BOXES(GridBox.class, GridBox.PROPERTY_VISIBLE_CHECK_BOXES, boolean.class),
    VISIBLE_HEADER(GridBox.class, GridBox.PROPERTY_VISIBLE_HEADER, boolean.class),
    BROWSER_LOCATION(WebBrowser.class, WebBrowser.PROPERTY_LOCATION, String.class),
    ROOT_ITEM_VISIBLE(Tree.class, Tree.PROPERTY_ROOT_ITEM_VISIBLE, boolean.class),
    RESIZE_ALLOWED(Dialog.class, Dialog.PROPERTY_RESIZE_ALLOWED, boolean.class),
    BACKGROUND_COLOR(Background.class, Background.PROPERTY_BACKGROUND_COLOR, Color.class),
    BACKGROUND_IMAGE(Background.class, Background.PROPERTY_BACKGROUND_IMAGE, String.class),
    BACKGROUND_POSITION(Background.class, Background.PROPERTY_BACKGROUND_POSITION, Background.Position.class),
    BACKGROUND_REPEAT(Background.class, Background.PROPERTY_BACKGROUND_REPEAT, Background.Repeat.class),
    FONT_BOLD(Font.class, Font.PROPERTY_FONT_BOLD, boolean.class),
    FONT_ITALIC(Font.class, Font.PROPERTY_FONT_ITALIC, boolean.class),
    FONT_UNDERLINE(Font.class, Font.PROPERTY_FONT_UNDERLINE, boolean.class),
    FONT_STRIKE(Font.class, Font.PROPERTY_FONT_STRIKE, boolean.class),
    FONT_FAMILY(Font.class, Font.PROPERTY_FONT_FAMILY, Font.Family.class),
    FONT_SIZE(Font.class, Font.PROPERTY_FONT_SIZE, double.class),
    FONT_COLOR(Font.class, Font.PROPERTY_FONT_COLOR, Color.class),
    BORDER_TYPE(Border.class, Border.PROPERTY_BORDER_TYPE, Border.Type.class),
    BORDER_SIZE(Border.class, Border.PROPERTY_BORDER_SIZE, int.class),
    BORDER_COLOR(Border.class, Border.PROPERTY_BORDER_COLOR, Color.class),
    BORDER_IMAGE(Border.class, Border.PROPERTY_BORDER_IMAGE, String.class),
    OPACITY(Style.class, Style.PROPERTY_OPACITY, int.class),
    FX_POSITION_CHANGE(FX.class, FX.PROPERTY_FX_POSITION_CHANGE, Effect.Motion.class),
    FX_SIZE_CHANGE(FX.class, FX.PROPERTY_FX_SIZE_CHANGE, Effect.Motion.class),
    FX_VISIBLE_CHANGE(FX.class, FX.PROPERTY_FX_VISIBLE_CHANGE, Effect.Motion.class),
    FX_OPACITY_CHANGE(FX.class, FX.PROPERTY_FX_OPACITY_CHANGE, Effect.Motion.class),
    FX_COLOR_CHANGE(FX.class, FX.PROPERTY_FX_COLOR_CHANGE, Effect.Motion.class),
    LENGTH(RangeComponent.class, RangeComponent.PROPERTY_LENGTH, int.class),
    TF_CURRENT_INDEX(TabFolder.class, TabFolder.PROPERTY_CURRENT_INDEX, int.class),
    CURRENT_INDEX(RangeComponent.class, RangeComponent.PROPERTY_CURRENT_INDEX, int.class),
    WRAP_TEXT(LabelComponent.class, LabelComponent.PROPERTY_WRAP_TEXT, boolean.class);
    
    private static final String[] TRUE_FALSE = new String[]{"true", "false"};
    private static final String DATE_TYPE_FORMAT = "MM/dd/yyyy";
    
    private Class objectType;
    private String name;
    private Class type;
    private Method getter;
    private Method setter;
    
    private Property(Class objectType, String name, Class type) {
        try {
            this.objectType = objectType;
            this.name = name;
            this.type = type;            
            if (isStyleProperty() && objectType != Style.class) name = name.substring(Main.getSimpleClassName(objectType).length());
            name = name.length() == 1 ? String.valueOf(Character.toUpperCase(name.charAt(0))) : Character.toUpperCase(name.charAt(0)) + name.substring(1);            
            getter = objectType.getMethod((type == boolean.class ? "is" : "get") + name);
            setter = objectType.getMethod("set" + name, type);
        } catch (Exception e) {
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            throw new RuntimeException(e);
        }
    }
    
    public Class getObjectType() {
        return objectType;
    }
    
    public String getName() {
        return name;
    }
    
    public Class getType() {
        return type;
    }
    
    public MaskEditorComponent newEditor() {
        String[] options = null;
        boolean editAllowed = true;
        String editMask = null;
        int maxLength = -1;
        AlignX alignX = null;
        
        if (type == boolean.class) {
            options = TRUE_FALSE;
            editAllowed = false;
        } else if (type == int.class) {
            editMask = name == Component.PROPERTY_X || name == Component.PROPERTY_Y ? "-####" : "####";
            alignX = AlignX.RIGHT;
        } else if (name == Font.PROPERTY_FONT_SIZE) {
            editMask = "###.#";
        } else if (type == Date.class) {
            editMask = DATE_TYPE_FORMAT;
        } else if (name == MaskEditorComponent.PROPERTY_EDIT_MASK) {
            options = new String[] {
                    "-###,###,###.##",
                    "###.####",
                    "MM/dd/yyyy",
                    "MM/dd/yy",
                    "hh:mm",
                    "999-99-9999",
                    "99-9999999",
                    "(999) 999-9999",
                    "XXXXXXXX",
                    "AAAAAAAA",
                    "aaaaaaaa",
            };
        } else if (name == Border.PROPERTY_BORDER_IMAGE) {
            options = new String[]{
                "",
                Main.RES_PATH + "BorderSize2.png",
                Main.RES_PATH + "BorderSize3.png",
                Main.RES_PATH + "BorderSize10.png",
                Main.RES_PATH + "BorderSize28.png",
            };
        } else if (name == Background.PROPERTY_BACKGROUND_IMAGE) {
            options = new String[]{Main.RES_PATH + "BackgroundOrangeLRFade.png"};
        } else if (name == ImageComponent.PROPERTY_IMAGE) {
            Widget[] values = Widget.values();
            options = new String[values.length + 2];
            options[0] = Main.RES_PATH + "File.png";
            options[1] = Main.RES_PATH + "Folder.png";
            
            for (int i = 0; i < values.length; i++) {
                options[i + 2] = Main.RES_PATH + Main.getSimpleClassName(values[i].getType()) + ".png";
            }
        } else if (name == WebBrowser.PROPERTY_LOCATION) {
            options = new String[] {
                    "http://www.thinwire.com",
                    "http://www.customcreditsystems.com",
                    "http://www.truecode.org",
                    Main.RES_PATH + "About.html"
            };
        } else if (Enum.class.isAssignableFrom(type)) {            
            try {
                editAllowed = false;
                Enum[] values = (Enum[])type.getMethod("values").invoke(null);                
                options = new String[values.length];
                
                for (int i = 0; i < values.length; i++) {
                    options[i] = values[i].name().toLowerCase();
                }                
            } catch (Exception e) {
                if (e instanceof RuntimeException) throw (RuntimeException)e;
                throw new RuntimeException(e);
            }            
        } else if (type == Color.class) {
            Color[] values = Color.values();
            options = new String[values.length + 2];
            editAllowed = true;
            options[0] = "rgb(255, 128, 0)";
            options[1] = "#5F9EA0";

            for (int i = 0; i < values.length; i++) {
                options[i + 2] = values[i].name().toLowerCase();
            }
        } else if (type == Font.Family.class) {
            Font.Family[] values = Font.Family.values();
            options = new String[values.length + 1];
            editAllowed = true;
            options[0] = "Tahoma, sans-serif";

            for (int i = 0; i < values.length; i++) {
                options[i + 1] = values[i].toString();
            }
        } else if (type == Background.Position.class) {
            Background.Position[] values = Background.Position.values();
            options = new String[values.length + 1];
            editAllowed = true;
            options[0] = "25% 25%";

            for (int i = 0; i < values.length; i++) {
                options[i + 1] = values[i].toString();
            }
        } else if (type == Effect.Motion.class) {
            Effect.Motion[] values = Effect.Motion.values();
            options = new String[values.length + 1];
            editAllowed = true;
            options[0] = "5000 25 smooth";

            for (int i = 0; i < values.length; i++) {
                options[i + 1] = values[i].toString();
            }
        }
        
        MaskEditorComponent editor;
        
        if (options != null) {
            DropDownGridBox dd = new DropDownGridBox();
            dd.getComponent().getColumns().add(new GridBox.Column((Object[])options));
            dd.setEditAllowed(editAllowed);
            editor = dd;
        } else {
            editor = new TextField();
        }
        
        if (editMask != null) {
            editor.setEditMask(editMask);
        } else if (maxLength != -1) {
            editor.setMaxLength(maxLength);
        }
        
        if (alignX != null) editor.setAlignX(alignX);        
        return editor;
    }
    
    public boolean isValidFor(Class<? extends Component> clazz) {        
        if (objectType.isAssignableFrom(clazz) || isStyleProperty()) {
            if (this == SCROLL_TYPE && TabFolder.class.isAssignableFrom(clazz)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    public boolean isStyleProperty() {
        return !Component.class.isAssignableFrom(objectType);
    }
    
    public void setValue(Component comp, Object value) {
        try {
            if (!type.isInstance(value)) value = convertTo(type, value.toString());
            
            if (objectType == Background.class) {
                setter.invoke(comp.getStyle().getBackground(), value);
            } else if (objectType == Font.class) {
                setter.invoke(comp.getStyle().getFont(), value);
            } else if (objectType == Border.class) {
                setter.invoke(comp.getStyle().getBorder(), value);
            } else if (objectType == FX.class) {
                setter.invoke(comp.getStyle().getFX(), value);
            } else if (objectType == Style.class) {
                setter.invoke(comp.getStyle(), value);
            } else {
                setter.invoke(comp, value);                    
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            throw new RuntimeException(e);
        }
    }

    public Object getValue(Component comp) {
        try {
            if (objectType == Background.class) {
                return getter.invoke(comp.getStyle().getBackground());
            } else if (objectType == Font.class) {
                return getter.invoke(comp.getStyle().getFont());
            } else if (objectType == Border.class) {
                return getter.invoke(comp.getStyle().getBorder());
            } else if (objectType == FX.class) {
                return getter.invoke(comp.getStyle().getFX());
            } else if (objectType == Style.class) {
                return getter.invoke(comp.getStyle());
            } else if (type == Date.class) {
                return new SimpleDateFormat(DATE_TYPE_FORMAT).format((Date)getter.invoke(comp));
            } else {            
                return getter.invoke(comp);
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            throw new RuntimeException(e);
        }
    }
    
    public Object getDefaultValue(Component comp) {
        for (Widget w : Widget.values()) {           
            if (w.getType().isInstance(comp)) {
                return w.getDefaultValue(name);
            }
        }
        
        return null;
    }
    
    private static Object convertTo(Class type, Object value) {
        if (value == null) return null;
        if (type.equals(value.getClass())) return value;        
        String str = value.toString();
        if (str.equals("null")) str = null;
        
        if (type == String.class) {
            value = str;
        } else if (type == Date.class) {
            try {
                value = new SimpleDateFormat(DATE_TYPE_FORMAT).parse(str);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if (type == boolean.class || type == Boolean.class) {
            value = Boolean.valueOf(str);
        } else if (type == int.class || type == Integer.class) {                            
            value = new Integer(Double.valueOf(str).intValue());
        } else if (type == long.class || type == Long.class) {                            
            value = new Long(Double.valueOf(str).longValue());
        } else if (type == short.class || type == Short.class) {
            value = new Short(Double.valueOf(str).shortValue());
        } else if (type == byte.class || type == Byte.class) {
            value = new Byte(Double.valueOf(str).byteValue());
        } else if (type == float.class || type == Float.class) {
            value = new Float(Double.valueOf(str).floatValue());
        } else if (type == double.class || type == Double.class) {
            value = Double.valueOf(str);                                
        } else if (type == char.class || type == Character.class) {                                
            value = new Character(str.charAt(0));
        } else {
            try {
                Field f = type.getField(str.toUpperCase().replace('-', '_'));                        
                value = f.get(null);
            } catch (NoSuchFieldException e2) {
                try {
                    Method m = type.getMethod("valueOf", String.class);
                    if (m.getReturnType() != type) throw new NoSuchMethodException("public static " + type + " valueOf(String value)");
                    value = m.invoke(null, value);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);                
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        }
        
        return value;
    }    
}
