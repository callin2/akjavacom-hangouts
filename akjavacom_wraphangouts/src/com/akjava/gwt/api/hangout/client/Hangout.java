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
package com.akjava.gwt.api.hangout.client;

import java.util.List;

import com.akjava.gwt.api.hangout.client.js.handler.ApiReadyListener;
import com.akjava.gwt.api.hangout.client.js.handler.AppVisibleListener;


public class Hangout {
	
		private static HangoutService hangoutService;
		
		static HangoutService getHangoutService(){
			return hangoutService;
		}
		
		public static void setInjectHangoutService(HangoutService service) {
			hangoutService=service;
		}
		private static IHangout ihangout;
		private static IHangout getHangout(){
			if(ihangout==null){
				ihangout=getHangoutService().getHangout();
				return ihangout;
			}else{
				return ihangout;
			}
		}
		
		public static boolean isApiReady(){
			return getHangout().isApiReady();
		}
		/**
		 * this is slow because of wrapping jsobject
		 * @return
		 */
		public static List<Participant> getParticipants(){
			return getHangout().getParticipants();
		}
		public static void addAppVisibleListener(AppVisibleListener listener){
			getHangout().addAppVisibleListener(listener);
		}
		public static void removeApiReadyListener(ApiReadyListener listener){
			getHangout().removeApiReadyListener(listener);
		}
		 public static void addApiReadyListener(ApiReadyListener listener){
			 getHangout().addApiReadyListener(listener);
		 }
		
}
