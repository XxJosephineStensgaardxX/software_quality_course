package com.jabberpoint.presentation;

import com.jabberpoint.AboutBox;
import com.jabberpoint.Accessor;
import com.jabberpoint.XMLAccessor;
import com.jabberpoint.presentation.Presentation;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Controller for menu-based commands in the presentation
 */
public class MenuController extends MenuBar {
	private Frame parent;
	private Presentation presentation;
	
	private static final long serialVersionUID = 227L;
	
	// Menu labels and messages
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";
	
	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		presentation = pres;
		
		add(createFileMenu());
		add(createViewMenu());
		setHelpMenu(createHelpMenu());  // setHelpMenu for portability
	}
	
	private Menu createFileMenu() {
		Menu fileMenu = new Menu(FILE);
		
		MenuItem openItem = mkMenuItem(OPEN);
		openItem.addActionListener(e -> openPresentation());
		fileMenu.add(openItem);
		
		MenuItem newItem = mkMenuItem(NEW);
		newItem.addActionListener(e -> clearPresentation());
		fileMenu.add(newItem);
		
		MenuItem saveItem = mkMenuItem(SAVE);
		saveItem.addActionListener(e -> savePresentation());
		fileMenu.add(saveItem);
		
		fileMenu.addSeparator();
		
		MenuItem exitItem = mkMenuItem(EXIT);
		exitItem.addActionListener(e -> exitPresentation());
		fileMenu.add(exitItem);
		
		return fileMenu;
	}
	
	private Menu createViewMenu() {
		Menu viewMenu = new Menu(VIEW);
		
		MenuItem nextItem = mkMenuItem(NEXT);
		nextItem.addActionListener(e -> nextSlide());
		viewMenu.add(nextItem);
		
		MenuItem prevItem = mkMenuItem(PREV);
		prevItem.addActionListener(e -> prevSlide());
		viewMenu.add(prevItem);
		
		MenuItem gotoItem = mkMenuItem(GOTO);
		gotoItem.addActionListener(e -> goToSlide());
		viewMenu.add(gotoItem);
		
		return viewMenu;
	}
	
	private Menu createHelpMenu() {
		Menu helpMenu = new Menu(HELP);
		
		MenuItem aboutItem = mkMenuItem(ABOUT);
		aboutItem.addActionListener(e -> AboutBox.show(parent));
		helpMenu.add(aboutItem);
		
		return helpMenu;
	}
	
	// Navigation methods
	
	public void nextSlide() {
		presentation.nextSlide();
	}
	
	public void prevSlide() {
		presentation.prevSlide();
	}
	
	public void goToSlide() {
		String pageNumberStr = JOptionPane.showInputDialog(parent, PAGENR);
		if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
			try {
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1); // Convert from 1-based to 0-based
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(parent, "Please enter a valid number",
											  "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// File operations
	
	public void openPresentation() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.loadFile(presentation, TESTFILE);
			presentation.setSlideNumber(0);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, IOEX + exc,
										  LOADERR, JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}
	
	public void savePresentation() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, SAVEFILE);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, IOEX + exc,
										  SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void clearPresentation() {
		presentation.clear();
		parent.repaint();
	}
	
	public void exitPresentation() {
		presentation.exit(0);
	}
	
	// Helper method to create menu items
	private MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}