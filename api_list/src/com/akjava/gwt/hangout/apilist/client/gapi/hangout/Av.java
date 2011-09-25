package com.akjava.gwt.hangout.apilist.client.gapi.hangout;

public class Av {
	public static final native boolean hasMicrophone() /*-{
    return $wnd.gapi.hangout.av.hasMicrophone();
    }-*/;
	public static final native boolean hasCamera() /*-{
    return $wnd.gapi.hangout.av.hasCamera();
    }-*/;
	public static final native boolean hasSpeakers() /*-{
    return $wnd.gapi.hangout.av.hasSpeakers();
    }-*/;
	
	public static final native boolean getMicrophoneMute() /*-{
    return $wnd.gapi.hangout.av.getMicrophoneMute();
    }-*/;
	
	public static final native void setMicrophoneMute(boolean mute) /*-{
     $wnd.gapi.hangout.av.setMicrophoneMute(mute);
    }-*/;
	
	public static final native boolean getCameraMute() /*-{
    return $wnd.gapi.hangout.av.getCameraMute();
    }-*/;
	
	public static final native void setCameraMute(boolean mute) /*-{
    $wnd.gapi.hangout.av.setCameraMute(mute);
   }-*/;

	public static final native double getParticipantVolume(String hangoutId) /*-{
    return $wnd.gapi.hangout.av.getParticipantVolume(hangoutId);
   }-*/;
	
	public static final native boolean isParticipantVisible(String hangoutId) /*-{
    return $wnd.gapi.hangout.av.isParticipantVisible(hangoutId);
    }-*/;
	
	public static final native void setParticipantVisible(String hangoutId,boolean visible) /*-{
    $wnd.gapi.hangout.av.setParticipantVisible(hangoutId,visible);
   }-*/;
	
	public static final native String getAvatar(String hangoutId) /*-{
    return $wnd.gapi.hangout.av.getAvatar(hangoutId);
   }-*/;
	
	public static final native void setAvatar(String hangoutId,String imageUrl) /*-{
    $wnd.gapi.hangout.av.setAvatar(hangoutId,imageUrl);
   }-*/;
	
	public static final native void clearAvatar(String hangoutId) /*-{
    $wnd.gapi.hangout.av.clearAvatar(hangoutId);
   }-*/;
}
