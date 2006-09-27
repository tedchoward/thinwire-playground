                      ThinWire(TM) Playground Demo
              Copyright (C) 2003-2006 Custom Credit Systems
   
 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 2 of the License, or (at your option) any later
 version.
   
 This program is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
   
 You should have received a copy of the GNU General Public License along with
 this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 Place, Suite 330, Boston, MA 02111-1307 USA
  
 Users wishing to use this library in proprietary products which are not 
 themselves to be released under the GNU Public License should contact Custom
 Credit Systems for a license to do so.
   
               Custom Credit Systems, Richardson, TX 75081, USA.
                          http://www.thinwire.com

===============================================================================
	                     About ThinWire(R) Playground Demo	
===============================================================================
The Playground Demo allows you to interact with most of the UI components that
ThinWire(R) includes. When we say interact, we're not talking about just being
able to look at a component and click on a button or two. Playground goes
beyond the basics in a number of ways:

    * Select a component and the applicable help file is available
    * Modify component properties and styles and see the result
    * Acivate event listeners and see real-time event output
    * Generate source code for the exact component you've configured

===============================================================================
                           Building the Playground Demo
===============================================================================
The build process for the demo is defined using the Apache Ant build tool. It
has only been built using Ant 1.6 or greater, but it may build correctly with
earlier releases as well.  You can learn about the Apache Ant project and
download a working version from: http://ant.apache.org/

Once you have Ant installed and added to your system path, you can build the
demo simply by typing 'ant dist' at the command shell from the 'build'
directory.  The following Ant build targets are supported:

 dist        compile the demo, create a jar and package
	         it along with other required runtime files into
             a distribution zip.

 dist14      compile a Java 1.4 compatible version of the
             demo, create a jar and package it along with
             other required runtime files into a distribution
             zip.
	
 source      create a source only distribution that contains
             everything necessary to build the demo.
