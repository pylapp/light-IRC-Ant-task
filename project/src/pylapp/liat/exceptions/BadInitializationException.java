/*
 * Software Name: Little IRC Ant Task (liat)
 * SPDX-FileCopyrightText: Copyright (c) 2013 Pierre-Yves LAPERSONNE (aka pylapp)
 * SPDX-License-Identifier: GPL-3.0
 *
 * This software is distributed under the GNU General Public License v3.0 only.
 *
 * Author: Pierre-Yves LAPERSONNE (aka pylapp) <dev(at)pylapersonne(dot)info> et al.
 */
 
package pylapp.liat.exceptions;

/**
 * Exception to use if an object is not correctly created
 * 
 * @author Pierre-Yves LAPERSONNE (aka pylapp)
 * @version 1.0.0
 * @see java.lang.Exception
 * @since 07/06/2013
 */
public class BadInitializationException extends Exception {

	
	// CONSANTS
	
	/**
	 * For the {@link java.io.Serializable}  aspects of the {@link java.lang.Exception} class. 
	 */
	private static final long serialVersionUID = 7345429034566392886L;

	// CONSTRUCTORS
	
	/**
	 * Default constructor
	 */
	public BadInitializationException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param text - The text to display
	 */
	public BadInitializationException( String text ){
		super( text );
	}
	
}
