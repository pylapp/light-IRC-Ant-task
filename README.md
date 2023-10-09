[![Opened issues](https://img.shields.io/github/issues-raw/pylapp/light-IRC-Ant-task?style=for-the-badge)](https://github.com/pylapp/light-IRC-Ant-task/issues)
[![GNU GPL3 license](https://img.shields.io/github/license/pylapp/light-IRC-Ant-task?style=for-the-badge)](https://github.com/pylapp/light-IRC-Ant-task/blob/master/LICENSE.txt)
[![Still maintained](https://img.shields.io/maintenance/no/2013?style=for-the-badge)](https://github.com/pylapp/light-IRC-Ant-task/issues?q=is%3Aissue+is%3Aclosed)
[![Code size](https://img.shields.io/github/languages/code-size/pylapp/light-IRC-Ant-task?style=for-the-badge)](https://github.com/pylapp/light-IRC-Ant-task)

[![Java](https://img.shields.io/badge/-Java-701516?style=for-the-badge)](https://github.com/pylapp/light-IRC-Ant-task/search?l=ruby)

# Little IRC Ant Task

## In few words

A simple / little / light / dumb library implemented so as to provide a custom _Ant_ task with the aim of using _IRC_ tools.
With this tool you can connect to an host, join a channel, send a message, leave a channel and disconnect from the host.

It allows users to use _IRC_ features quite simply through their own _Ant_ script (build.xml). With this code, they are able to use a special task which contains the host, the channel and the message to send for example. The tool is based on the "irc-api" developed by _Miguel Lebane_ for the _IRC_ part, and also based on the _ANT_ Aoache lib so as to provide a task.

This project is under _GPL3 license_, and tested with _JUnit_.

## How to use

For using this tool:
* You should use the .jar lib available in release archive
* Then you should in your own _Ant_ script build.xml use the task like bellow

To use it:
```xml
<taskdef name="irc" classname="pylapp.liat.ant.LIrcAntTask"/>
<irc 
      hostname="irc.myserver.org"
      servicePort="6667"
      isSSLServer="true"
      channel="#channel"
      nickname="irc nickname"
      alternativeNickname1="first choice alternative nick"
      alternativeNickname2="second choice alternative nick"
      alternativeNickname3="third choice alternative nick"
      realName="irc real name"
      ident="irc ident"
      message="the message to send"
/>
```

## Disclaimer

This project was implemented and released in 2013 on [Google Code](https://code.google.com/archive/p/liat/) (and versioned with SubVersioN as VCS).
Because this platform has been shutdown the source code has been migrated to GitHub (with Git as SCM) so as to keep the source code available.
The code remains here as is, without any waranty, is not maintained anymore but still exists!