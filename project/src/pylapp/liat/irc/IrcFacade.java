/*
 * Software Name: Little IRC Ant Task (liat)
 * SPDX-FileCopyrightText: Copyright (c) 2013 Pierre-Yves LAPERSONNE (aka pylapp)
 * SPDX-License-Identifier: GPL-3.0
 *
 * This software is distributed under the GNU General Public License v3.0 only.
 *
 * Author: Pierre-Yves LAPERSONNE (aka pylapp) <dev(at)pylapersonne(dot)info> et al.
 */
 
package pylapp.liat.irc;

/**
 * Facade which provides main IRC methods like sending a message, join a channel etc.
 * 
 * @author Pierre-Yves LAPERSONNE (aka pylapp)
 * @version 1.0.0
 * @since 07/06/2013
 */
public interface IrcFacade {
	
	/**
	 * Should make the connection to an IRC server
	 */
	public void connectToHost();
	
	/**
	 * Should join the IRC channel _channel_
	 * @param channel
	 */
	public void joinChannel( String channel );

	/**
	 * Should quit the IRC channel _channel_
	 * @param channel
	 * @param quitMessage
	 */
	public void quitChannel( String channel, String quitMessage  );
	
	/**
	 * Should send the _message_ to the IRC channel _channel_
	 * @param message
	 * @param channel
	 */
	public void sendMessage( String message, String channel );

	/**
	 * Should shut down the connection to an IRC server
	 * @param quitMessage
	 */
	public void disconnectFromHost( String quitMessage );
	
}
