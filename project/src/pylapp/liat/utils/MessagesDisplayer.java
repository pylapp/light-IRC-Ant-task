/*
 * Software Name: Little IRC Ant Task (liat)
 * SPDX-FileCopyrightText: Copyright (c) 2013 Pierre-Yves LAPERSONNE (aka pylapp)
 * SPDX-License-Identifier: GPL-3.0
 *
 * This software is distributed under the GNU General Public License v3.0 only.
 *
 * Author: Pierre-Yves LAPERSONNE (aka pylapp) <dev(at)pylapersonne(dot)info> et al.
 */

package pylapp.liat.utils;

import java.io.PrintStream;

/**
 * Tool class used so as to display texts on specified outputs.
 * 
 * @author Pierre-Yves LAPERSONNE (aka pylapp)
 * @version 1.0.0
 * @since 07/06/2013
 */
public class MessagesDisplayer {

	
	// CONSTANTS
	
	/**
	 * The default output stream
	 */
	private static final PrintStream OUT;
	
	/**
	 * The error output stream
	 */
	private static final PrintStream ERR;
	
	static {
		OUT = System.out;
		ERR = System.err;
	}
	
	
	// METHODS
	
	/**
	 * Displays in the default output the text
	 * 
	 * @param text - The message to write
	 */
	public static void displayOut( String text ){
		OUT.print(text);
	}

	/**
	 * Displays in the error output the text
	 * 
	 * @param text - The message to write
	 */
	public static void displayErr( String text ){
		ERR.print(text);
	}
	
}
