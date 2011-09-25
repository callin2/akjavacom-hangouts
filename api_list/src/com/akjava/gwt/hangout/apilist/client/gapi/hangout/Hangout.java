package com.akjava.gwt.hangout.apilist.client.gapi.hangout;

import java.util.ArrayList;
import java.util.List;

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
}
