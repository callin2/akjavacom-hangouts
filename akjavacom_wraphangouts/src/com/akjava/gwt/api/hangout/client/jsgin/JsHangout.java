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
package com.akjava.gwt.api.hangout.client.jsgin;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.api.hangout.client.IHangout;
import com.akjava.gwt.api.hangout.client.js.Hangout;
import com.akjava.gwt.api.hangout.client.js.Participant;
import com.akjava.gwt.api.hangout.client.js.handler.ApiReadyListener;
import com.akjava.gwt.api.hangout.client.js.handler.AppVisibleListener;
import com.google.gwt.core.client.JsArray;

public class JsHangout implements IHangout{
	private static JsHangout jsHangout;
	private JsHangout(){}
	public static JsHangout getInstance(){
		if(jsHangout==null){
			jsHangout=new JsHangout();
		}
		return jsHangout;
	}

	public List<com.akjava.gwt.api.hangout.client.Participant> getParticipants() {
		JsArray<Participant> array=Hangout.getParticipants();
		List<com.akjava.gwt.api.hangout.client.Participant> participants=new ArrayList<com.akjava.gwt.api.hangout.client.Participant>();
		for(int i=0;i<array.length();i++){
			participants.add(array.get(i));
		}
		return participants;
	}
	
	public boolean isApiReady() {
		return Hangout.isApiReady();
	}
	
	public void addAppVisibleListener(AppVisibleListener listener){
		Hangout.addAppVisibleListener(listener);
	}
	@Override
	public void addApiReadyListener(ApiReadyListener listener) {
		Hangout.addApiReadyListener(listener);
	}
	@Override
	public void removeApiReadyListener(ApiReadyListener listener) {
		Hangout.removeApiReadyListener(listener);
	}
}
