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
package com.akjava.gwt.api.hangout.client.js;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.api.hangout.client.js.handler.ApiReadyListener;
import com.akjava.gwt.api.hangout.client.js.handler.AppVisibleListener;
import com.google.gwt.core.client.JsArray;

public class Hangout {
	private Hangout(){}
	 public static native JsArray<Participant> getParticipants() /*-{
	    return $wnd.gapi.hangout.getParticipants();
	  }-*/;
	 
	 public static List<Participant> getParticipantsAsList(){
		 List<Participant> lists=new ArrayList<Participant>();
		 JsArray<Participant> array=getParticipants();
		 for(int i=0;i<array.length();i++){
			 lists.add(array.get(i));
		 }
		 return lists;
	 }
	 
	 public static native Participant getParticipantById(String id) /*-{
	    return $wnd.gapi.hangout.getParticipantById(id);
	  }-*/;
	 
	 public static native boolean isApiReady() /*-{
	    return $wnd.gapi.hangout.isApiReady();
	  }-*/;
	 
	 public static native boolean isAppVisible() /*-{
	    return $wnd.gapi.hangout.isAppVisible();
	  }-*/;
	 
	 public static native void hideApp() /*-{
	    return $wnd.gapi.hangout.hideApp();
	  }-*/;
	 
	 public static native String getHangoutId() /*-{
	    return $wnd.gapi.hangout.getHangoutId();
	  }-*/;
	 
	 public static native String getLocale() /*-{
	    return $wnd.gapi.hangout.getLocale();
	  }-*/;
	 
	 public static native String getParticipantId() /*-{
	    return $wnd.gapi.hangout.getParticipantId();
	  }-*/;
	 
	 public static native boolean hasNotice() /*-{
	    return $wnd.gapi.hangout.hasNotice();
	  }-*/;
	 
	 public static native void displayNotice(String message,boolean opt_permanent) /*-{
	    return $wnd.gapi.hangout.displayNotice(message,opt_permanent);
	  }-*/;
	 public static native void dismissNotice() /*-{
	    return $wnd.gapi.hangout.dismissNotice();
	  }-*/;
	 
	 public static native String getActiveSpeaker() /*-{
	    return $wnd.gapi.hangout.getActiveSpeaker();
	  }-*/;
	 
	 public static native void setActiveSpeaker(String id) /*-{
	    return $wnd.gapi.hangout.setActiveSpeaker(id);
	  }-*/;
	 
	 public static native void clearActiveSpeaker() /*-{
	    $wnd.gapi.hangout.clearActiveSpeaker();
	  }-*/;
	 
	 private static native void registAppVisibleListener()/*-{
	    $wnd.gapi.hangout.addAppVisibleListener(@com.akjava.gwt.api.hangout.client.js.Hangout::fireAppVisibleListener(Z));
	  }-*/;
	 

	 public static void addAppVisibleListener(AppVisibleListener listener){
		 if(appVisibleListeners==null){
			 appVisibleListeners=new ArrayList<AppVisibleListener>();
			 appVisibleListeners.add(listener);
			 registAppVisibleListener();
		 }else{
		 appVisibleListeners.add(listener);
		 }
	 }
	 
	 private static List<AppVisibleListener> appVisibleListeners;
	 synchronized static void fireAppVisibleListener(boolean bool){
		 for(AppVisibleListener listener:appVisibleListeners){
			 listener.appVisibleChanged(bool);
		 }
	 }
	 
	 private static native void registApiReadyListener()/*-{
	    //$wnd.gapi.hangout.addApiReadyListener(@com.akjava.gwt.api.hangout.client.js.Hangout::fireApiReadyListener());
	  	$wnd.gapi.hangout.addApiReadyListener(@com.akjava.gwt.api.hangout.client.js.Hangout::fireApiReadyListener(Z));
	  }-*/;
	 private static native void unregistApiReadyListener()/*-{
	    $wnd.gapi.hangout.removeApiReadyListener(@com.akjava.gwt.api.hangout.client.js.Hangout::fireApiReadyListener(Z));
	  }-*/;
	 

	 public static void addApiReadyListener(ApiReadyListener listener){
		 if(apiReadyListeners==null){
			 apiReadyListeners=new ArrayList<ApiReadyListener>();
			 apiReadyListeners.add(listener);
			 registApiReadyListener();
		 }else{
			 apiReadyListeners.add(listener);
		 }
	 }
	 public static void removeApiReadyListener(ApiReadyListener listener){
		 if(apiReadyListeners==null){
			 
		 }else{
			 apiReadyListeners.remove(listener);
			 if(apiReadyListeners.size()==0){
				 unregistApiReadyListener();
				 apiReadyListeners=null;
			 }
		 }
	 }
	 
	 private static List<ApiReadyListener> apiReadyListeners;
	 synchronized static void fireApiReadyListener(boolean bool){
		 for(ApiReadyListener listener:apiReadyListeners){
			 listener.apiReadyChanged(bool);
		 }
	 }

}
