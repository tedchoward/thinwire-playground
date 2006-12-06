package thinwire.apps.playground;

import java.util.List;

import thinwire.ui.Button;
import thinwire.ui.CheckBox;
import thinwire.ui.Component;
import thinwire.ui.Container;
import thinwire.ui.DropDownDateBox;
import thinwire.ui.DropDownGridBox;
import thinwire.ui.GridBox;
import thinwire.ui.Hyperlink;
import thinwire.ui.Label;
import thinwire.ui.Panel;
import thinwire.ui.RadioButton;
import thinwire.ui.TextComponent;
import thinwire.ui.TextField;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

public class ExampleLayoutGridInterface extends Example {

	@Override
	Component getContent() {
		Panel container = new Panel();
        container.setScrollType(Panel.ScrollType.AS_NEEDED);
        container.getStyle().getBackground().setColor(Color.LIGHTGRAY);
		
		TableLayout layout = new TableLayout();
		layout.setMargin(1);
		layout.setSpacing(1);
		container.setLayout(layout);
		
		List<TableLayout.Column> columns = layout.getColumns();
		
        for (int i = 0; i < 8; i++) {
            columns.add(new TableLayout.Column(0));
        }
		
		List<TableLayout.Row> rows = layout.getRows();
		
        for (int i = 0; i < 10; i++) {
            rows.add(new TableLayout.Row(18));
        }

		int cnt = columns.size();
        
		for (TableLayout.Row r : rows) {
			for (int i = 0; i < cnt; i++) {
				TextField tf = new TextField();
				tf.getStyle().getBorder().setSize(0);
				r.set(i, tf);
			}
		}

        Component leftOver = new Label().setLimit(new TableLayout.Range(layout, 0, rows.size(), columns.size(), 1));
        leftOver.getStyle().getBackground().setColor(Color.WHITESMOKE);
        rows.add(new TableLayout.Row(500));
        container.getChildren().add(leftOver);
		
		return container;
	}

	@Override
	String getName() {
		return "TableLayout (Grid Interface)";
	}
	
    @Override
    boolean hasCommands() {
        return true;
    }
    
