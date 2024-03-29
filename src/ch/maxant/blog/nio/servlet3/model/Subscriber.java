/*  
 * Copyright (c) 2011 Ant Kutschera
 * 
 * This file is part of Ant Kutschera's blog, 
 * http://blog.maxant.co.uk
 * 
 * This is free software: you can redistribute
 * it and/or modify it under the terms of the
 * Lesser GNU General Public License as published by
 * the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 * 
 * It is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the Lesser GNU General Public License for
 * more details. 
 * 
 * You should have received a copy of the
 * Lesser GNU General Public License along with this software.
 * If not, see http://www.gnu.org/licenses/.
 */
package ch.maxant.blog.nio.servlet3.model;

import javax.servlet.AsyncContext;

public class Subscriber {

	private String channel;
	private final AsyncContext aCtx;
	
	public Subscriber(AsyncContext aCtx, String channel) {
		this.aCtx = aCtx;
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}
	
	public AsyncContext getaCtx() {
		return aCtx;
	}
}
