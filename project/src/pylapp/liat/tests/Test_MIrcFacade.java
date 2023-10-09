/*
 * Software Name: Little IRC Ant Task (liat)
 * SPDX-FileCopyrightText: Copyright (c) 2013 Pierre-Yves LAPERSONNE (aka pylapp)
 * SPDX-License-Identifier: GPL-3.0
 *
 * This software is distributed under the GNU General Public License v3.0 only.
 *
 * Author: Pierre-Yves LAPERSONNE (aka pylapp) <dev(at)pylapersonne(dot)info> et al.
 */

package pylapp.liat.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pylapp.liat.ant.LIrcAntTask;
import pylapp.liat.exceptions.BadInitializationException;
import pylapp.liat.irc.IrcFacade;
import pylapp.liat.irc.MIrcFacade;
import pylapp.liat.utils.MessagesDisplayer;

/**
 * Class to use so as to test the {@link pylapp.liat.irc.MIrcFacade}.
 * 
 * @author Pierre-Yves LAPERSONNE (aka pylapp)
 * @version 1.0.0
 * @since 08/06/2013
 */
public class Test_MIrcFacade {

	@BeforeClass
	public static void displayStart(){
		MessagesDisplayer.displayOut("[Test][LIrcAntTask] Begin...\n");
	}

	/**
	 * Tests the launch of the Task and its IRC behaviour
	 * 
	 * @throws BadInitializationException 
	 */
	@Test
	public void testTaskBehaviour() throws BadInitializationException {
		
		notifyTest("testTaskBehaviour\n");
		
		String hostname = "euroserv.fr.quakenet.org";
		int port = 6667;
		boolean ssl = false;
		
		LIrcAntTask liat = new LIrcAntTask( hostname, "", port, ssl );
		liat.setNickname("SuperPaint_BuildManager");
		liat.setRealName("SuperPaint_BuildManager");
		liat.setIdent("SuperPaint_BuildManager");
		liat.setAlternativeNickname1("SuperPaint_BuildBoss");
		liat.setAlternativeNickname2("SuperPaint_BuildChief");
		liat.setAlternativeNickname3("SuperPaint_BuildNotifier");
		liat.setChannel("#SuperPaint_Build");
		liat.setMessage("Hello World !");

		IrcFacade mirc = new MIrcFacade();
		((MIrcFacade)mirc).setConfigBundle(liat);
		mirc.connectToHost();
		
	}

	@AfterClass
	public static void displayEnd(){
		MessagesDisplayer.displayOut("[Test][LIrcAntTask] ...End\n");
	}
	
	/**
	 * Notify the call of a unit test method
	 * @param methodName
	 */
	private void notifyTest( String methodName ){
		MessagesDisplayer.displayOut("\tTest : " + methodName);
	}
	
}