	@Override
	Panel getCommands() {
		Panel commands = new Panel();
		
		TableLayout layout = new TableLayout();
		layout.setMargin(5);
		layout.setSpacing(5);
		commands.setLayout(layout);
		
		//4 columns, evenly sized
		List<TableLayout.Column> columns = layout.getColumns();
		for (int i = 0; i < 7; i++) columns.add(new TableLayout.Column(0));
		
		// 10 rows, evenly sized
		List<TableLayout.Row> rows = layout.getRows();
		for (int i = 0; i < 10; i++) rows.add(new TableLayout.Row(22));
		
		List<Component> kids = commands.getChildren();
		
		Label updateColLbl = (Label) new Label("Column:").setLimit("0, 0");
		updateColLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(updateColLbl);
		
		final TextField updateColTf = (TextField) new TextField().setLimit("1, 0");
		updateColTf.setEditMask("##");
		kids.add(updateColTf);
		
		Label updateRowLbl = (Label) new Label("Row:").setLimit("2, 0");
		updateRowLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(updateRowLbl);
		
		final TextField updateRowTf = (TextField) new TextField().setLimit("3, 0");
		updateRowTf.setEditMask("##");
		kids.add(updateRowTf);
		
		Label updateValLbl = (Label) new Label("Value:").setLimit("4, 0");
		updateValLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(updateValLbl);
		
		final TextField updateValTf = (TextField) new TextField().setLimit("5, 0");
		kids.add(updateValTf);
		
		Button updateValBtn = (Button) new Button("Update Value").setLimit("6, 0");
		updateValBtn.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int row = Integer.parseInt(updateRowTf.getText());
				int col = Integer.parseInt(updateColTf.getText());
				TableLayout tl = (TableLayout) ((Container)content).getLayout();
				((TextComponent) tl.getRows().get(row).get(col)).setText(updateValTf.getText());
			}
		});
		kids.add(updateValBtn);
		
		Label setEditorColLbl = (Label) new Label("Column:").setLimit("0, 1");
		setEditorColLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(setEditorColLbl);
		
		final TextField setEditorColTf = (TextField) new TextField().setLimit("1, 1");
		setEditorColTf.setEditMask("##");
		kids.add(setEditorColTf);
		
		Label setEditorRowLbl = (Label) new Label("Row:").setLimit("2, 1");
		setEditorRowLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(setEditorRowLbl);
		
		final TextField setEditorRowTf = (TextField) new TextField().setLimit("3, 1");
		setEditorRowTf.setEditMask("##");
		kids.add(setEditorRowTf);
		
		Label setEditorValLbl = (Label) new Label("Editor:").setLimit("4, 1");
		setEditorValLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(setEditorValLbl);
		
		final DropDownGridBox setEditorValDD = (DropDownGridBox) new DropDownGridBox().setLimit("5, 1");
		setEditorValDD.getComponent().getColumns().add(new GridBox.Column("CheckBox", "DropDownDateBox", 
				"DropDownGridBox", "Hyperlink", "Label", "RadioButton", "TextField"));
		setEditorValDD.getComponent().getColumns().add(new GridBox.Column(CheckBox.class, DropDownDateBox.class,
				DropDownGridBox.class, Hyperlink.class, Label.class, RadioButton.class, TextField.class));
		setEditorValDD.getComponent().getColumns().get(1).setVisible(false);
		setEditorValDD.setEditAllowed(false);
		kids.add(setEditorValDD);
		
		Button setEditorValBtn = (Button) new Button("Set Editor").setLimit("6, 1");
		setEditorValBtn.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					TextComponent tc = (TextComponent) ((Class) setEditorValDD.getComponent().getSelectedRow().get(1)).newInstance();
					tc.getStyle().getBorder().setSize(0);
					int row = Integer.parseInt(setEditorRowTf.getText());
					int col = Integer.parseInt(setEditorColTf.getText());
					TableLayout tl = (TableLayout) ((Container)content).getLayout();
					tl.getRows().get(row).set(col, tc);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		kids.add(setEditorValBtn);
		
		Label addRowLbl = (Label) new Label("Row:").setLimit("2, 2");
		addRowLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(addRowLbl);
		
		final TextField addRowTf = (TextField) new TextField().setLimit("3, 2");
		addRowTf.setEditMask("##");
		kids.add(addRowTf);
		
		Label addRowSizeLbl = (Label) new Label("Height:").setLimit("4, 2");
		addRowSizeLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(addRowSizeLbl);
		
		final TextField addRowSizeTf = (TextField) new TextField().setLimit("5, 2");
		addRowSizeTf.setEditMask("##.##");
		kids.add(addRowSizeTf);
		
		Button addRowBtn = (Button) new Button("Add Row").setLimit("6, 2");
		addRowBtn.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int row = Integer.parseInt(addRowTf.getText());
				double height = Double.parseDouble(addRowSizeTf.getText());
				TableLayout tl = (TableLayout) ((Container)content).getLayout();
				TableLayout.Row newRow = new TableLayout.Row(height);
				for (int i = 0, cnt = tl.getColumns().size(); i < cnt; i++) {
					TextField tf = new TextField();
					tf.getStyle().getBorder().setSize(0);
					newRow.add(tf);
				}
				tl.getRows().add(row, newRow);
			}
		});
		kids.add(addRowBtn);
		/*
		Label addColLbl = (Label) new Label("Column:").setLimit("0, 3");
		addColLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(addColLbl);
		
		final TextField addColTf = (TextField) new TextField().setLimit("1, 3");
		addColTf.setEditMask("##");
		kids.add(addColTf);
		
		Label addColSizeLbl = (Label) new Label("Width:").setLimit("4, 3");
		addColSizeLbl.setAlignX(Label.AlignX.RIGHT);
		kids.add(addColSizeLbl);
		
		final TextField addColSizeTf = (TextField) new TextField().setLimit("5, 3");
		addColSizeTf.setEditMask("##.##");
		kids.add(addColSizeTf);
		
		Button addColBtn = (Button) new Button("Add Column").setLimit("6, 3");
		addColBtn.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int column = Integer.parseInt(addColTf.getText());
				double width = Double.parseDouble(addColSizeTf.getText());
				TableLayout tl = (TableLayout) ((Container) c).getLayout();
				TableLayout.Column newCol = new TableLayout.Column(width);
				
				for (int i = 0, cnt = tl.getRows().size(); i < cnt; i++) {
					TextField tf = new TextField();
					tf.getStyle().getBorder().setSize(0);
					newCol.add(tf);
				}
				
				tl.getColumns().add(column, newCol);
			}
		});
		kids.add(addColBtn);
		*/
		return commands;
	}
}
