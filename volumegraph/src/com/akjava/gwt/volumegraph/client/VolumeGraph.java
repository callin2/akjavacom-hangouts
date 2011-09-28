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
package com.akjava.gwt.volumegraph.client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akjava.gwt.api.hangout.client.Av;
import com.akjava.gwt.api.hangout.client.Hangout;
import com.akjava.gwt.api.hangout.client.js.NumberMap;
import com.akjava.gwt.api.hangout.client.js.handler.ApiReadyListener;
import com.akjava.gwt.api.hangout.client.js.handler.VolumesChangedListener;
import com.akjava.gwt.api.hangout.client.jsgin.JsHangoutService.HangoutGinJector;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
@ModulePrefs(//
		title = "Volume Graph",width=550,height=200)
		// Create a short manifest name (instead of prepending the package prefix)

		@UseLongManifestName(false)
		@AllowHtmlQuirksMode(false)
public class VolumeGraph extends Gadget<TestPreference> implements SupportFeature{
	private final HangoutGinJector injector = GWT.create(HangoutGinJector.class);
	@Override
	protected void init(TestPreference preferences) {

		Hangout.setInjectHangoutService(injector.getHangoutService());
		StyleInjector.inject(".grey{color:#666}");
		
		HorizontalPanel root=new HorizontalPanel();
		root.setSize("100%", "100%");
		RootPanel.get().add(root);
		
		VerticalPanel mainPanel=new VerticalPanel();
		mainPanel.setSize("100%","100%");
		
		HorizontalPanel controler=new HorizontalPanel();
		mainPanel.add(controler);
		
		Label label=new Label("Emulate Participant");
		label.setStylePrimaryName("grey");
		controler.add(label);
		
		Button plus=new Button("+");
		plus.setStylePrimaryName("grey");
		controler.add(plus);
		plus.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dummyParticipant++;
			}
		});
		Button clear=new Button("remove");
		clear.setStylePrimaryName("grey");
		controler.add(clear);
		clear.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dummyParticipant=0;
			}
		});
		
		
		root.add(mainPanel);
		
	
		canvas = Canvas.createIfSupported();
		
		canvas.setSize("100%", "400px");
		canvas.getContext2d().setFillStyle(CssColor.make("#fff"));
		
		mainPanel.add(canvas);
		int w=canvas.getOffsetWidth();
		int h=canvas.getOffsetHeight();
		canvas.setCoordinateSpaceWidth(w);
		canvas.setCoordinateSpaceHeight(h);
		
		
		addReady();
	}
	int dummyParticipant;
	long vindex=0;
	
	
	Map<String,String> colorMap=new HashMap<String,String>();
	List<String> colors=new ArrayList<String>();
	int colorIndex;
	
	private  String getColor(String key){
		String color=colorMap.get(key);
		if(color==null){
			color=colors.get(colorIndex);
			colorMap.put(key, color);
			colorIndex++;
			if(colorIndex>=colors.size()){
				colorIndex=0;
			}
		}
		return color;
	}
	
	private void addVolumesChangedListener(){
		colors.add(alphaColor(200,255,0,0.7));
		colors.add(alphaColor(0,200,255,0.7));
		colors.add(alphaColor(200,0,255,0.7));
		
		
		colors.add(alphaColor(255,200,200,0.7));
		colors.add(alphaColor(255,200,200,0.7));
		colors.add(alphaColor(200,255,200,0.7));
		
		colors.add(alphaColor(0,0,255,0.7));
		
		colors.add(alphaColor(0,255,0,0.7));
		colors.add(alphaColor(255,0,0,0.7));
		colors.add(alphaColor(255,255,0,0.7));
		colors.add(alphaColor(255,0,255,0.7));
		colors.add(alphaColor(0,255,255,0.7));
		

	
		
		colors.add(alphaColor(255,200,0,0.7));
		colors.add(alphaColor(255,0,200,0.7));
		colors.add(alphaColor(0,255,200,0.7));
		
		
		
		final ColorPainter painter=new ColorPainter(canvas,80);
		volumeChangeListener=new VolumesChangedListener(){
			
			@Override
			public void volumeChanged(NumberMap volumeMap) {
				
				
				painter.clear();
				
				//dummys
				for(int i=0;i<dummyParticipant;i++){
					volumeMap.put("dummy"+i, (int) (Math.random()*5));
				}
				JsArrayString keys=volumeMap.keys();
				
			
				for(int i=0;i<keys.length();i++){
					String key=keys.get(i);
					int num=(int) volumeMap.get(keys.get(i));
					painter.update(num,getColor(key));
					
					
				}	
				painter.increment();
			
			}
			
		};
		Av.addVolumesChangedListener(volumeChangeListener);
	}
	
	public static String alphaColor(int r,int g,int b,double a){
		return "rgba("+r+","+g+","+b+","+a+")";
	}
	public class ColorPainter{
		int pw;
		int currentIndex;
		int maxSplit;

		Canvas canvas;
		int height;
		CssColor bgColor=CssColor.make("#fff");
		public ColorPainter(Canvas canvas,int split){
			this.canvas=canvas;
			maxSplit=split;
			int w=canvas.getOffsetWidth();
			height=canvas.getOffsetHeight();
			pw=(int)w/split;
			
			canvas.getContext2d().setFillStyle(bgColor);
			canvas.getContext2d().fillRect(0, 0, w, height);

		}
		public void clear() {
			int sx=(int) (currentIndex*pw);
			int sy=0;
			canvas.getContext2d().setFillStyle(bgColor);
			canvas.getContext2d().fillRect(sx, sy, pw, height);
		}
		public void update(int num,String color){
			int sx=(int) (currentIndex*pw);
			int sy=0;
			if(num==0){
				
			}else{
				sy=height-(height/5)*num;
				if(currentIndex%2==0){
				
					canvas.getContext2d().setFillStyle(color);
				}else{
				canvas.getContext2d().setFillStyle(color);
				}
				canvas.getContext2d().fillRect(sx, sy, pw, height);

			}
			
		}
		public void increment(){
			currentIndex++;
			if(currentIndex>=maxSplit){
				currentIndex=0;
			}
		}
	}
	
	VolumesChangedListener volumeChangeListener;
	ApiReadyListener listener;
	private Canvas canvas;
	

	public void addReady(){
		listener=new ApiReadyListener() {
			@Override
			public void apiReadyChanged(boolean bool) {
				addVolumesChangedListener();
			}
		};
		Hangout.addApiReadyListener(listener);
	}
	
}
