package lsi.screen;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ParentScreenView {

	private ParentScreenController controller;
	private JFrame frame;
	
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuLexical;
	private JMenu menuSyntax;
	private JMenu menuSemantic;

	private JMenuItem menuItemAbout;
	private JMenuItem menuItemExit;

	private JMenuItem verifyLexical;
	private JMenuItem verifySyntax;
	private JMenuItem verifySemantic;

	private JScrollPane scrollPane;
	
	private JTextArea textArea;
	private JTextArea feedbackArea;
	
	public ParentScreenView(ParentScreenController controller) {
		setController(controller);

		init();
	}

	private void init() {
		frame = new JFrame("Compiler");
		
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		feedbackArea = new JTextArea();
		
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuLexical = new JMenu("Lexical");
		menuSyntax = new JMenu("Syntax");
		menuSemantic = new JMenu("Semantic");
		
		menuBar.add(menuFile);
		menuBar.add(menuLexical);
		menuBar.add(menuSyntax);
		menuBar.add(menuSemantic);
		
		menuItemAbout = new JMenuItem("About");
		menuItemExit = new JMenuItem("Exit");
		menuItemAbout.addActionListener(controller);
		menuItemExit.addActionListener(controller);
		
		menuFile.add(menuItemAbout);
		menuFile.add(menuItemExit);
		
		setVerifyLexical(new JMenuItem("Verify"));
		setVerifySyntax(new JMenuItem("Verify"));
		setVerifySemantic(new JMenuItem("Verify"));
		getVerifyLexical().addActionListener(controller);
		getVerifySyntax().addActionListener(controller);
		getVerifySemantic().addActionListener(controller);
		
		menuLexical.add(getVerifyLexical());
		menuSyntax.add(getVerifySyntax());
		menuSemantic.add(getVerifySemantic());

		frame.setJMenuBar(menuBar);
		frame.add("Center", scrollPane);
		frame.add("South", feedbackArea);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	
	public String getText(){
		return textArea.getText();
	}
	
	/**************** Setters & Getters *******************/
	
	private void setController(ParentScreenController controller) {
		this.controller = controller;
	}
	
	public JMenuItem getMenuItemAbout() {
		return menuItemAbout;
	}

	public void setMenuItemAbout(JMenuItem menuItemAbout) {
		this.menuItemAbout = menuItemAbout;
	}

	public JMenuItem getMenuItemExit() {
		return menuItemExit;
	}

	public void setMenuItemExit(JMenuItem menuItemExit) {
		this.menuItemExit = menuItemExit;
	}

	public JMenuItem getVerifyLexical() {
		return verifyLexical;
	}

	public void setVerifyLexical(JMenuItem verifyLexical) {
		this.verifyLexical = verifyLexical;
	}

	public JMenuItem getVerifySyntax() {
		return verifySyntax;
	}

	public void setVerifySyntax(JMenuItem verifySyntax) {
		this.verifySyntax = verifySyntax;
	}

	public JMenuItem getVerifySemantic() {
		return verifySemantic;
	}

	public void setVerifySemantic(JMenuItem verifySemantic) {
		this.verifySemantic = verifySemantic;
	}

	public void feedback(String error) {
		feedbackArea.setText(error);
	}

	public void setCaretPosition(int position) {
		textArea.setCaretPosition(position);
	}

}
