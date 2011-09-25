package com.akjava.gwt.hangout.apilist.client.gapi.hangout;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class State extends JavaScriptObject{
	protected State(){}
	
	public final native JsArrayString keys()/*-{
    var array=new Array();
    var index=0;
    for(var key in this){
    array[index]=key;
    index++;	
    }
    return array;
    }-*/;
	
	public final native String get(String key) /*-{
    return this[key];
  	}-*/;
	
	public final native void set(String key,String value) /*-{
    this[key]=value;
  	}-*/;
	
	
	public static final native State getState() /*-{
    return $wnd.gapi.hangout.data.getState()
  	}-*/;
	
	/**
	 * i faild to implements removes
	 * @param updates
	 */
	public static final native void submitDelta(State updates) /*-{
    $wnd.gapi.hangout.data.submitDelta(updates);
  	}-*/;
	
	
}
