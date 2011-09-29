package com.akjava.gwt.api.hangout.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class StateMetadata extends JavaScriptObject{

	protected StateMetadata(){}
	
	public final native String getKey() /*-{
    return this["key"];
  	}-*/;
	public final native String getValue() /*-{
    return this["value"];
  	}-*/;
	public final native double getTimediff() /*-{
    return this["timediff"];
  	}-*/;
	public final native double getTimestamp() /*-{
    return this["timestamp"];
  	}-*/;
}
