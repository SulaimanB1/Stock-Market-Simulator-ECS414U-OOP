/**
 Class Prompt represents the prompt window

 @author Sulaiman Bakali Mueden
 @version 1
 */

import java.awt.*;
import java.awt.event.*;

public class Prompt extends Frame{
	private Button submit;

	public Prompt() {
		setLayout(new GridLayout(0,2));

		// Window

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Frame frame = (Frame) e.getSource();
				frame.dispose();
			}
		});

		setTitle("Prompt");
		setSize(200,80);

		setVisible(true);
	}

	

	public TextField createInputInteger() {
		// Label, TextField and Button
		Label amountLabel = new Label("Amount");
		TextField amountField = new TextField();

		this.add(amountLabel);
		this.add(amountField);

		return amountField;
	}

	public List createList(Object[] array) {
		List list = new List(4, false);
		for (int i=0; i< array.length; i++) {
			list.add(array[i].toString());
		}
		this.add(list);
		this.setSize(400, 200);

		return list;
	}

	public Button addSubmit() {
		Button submitButton = new Button("Submit");
		this.add(submitButton);
		this.submit = submitButton;

		return this.submit;
	}
}
