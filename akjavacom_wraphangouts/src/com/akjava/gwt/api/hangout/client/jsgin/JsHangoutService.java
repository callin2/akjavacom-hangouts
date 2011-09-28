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

import com.akjava.gwt.api.hangout.client.HangoutService;
import com.akjava.gwt.api.hangout.client.IAv;
import com.akjava.gwt.api.hangout.client.IHangout;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

public class JsHangoutService implements HangoutService{

	@Override
	public IHangout getHangout() {
		return JsHangout.getInstance();
	}

	@GinModules(JSHangoutModule.class)
	public interface HangoutGinJector extends Ginjector{
		HangoutService getHangoutService();
	}
	
	public static class JSHangoutModule extends AbstractGinModule {
		@Override
		protected void configure() {
			 bind(HangoutService.class).to(JsHangoutService.class);
		}
	}

	@Override
	public IAv getAv() {
		return JsAv.getInstance();
	}
}
