package com.akjava.gwt.api.hangout.client.jsgin;

import com.akjava.gwt.api.hangout.client.js.State;
import com.akjava.gwt.api.hangout.client.js.StringMap;

public class JsState {
	private static JsState jsState;
	private JsState(){}
	public static JsState getInstance(){
		if(jsState==null){
			jsState=new JsState();
		}
		return jsState;
	}

	public static  void submitDelta(StringMap updates){
		State.submitDelta(updates);
	}
}
