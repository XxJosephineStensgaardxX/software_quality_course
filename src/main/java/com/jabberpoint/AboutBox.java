package com.jabberpoint;

import java.awt.*;
import javax.swing.*;

public class AboutBox
{
	public static String aboutMessage = "JabberPoint is a primitive slide-show program in Java(tm). It is freely copyable as long as you keep this notice and the splash screen intact. Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com. Adapted by Gert Florijn (version 1.1) and Sylvia Stuurman (version 1.2 and higher) for the Open University of the Netherlands, 2002 -- now. Author's version available from http://www.darwinsys.com/";
	
	public static String getAboutMessage()
	{
		return aboutMessage;
	}
	
	public static void setAboutMessage(String aboutMessage)
	{
		AboutBox.aboutMessage = aboutMessage;
	}
	
	public static void show(Frame frame) {
		JTextArea textArea = new JTextArea(aboutMessage);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(UIManager.getColor("Panel.background"));
		textArea.setFont(UIManager.getFont("Label.font"));
		
		// Set preferred size to control dialog dimensions
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(350, 150));
		
		JOptionPane.showMessageDialog(frame, scrollPane, "About JabberPoint", JOptionPane.INFORMATION_MESSAGE);
	}
}
