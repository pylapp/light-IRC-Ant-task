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

import java.util.ArrayList;
import java.util.List;

import pylapp.liat.ant.LIrcAntTask;
import pylapp.liat.utils.MessagesDisplayer;

import com.ircclouds.irc.api.Callback;
import com.ircclouds.irc.api.IRCApiImpl;
import com.ircclouds.irc.api.IServerParameters;
import com.ircclouds.irc.api.domain.IRCChannel;
import com.ircclouds.irc.api.domain.IRCServer;
import com.ircclouds.irc.api.state.IIRCState;

/**
 * Class used as a facade with the aim of providing main IRC features
 * through the {@link com.ircclouds.irc.api.IRCApiImpl} class as an
 * {@link pylapp.liat.irc.IrcFacade}. Uses the {@link pylapp.liat.ant.LIrcAntTask} object for retrieving
 * the IRC config. This class is based on the API developed by <b>Miguel Lebane</b> (miguel (at) lebane (dot) se).
 * 
 * @author Pierre-Yves LAPERSONNE (aka pylapp)
 * @version 1.1.0
 * @since 07/06/2013
 */
public class MIrcFacade extends IRCApiImpl implements IrcFacade {

	
	// ATTRIBUTES
	
	/**
	 * The object which handles the IRC config
	 */
	private LIrcAntTask configBundle;
	/**
	 * A flag which defines id the connection to the IRC host is done
	 */
	private boolean isConnected;
	/**
	 * The state of the object
	 */
	private States state;
	
	
	// CONSTRUCTOR
	
	/**
	 * Default constructor
	 */
	public MIrcFacade(){
		super( false );
		isConnected = false;
		state = States.INIT;
	}
	
	/**
	 * Constructor
	 * 
	 * @param saveIrcState -  A flag to allow saving the IRC state that will be obtained by
	 */
	public MIrcFacade( Boolean saveIrcState ){
		super( saveIrcState );
		isConnected = false;
		state = States.INIT;
	}

	
	// METHODS
	
	/**
	 * Disconnects from the channel _channel_ after sending an exit message
	 * 
	 * @param channel
	 * @param quitMessage
	 */
	@Override
	public void quitChannel( String channel, String quitMessage ){
		MessagesDisplayer.displayOut("[liat] Disconnecting from channel " + channel + "\n");
		Callback<String> cb = new MIrcCallback(); 
		super.leaveChannel( channel, quitMessage, cb  );
	}

	/**
	 * Sends the _message_ to the IRC _channel_
	 * 
	 * @param message
	 * @param channel
	 */
	@Override
	public void sendMessage( String message, String channel ){
		MessagesDisplayer.displayOut("[liat] Sending message to channel " + channel + "\n");
		Callback<String> cb = new MIrcCallback();
		act( channel, message, cb );
	}

	/**
	 * Connect to the _channel_
	 * 
	 * @param channel
	 */
	@Override
	public void joinChannel( String channel ){
		MessagesDisplayer.displayOut("[liat] Joining channel " + channel + "\n");
		Callback<IRCChannel> cb = new MIrcChannelCallback() ;
		super.joinChannel( channel, cb );
	}

	/**
	 * Establishes the IRC connection
	 */
	@Override
	public void connectToHost(){
		MessagesDisplayer.displayOut("[liat] Connecting to host " + configBundle.getHostName() + "\n");
		IServerParameters isp = new MIServerParameters();
		Callback<IIRCState> cb = new MIrcStateCallback();
		state = States.ON_GOING;
		connect(isp, cb);
	}

	/**
	 * Halts the IRC connection
	 * 
	 * @param quitMessage - An IRC quit message
	 */
	@Override
	public void disconnectFromHost( String quitMessage ){
		MessagesDisplayer.displayOut("[liat] Disconnecting from host " + configBundle.getHostName() + "\n");
		super.disconnect( quitMessage );
	}

	/**
	 * Possess the code to execute when the connection to the host is established.
	 * In this case, if this method is not redefined,  the class joins a channel
	 * specified in the _configBundle_ attribute, sends its message and disconnects from the chan and
	 * the host.
	 */
	public void process() {
		joinChannel(configBundle.getChannel());
		sendMessage(configBundle.getMessage(), configBundle.getChannel());
		quitChannel(configBundle.getChannel(), "Exiting the channel... Bye !");
		disconnectFromHost("Exiting the IRC server... Bye !" + "\n");
		isConnected = false;
		state = States.DONE;
	}
	
	
	// GETTERS AND SETTERS
	
