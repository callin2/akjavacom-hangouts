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
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LogBox extends VerticalPanel{
private List<String> lines=new ArrayList<String>();
private TextArea area;
private int maxLine=20;
public LogBox(){
	area = new TextArea();
	area.setSize("100%", "100%");
	add(area);
	Button clearBt=new Button("Clear");
	add(clearBt);
	clearBt.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			clear();
		}
	});
}
public void log(String line){
	lines.add(line);
	if(lines.size()>=maxLine){
		lines.remove(0);
	}
	StringBuilder builder=new StringBuilder();
	for(String nl:lines){
		builder.append(nl+"\n");
	}
	area.setText(builder.toString());
}

public void clear(){
	lines.clear();
	area.setText("");
}
}
