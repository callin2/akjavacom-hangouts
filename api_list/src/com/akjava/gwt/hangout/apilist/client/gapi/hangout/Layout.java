package com.akjava.gwt.hangout.apilist.client.gapi.hangout;

public class Layout {
	private Layout(){}
	public static final native void setChatPaneVisible(boolean visible) /*-{
    $wnd.gapi.hangout.layout.setChatPaneVisible(visible);
    }-*/;
	public static  final native boolean isChatPaneVisible() /*-{
    return $wnd.gapi.hangout.layout.isChatPaneVisible();
    }-*/;
	public static  final native boolean getParticipantHighlight(String hangoutId) /*-{
    return $wnd.gapi.hangout.layout.getParticipantHighlight(hangoutId);
    }-*/;
	public static  final native void setParticipantHighlight(String hangoutId) /*-{
    $wnd.gapi.hangout.layout.setParticipantHighlight(hangoutId);
    }-*/;
	public static  final native void clearParticipantHighlight(String hangoutId) /*-{
    $wnd.gapi.hangout.layout.clearParticipantHighlight(hangoutId);
    }-*/;
}