	/**
	 * @return LIrcAntTask - The object which possesses the config
	 */
	public LIrcAntTask getConfigBundle(){
		return configBundle;
	}
	
	/**
	 * @param configBundle - The object which owns the config (if not null)
	 */
	public void setConfigBundle( LIrcAntTask configBundle ){
		if ( configBundle != null ) this.configBundle = configBundle;
		else this.configBundle = new LIrcAntTask();
	}
	
	/**
	 * @return boolean - If the connection to the host is established
	 */
	public boolean getIsConnected(){
		return isConnected;
	}
	
	/**
	 * @param isConnected - The flag to change
	 */
	public void setIsConnected( boolean isConnected ){
		this.isConnected = isConnected;
	}

	/**
	 * @return States - The state of the object
	 */
	public States getState(){
		return state;
	}
	
	/**
	 * @param state - The new state of the facade
	 */
	public void setState( States state ){
		this.state = state;
	}
	
	/*
	 * ENUMERATION 
	 */
	
	/**
	 * An enum which contains some states so as to describe the current action of
	 * the object and its IRC aspects.
	 */
	public enum States {
		/**
		 * Initial state of the object
		 */
		INIT,
		/**
		 * If the object is trying to connect to the host
		 */
		ON_GOING,
		/**
		 * When the _process_ method was called and executed
		 */
		DONE,
	}
	
	/*
	 * INNER CLASSES
	 */
	
	/**
	 * Custom inner classes for handling properly and safely IRC Server parameters
	 * 
	 * @see com.ircclouds.irc.api.IServerParameters
	 */
	private class MIServerParameters implements IServerParameters {

		@Override
		public List<String> getAlternativeNicknames() {
			List<String> altNickNames = new ArrayList<String>();
			altNickNames.add( configBundle.getAlternativeNickname1() );
			altNickNames.add( configBundle.getAlternativeNickname2() );
			altNickNames.add( configBundle.getAlternativeNickname3() );
			return altNickNames;
		}

		@Override
		public String getIdent() {
			return configBundle.getIdent();
		}

		@Override
		public String getNickname() {
			return configBundle.getNickname();
		}

		@Override
		public String getRealname() {
			return configBundle.getRealName();
		}

		@Override
		public IRCServer getServer() {
			 return new IRCServer( configBundle.getHostName(), configBundle.getIsSSLserver() );
		}
		
	}

	/**
	 * Custom inner class for handling the IRC callback launched within IRC
	 * (which concerns connection).
	 * 
	 * @see com.ircclouds.irc.api.Callback<IIRCState>
	 * @see com.ircclouds.irc.api.state.IIRCState
	 */
	private class MIrcStateCallback implements Callback<IIRCState> {

		@Override
		public void onFailure( Exception e ){
			MessagesDisplayer.displayErr( "[liat] Error with state : " + e.getMessage() + "\n" );
		}

		@Override
		public void onSuccess( IIRCState iircs ){
			MessagesDisplayer.displayOut( "[liat] Connected to host : " + iircs.getServer().getHostname() + "\n" );
			isConnected = true;
			process();
		}
		
	}
	
	/**
	 * Custom inner class for handling the IRC callback launched within IRC
	 * (callback which concerns channels).
	 * 
	 * @see com.ircclouds.irc.api.Callback<IRCChannel>
	 * @see com.ircclouds.irc.api.state.IRCChannel
	 */
	private class MIrcChannelCallback implements Callback<IRCChannel> {

		@Override
		public void onFailure( Exception e ){
			MessagesDisplayer.displayErr("[liat] Error with channel : " + e.getMessage() + "\n" );
		}

		@Override
		public void onSuccess( IRCChannel ircc ){
			MessagesDisplayer.displayOut( "[liat] Join a new channel : " + ircc.getName() + "\n" );
		}
		
	}
	
	/**
	 * Custom inner class for handling the IRC callback launched within IRC.
	 *  
	 * @see com.ircclouds.irc.api.Callback
	 */
	private class MIrcCallback implements Callback<String> {

		@Override
		public void onFailure( Exception e ){
			MessagesDisplayer.displayErr( "[liat] Error : " + e.getMessage() + "\n" );
		}

		@Override
		public void onSuccess( String s ){
			MessagesDisplayer.displayOut( "[liat] Success : " + s  + "\n");
		}
		
	}
	
}
