package com.nhlstenden;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**The About-box for JabberPoint.
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.1 2025/03/29 Blossom Anukposi
 */

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
		JOptionPane.showMessageDialog(frame, aboutMessage, "About JabberPoint", JOptionPane.INFORMATION_MESSAGE);
	}
}
