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

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pylapp.liat.ant.LIrcAntTask;
import pylapp.liat.exceptions.BadInitializationException;
import pylapp.liat.utils.MessagesDisplayer;

/**
 * Class to use so as to test the {@link pylapp.liat.ant.LIrcAntTask}.
 * 
 * @author Pierre-Yves LAPERSONNE (aka pylapp)
 * @version 1.0.0
 * @since 07/06/2013
 */
public class Test_LIrcAntTask {

	@BeforeClass
	public static void displayStart(){
		MessagesDisplayer.displayOut("[Test][LIrcAntTask] Begin...\n");
	}
	
	/**
	 * Tests a "good" initialization of the object
	 * @throws BadInitializationException
	 */
	@Test
	public void testInitialization_1() throws BadInitializationException {
		
		notifyTest("testInitialization_1\n");
		
		String hostname = "a mock host name";
		String ipv4 = "192.168.10.1";
		int port = 6668;
		boolean ssl = true;
		
		LIrcAntTask liat = new LIrcAntTask( hostname, ipv4, port, ssl );
		
		assertEquals( liat.getHostName(), hostname );
		assertEquals( liat.getHostIpv4(), ipv4 );
		assertEquals( liat.getServicePort(), port );
		assertEquals( liat.getChannel(), LIrcAntTask.DEFAULT_CHANNEL );
		assertEquals( liat.getNickname(), LIrcAntTask.DEFAULT_NICK );
		assertEquals( liat.getAlternativeNickname1(), LIrcAntTask.DEFAULT_ALTER_NICK_1 );
		assertEquals( liat.getAlternativeNickname2(), LIrcAntTask.DEFAULT_ALTER_NICK_2 );
		assertEquals( liat.getAlternativeNickname3(), LIrcAntTask.DEFAULT_ALTER_NICK_3 );
		assertEquals( liat.getMessage(), LIrcAntTask.DEFAULT_MESSAGE );
		
	}
	
	/**
	 * Test another "good" initialization, with default values here.
	 * @throws BadInitializationException
	 */
	@Test
	public void testInitialization_2() throws BadInitializationException {
		
		notifyTest("testInitialization_2\n");
		
		LIrcAntTask liat = new LIrcAntTask();
		
		assertEquals( liat.getHostName(), LIrcAntTask.DEFAULT_HOST );
		assertEquals( liat.getHostIpv4(), LIrcAntTask.DEFAULT_V4ADDRESS );
		assertEquals( liat.getServicePort(), LIrcAntTask.DEFAULT_PORT );
		assertEquals( liat.getChannel(), LIrcAntTask.DEFAULT_CHANNEL );
		assertEquals( liat.getNickname(), LIrcAntTask.DEFAULT_NICK );
		assertEquals( liat.getAlternativeNickname1(), LIrcAntTask.DEFAULT_ALTER_NICK_1 );
		assertEquals( liat.getAlternativeNickname2(), LIrcAntTask.DEFAULT_ALTER_NICK_2 );
		assertEquals( liat.getAlternativeNickname3(), LIrcAntTask.DEFAULT_ALTER_NICK_3 );
		assertEquals( liat.getMessage(), LIrcAntTask.DEFAULT_MESSAGE );
		
	}

	/**
	 * Tests a "bad" initialization with empty values
	 * @throws BadInitializationException
	 */
	@Test (expected=BadInitializationException.class)
	public void testInitialization_3() throws BadInitializationException {
		
		notifyTest("testInitialization_3\n");
		
		String hostname = "";
		String ipv4 = "";
		int port = 6668;
		boolean ssl = false;
		
		new LIrcAntTask( hostname, ipv4, port, ssl );
	
	}
	
	/**
	 * Tests the toString() description of the object
	 * 
	 * @throws BadInitializationException 
	 */
	@Test
	public void testToString() throws BadInitializationException {
		
		notifyTest("testToString\n");
		
		String hostname = "a_mock_host_name";
		String ipv4 = "192.168.10.1";
		int port = 6668;
		boolean ssl = false;
		
		LIrcAntTask liat = new LIrcAntTask( hostname, ipv4, port, ssl );
		liat.setAlternativeNickname1("Alice");
		liat.setAlternativeNickname2("Bob");
		liat.setAlternativeNickname3("June");
		liat.setChannel("#mockChan");
		liat.setMessage("Hello World !");
		
		MessagesDisplayer.displayOut(liat.toString());
		
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
