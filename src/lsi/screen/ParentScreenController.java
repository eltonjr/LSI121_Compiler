package lsi.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lsi.Env;

public class ParentScreenController implements ActionListener {

	private static ParentScreenController instance;
	private static ParentScreenView display;

	private ParentScreenController() {
	}

	public static ParentScreenController getInstance() {
		if (instance == null) {
			instance = new ParentScreenController();
		}
		return instance;
	}

	public void init() {
		setDisplay(new ParentScreenView(instance));
	}

	public static ParentScreenView getDisplay() {
		return display;
	}

	public static void setDisplay(ParentScreenView display) {
		ParentScreenController.display = display;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == display.getMenuItemAbout()) {
		} else if (e.getSource() == display.getMenuItemExit()) {
			System.exit(0);
		} else if(e.getSource() == display.getLangPtBR()){
			Env.setPtBR();
			display.reload();
		} else if(e.getSource() == display.getLangEnUS()){
			Env.setEnUS();
			display.reload();
		} else if (e.getSource() == display.getVerifyLexical()) {
			feedback(Env.getLexicalAnalyser().analyse(display.getText()));
		} else if (e.getSource() == display.getVerifySyntax()) {
			feedback(Env.getSyntaticAnalyser().analyse(display.getText()));
		} else if (e.getSource() == display.getVerifySemantic()) {
			feedback(Env.getSyntaticAnalyser().analyse(display.getText()));
		}
	}
	
	public void feedback(String error){
		display.feedback(error);
	}

	public void setCaretPosition(int position) {
		display.setCaretPosition(position);
	}

}
