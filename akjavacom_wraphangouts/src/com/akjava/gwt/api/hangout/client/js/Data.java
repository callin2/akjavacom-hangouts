package com.akjava.gwt.api.hangout.client.js;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.api.hangout.client.js.handler.StateChangeListener;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class Data {
	
	/**
	 * 
	 * remove not support because in hangout.js
	 * if(c===null||c===void 0)c=[];
		(!c||!(c instanceof Array))&&f("opt_removes parameter of submitDelta must be an array.");
		i guess some context problem make error.i have no idea how to avoid it.
	 * @param updates
	 */
	
	
	
	public static final native void submitDelta(StringMap updates) /*-{
    $wnd.gapi.hangout.data.submitDelta(updates);
  	}-*/;
		
	public static final native StateMetadataMap getStateMetadata() /*-{
    return $wnd.gapi.hangout.data.getStateMetadata();
  	}-*/;
	
	public static final native StringMap getState() /*-{
    return $wnd.gapi.hangout.data.getState()
  	}-*/;
	
	private static native void registStateChangeListener()/*-{
		$wnd.gapi.hangout.data.addStateChangeListener(@com.akjava.gwt.api.hangout.client.js.Data::fireStateChangeListener(Lcom/akjava/gwt/api/hangout/client/js/StringMap;Lcom/google/gwt/core/client/JsArrayString;Lcom/akjava/gwt/api/hangout/client/js/StringMap;Lcom/akjava/gwt/api/hangout/client/js/StateMetadataMap;));
  }-*/;
	
	
 private static native void unregistStateChangeListener()/*-{
    $wnd.gapi.hangout.data.removeStateChangeListener(@com.akjava.gwt.api.hangout.client.js.Data::fireStateChangeListener(Lcom/akjava/gwt/api/hangout/client/js/StringMap;Lcom/google/gwt/core/client/JsArrayString;Lcom/akjava/gwt/api/hangout/client/js/StringMap;Lcom/akjava/gwt/api/hangout/client/js/StateMetadataMap;));
  }-*/;
 

 public synchronized static void addStateChangeListener(StateChangeListener listener){
	 if(stateChangeListeners==null){
		 stateChangeListeners=new ArrayList<StateChangeListener>();
		 stateChangeListeners.add(listener);
		 registStateChangeListener();
	 }else{
		 stateChangeListeners.add(listener);
	 }
 }
 public synchronized static void removeStateChangeListener(StateChangeListener listener){
	 if(stateChangeListeners==null){
		 
	 }else{
		 stateChangeListeners.remove(listener);
		 if(stateChangeListeners.size()==0){
			 unregistStateChangeListener();
			 stateChangeListeners=null;
		 }
	 }
 }
 
 private static List<StateChangeListener> stateChangeListeners;
 synchronized static void fireStateChangeListener(StringMap adds,JsArrayString removes,StringMap state,StateMetadataMap metadata){
	 for(StateChangeListener listener:stateChangeListeners){
		 listener.stateChange(adds, removes, state, metadata);
	 }
 }
}
