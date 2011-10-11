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
package com.akjava.gwt.threetest.client;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.renderers.WebGLRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT.create(MainWidgetUiBinder.class);

	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> {
	}

	private Demo lastDemo;
	
	final Demo[] demos=new Demo[]{
			new PickDemo(),new ShadowDemo(),new TextureDemo(),
			new CylinderDemo(),new PlainDemo(),new SimpleDemo(),new SphereDemo()};
	int width=500,height=500;
	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		renderer = THREE.WebGLRenderer();
		renderer.setSize(width, height);
		GWT.log("element:"+renderer.getDomElement());
		
		
		HTMLPanel div=new HTMLPanel("");
		div.getElement().appendChild(renderer.getDomElement());
		focusPanel = new FocusPanel();
		focusPanel.add(div);
		
		
		
		getMain().add(focusPanel);
		
		for(int i=0;i<demos.length;i++){
			DemoButton demoButton=new DemoButton(demos[i]);
			side.add(demoButton);
			if(i==0){
				demoButton.startDemo();
			}
		}
		
	}

@UiField VerticalPanel main,side;
private WebGLRenderer renderer;

private FocusPanel focusPanel;

	


public VerticalPanel getMain(){
return main;
}


public class DemoButton extends Button implements ClickHandler{
	Demo demo;
public DemoButton(Demo demo){
	super();
	this.demo=demo;
	setText(demo.getName());
	this.addClickHandler(this);
}
@Override
public void onClick(ClickEvent event) {
	startDemo();
}
public void startDemo(){
	if(lastDemo!=null){
		lastDemo.stop();
	}
	demo.start(renderer,width,height,focusPanel);
	lastDemo=demo;
}
	
}



}
