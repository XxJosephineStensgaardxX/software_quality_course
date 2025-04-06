package com.jabberpoint.presentation;

import com.jabberpoint.AboutBox;
import com.jabberpoint.Accessor;
import com.jabberpoint.XMLAccessor;
import com.jabberpoint.presentation.config.MenuError;
import com.jabberpoint.presentation.config.MenuFile;
import com.jabberpoint.presentation.config.MenuLabel;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MenuController extends MenuBar {
	private Frame parent;
	private Presentation presentation;
	
	private static final long serialVersionUID = 227L;
	
	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		presentation = pres;
		
		add(createFileMenu());
		add(createViewMenu());
		setHelpMenu(createHelpMenu());
	}
	
	private Menu createFileMenu() {
		Menu fileMenu = new Menu(MenuLabel.FILE.getLabel());
		
		MenuItem openItem = mkMenuItem(MenuLabel.OPEN.getLabel());
		openItem.addActionListener(e -> openPresentation());
		fileMenu.add(openItem);
		
		MenuItem newItem = mkMenuItem(MenuLabel.NEW.getLabel());
		newItem.addActionListener(e -> clearPresentation());
		fileMenu.add(newItem);
		
		MenuItem saveItem = mkMenuItem(MenuLabel.SAVE.getLabel());
		saveItem.addActionListener(e -> savePresentation());
		fileMenu.add(saveItem);
		
		fileMenu.addSeparator();
		
		MenuItem exitItem = mkMenuItem(MenuLabel.EXIT.getLabel());
		exitItem.addActionListener(e -> exitPresentation());
		fileMenu.add(exitItem);
		
		return fileMenu;
	}
	
	private Menu createViewMenu() {
		Menu viewMenu = new Menu(MenuLabel.VIEW.getLabel());
		
		MenuItem nextItem = mkMenuItem(MenuLabel.NEXT.getLabel());
		nextItem.addActionListener(e -> nextSlide());
		viewMenu.add(nextItem);
		
		MenuItem prevItem = mkMenuItem(MenuLabel.PREV.getLabel());
		prevItem.addActionListener(e -> prevSlide());
		viewMenu.add(prevItem);
		
		MenuItem gotoItem = mkMenuItem(MenuLabel.GOTO.getLabel());
		gotoItem.addActionListener(e -> goToSlide());
		viewMenu.add(gotoItem);
		
		return viewMenu;
	}
	
	private Menu createHelpMenu() {
		Menu helpMenu = new Menu(MenuLabel.HELP.getLabel());
		
		MenuItem aboutItem = mkMenuItem(MenuLabel.ABOUT.getLabel());
		aboutItem.addActionListener(e -> AboutBox.show(parent));
		helpMenu.add(aboutItem);
		
		return helpMenu;
	}
	
	// Navigation methods remain unchanged
	
	public void goToSlide() {
		String pageNumberStr = JOptionPane.showInputDialog(parent, MenuLabel.PAGE_NUMBER);
		if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
			try {
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(parent, "Please enter a valid number",
											  "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void nextSlide() {
		presentation.nextSlide();
	}
	
	public void prevSlide() {
		presentation.prevSlide();
	}
	
	public void openPresentation() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.loadFile(presentation, MenuFile.TEST.getFilename());
			presentation.setSlideNumber(0);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, MenuError.IO_EXCEPTION.getMessage() + exc,
										  MenuError.LOAD_ERROR.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}
	
	public void savePresentation() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, MenuFile.SAVE.getFilename());
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, MenuError.IO_EXCEPTION.getMessage() + exc,
										  MenuError.SAVE_ERROR.getMessage(), JOptionPane.ERROR_MESSAGE);
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