package lsi.screen;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import lsi.i18n.I18nConstants;

public class ParentScreenView {

	private ParentScreenController controller;
	private JFrame frame;
	
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuLexical;
	private JMenu menuSyntax;
	private JMenu menuSemantic;
	private JMenu menuLanguage;

	private JMenuItem menuItemAbout;
	private JMenuItem menuItemExit;

	private JMenuItem verifyLexical;
	private JMenuItem verifySyntax;
	private JMenuItem verifySemantic;

	private JMenuItem langPtBR;
	private JMenuItem langEnUS;

	private JScrollPane scrollPane;
	
	private JTextArea textArea;
	private JTextArea feedbackArea;
	
	public ParentScreenView(ParentScreenController controller) {
		setController(controller);

		init();
	}

	private void init() {
		frame = new JFrame();
		
		buildComponents();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	private void buildComponents() {
		frame.setTitle(I18nConstants.get("compiler"));
		
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		feedbackArea = new JTextArea();
		
		menuBar = new JMenuBar();
		menuFile = new JMenu(I18nConstants.get("file")); 
		menuLexical = new JMenu(I18nConstants.get("lexical")); 
		menuSyntax = new JMenu(I18nConstants.get("syntax")); 
		menuSemantic = new JMenu(I18nConstants.get("semantic"));
		menuLanguage = new JMenu(I18nConstants.get("language"));
		
		menuBar.add(menuFile);
		menuBar.add(menuLexical);
		menuBar.add(menuSyntax);
		menuBar.add(menuSemantic);
		
		setLangPtBR(new JMenuItem(I18nConstants.get("portuguese")));
		setLangEnUS(new JMenuItem(I18nConstants.get("english")));
		menuLanguage.add(getLangPtBR());
		menuLanguage.add(getLangEnUS());
		
		menuItemAbout = new JMenuItem(I18nConstants.get("about")); 
		menuItemExit = new JMenuItem(I18nConstants.get("exit"));
		
		menuItemAbout.addActionListener(controller);
		menuItemExit.addActionListener(controller);
		langPtBR.addActionListener(controller);
		langEnUS.addActionListener(controller);
		
		menuFile.add(menuLanguage);
		menuFile.add(menuItemAbout);
		menuFile.add(menuItemExit);
		
		setVerifyLexical(new JMenuItem(I18nConstants.get("verify"))); 
		setVerifySyntax(new JMenuItem(I18nConstants.get("verify"))); 
		setVerifySemantic(new JMenuItem(I18nConstants.get("verify"))); 
		getVerifyLexical().addActionListener(controller);
		getVerifySyntax().addActionListener(controller);
		getVerifySemantic().addActionListener(controller);
		
		menuLexical.add(getVerifyLexical());
		menuSyntax.add(getVerifySyntax());
		menuSemantic.add(getVerifySemantic());
		
		frame.setJMenuBar(menuBar);
		frame.add("Center", scrollPane); 
		frame.add("South", feedbackArea);
	}
	
	public String getText(){
		return textArea.getText();
	}
	
	public void reload(){
		buildComponents();
		frame.repaint();
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

	public JMenuItem getLangPtBR() {
		return langPtBR;
	}

	public void setLangPtBR(JMenuItem langPtBR) {
		this.langPtBR = langPtBR;
	}

	public JMenuItem getLangEnUS() {
		return langEnUS;
	}

	public void setLangEnUS(JMenuItem langEnUS) {
		this.langEnUS = langEnUS;
	}

}
