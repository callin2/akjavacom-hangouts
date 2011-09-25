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
