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
