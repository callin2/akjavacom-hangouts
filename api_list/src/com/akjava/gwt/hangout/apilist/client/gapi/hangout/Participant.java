/*
 * Copyright (C) 2011 aki@akjava.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.akjava.gwt.hangout.apilist.client.gapi.hangout;

import com.google.gwt.core.client.JavaScriptObject;

public class Participant extends JavaScriptObject {

	protected Participant(){}
	
	 public final native String getHangoutId() /*-{
	    return this.hangoutId;
	  }-*/;
	 
	 public final native boolean hasAppInstalled() /*-{
	    return this.hasAppInstalled;
	  }-*/;
	 
	 public final native String getId() /*-{
	    return this.id;
	  }-*/;
	 
	 public final native String getDisplayName() /*-{
	    return this.displayName;
	  }-*/;
	 
	 public final native String getImageUrl() /*-{
	    return this.image.url;
	  }-*/;
}
