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
package com.akjava.gwt.hangout.apilist.client;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.hangout.apilist.client.gapi.hangout.Av;
import com.akjava.gwt.hangout.apilist.client.gapi.hangout.Hangout;
import com.akjava.gwt.hangout.apilist.client.gapi.hangout.Layout;
import com.akjava.gwt.hangout.apilist.client.gapi.hangout.Participant;
import com.akjava.gwt.hangout.apilist.client.gapi.hangout.State;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */



@ModulePrefs(//
		title = "Hangouts API List",width=550,height=200)
		// Create a short manifest name (instead of prepending the package prefix)
		

		@UseLongManifestName(false)
		@AllowHtmlQuirksMode(false)
public class APILists extends Gadget<TestPreference> implements SupportFeature {

	@Override
	protected void init(TestPreference preferences) {
		//create css
		StyleInjector.inject(".bold{font-weight:bold;color:#666;}");
ScriptElement script=createScriptElement("http://hangoutsapi.appspot.com/static/hangout.js",null);
		
		HTML html=new HTML();
		html.getElement().insertFirst(script);
		RootPanel.get().add(html);
		
		TabPanel tab=new TabPanel();
		RootPanel.get().add(tab);
		
		
		ParticipantsPanel participantsPanel=new ParticipantsPanel();
		tab.add(participantsPanel,"Participants");
		
		HangoutPanel hangoutPanel=new HangoutPanel();
		tab.add(hangoutPanel,"Hangout");
		
		
		NoticePanel noticePanel=new NoticePanel();
		tab.add(noticePanel,"Notice");
		
		
		ActiveSpeakerPanel activeSpeakerPanel=new ActiveSpeakerPanel();
		tab.add(activeSpeakerPanel,"Speaker");
		
		
		LayoutPanel laytouPanel=new LayoutPanel();
		tab.add(laytouPanel,"layout");
		
		
		StatePanel statePanel=new StatePanel();
		tab.add(statePanel,"state");
		
		
		AVPanel avPanel=new AVPanel();
		tab.add(avPanel,"AV");
		
		
		tab.selectTab(0);
	}
	
	public class ParticipantsPanel extends VerticalPanel{
	
		private FlexTable table;
		public ParticipantsPanel(){
			Button bt=new Button("update");
			bt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					update();
				}
			});
			add(bt);
			
			table = new FlexTable();
			table.setBorderWidth(1);
			table.setHTML(0, 0, "<b>hangoutid</b>");
			table.setHTML(0, 1, "<b>installed</b>");
			
			table.setHTML(0, 2, "<b>google+ id</b>");
			table.setHTML(0, 3, "<b>displayName</b>");
			table.setHTML(0, 4, "<b>image-url</b>");
			add(table);
			 
		}
		public void update(){
			table.clear();
			table.setHTML(0, 0, "<b>hangoutid</b>");
			table.setHTML(0, 1, "<b>installed</b>");
			
			table.setHTML(0, 2, "<b>google+ id</b>");
			table.setHTML(0, 3, "<b>displayName</b>");
			table.setHTML(0, 4, "<b>image-url</b>");
			
			JsArray<Participant> parts=Hangout.getParticipants();
			int size=parts.length();
			
			
			
			
			for(int i=0;i<size;i++){
				Participant p=parts.get(i);
				int index=i+1;
				table.setText(index, 0, p.getHangoutId());
				table.setText(index, 1, ""+p.hasAppInstalled());
			
				table.setText(index, 2, p.getId());
				table.setText(index, 3, p.getDisplayName());
				table.setText(index, 4, p.getImageUrl());
			}
		}
	}
	
	private Label boldLabel(String text){
		Label label=new Label(text);
		label.setStylePrimaryName("bold");
		return label;
	}
	
	public class NoticePanel extends VerticalPanel{
		
		private Label hasNoticeLabel;

		public NoticePanel(){
			add(boldLabel("[Show Notice]"));
			hasNoticeLabel = new Label();
			add(hasNoticeLabel);
			
			final TextBox inputBox=new TextBox();
			inputBox.setText("hello world!");
			add(inputBox);
			
			HorizontalPanel control=new HorizontalPanel();
			add(control);
			final CheckBox check=new CheckBox();
			
			Button noticeBt=new Button("notice");
			noticeBt.setWidth("100px");
			noticeBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Hangout.displayNotice(inputBox.getText(), check.getValue());
					hasNoticeLabel.setText(""+Hangout.hasNotice());
				}
			});
			
			control.add(noticeBt);
			control.add(check);
			control.add(new Label("Permanent"));
			
			Button dismissBt=new Button("dismiss");
			dismissBt.setWidth("100px");
			dismissBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Hangout.dismissNotice();
					hasNoticeLabel.setText(""+Hangout.hasNotice());
				}
			});
			add(dismissBt);
			
		}
		
	}
	
public class ParticipantButton extends VerticalPanel{
	private Participant p;
	private Button bt;
	
	public ParticipantButton(Participant p,String label){
		this.p=p;
		bt=new Button(label);
		add(bt);
	}
	public ParticipantButton(Participant p){
		this(p,p.getDisplayName());
	}
	public String getHangoutId(){
		return p.getHangoutId();
	}
	public Button getButton(){
		return bt;
	}
}
public class ActiveSpeakerPanel extends VerticalPanel{
		
		private Label activeSpeaker;
		private VerticalPanel list;
		public ActiveSpeakerPanel(){
			Button noticeBt=new Button("update!");
			noticeBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String id=Hangout.getActiveSpeaker();
					Participant p=Hangout.getParticipantById(id);
					activeSpeaker.setText(""+p.getDisplayName());
					list.clear();
					
					JsArray<Participant> parts=Hangout.getParticipants();
					int size=parts.length();
					for(int i=0;i<size;i++){
						Participant p1=parts.get(i);
						ParticipantButton bt=new ParticipantButton(p1);
						bt.getButton().addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								Button b=(Button) event.getSource();
								ParticipantButton pb=(ParticipantButton) b.getParent();
								Hangout.setActiveSpeaker(pb.getHangoutId());
							}
						});
						list.add(bt);
						
						
					}
					
				}
			});
			add(noticeBt);
			add(new Label("[Active]"));
			activeSpeaker = new Label();
			add(activeSpeaker);
			
			add(new Label("[Participant]"));
			list=new VerticalPanel();
			add(list);
			
			
			Button clear=new Button("clear");
			clear.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Hangout.clearActiveSpeaker();
				}
			});
			add(clear);
			
			
		}
		
	}
	
public class LayoutPanel extends VerticalPanel{
	private Label chat;
	VerticalPanel vpanel;
	public LayoutPanel(){
	Button bt=new Button("update");
	
	add(bt);
		bt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				update();
			}
		});
		
		add(new Label("[chat]"));
		chat = new Label();
		add(chat);	
	HorizontalPanel control=new HorizontalPanel();
	add(control);
	Button show=new Button("show");
	control.add(show);
		show.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Layout.setChatPaneVisible(true);
			}
		});
		Button hide=new Button("hide");
		control.add(hide);
		hide.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Layout.setChatPaneVisible(false);
				}
			});
		
		add(new Label("[highlights]"));
		vpanel=new VerticalPanel();	
		add(vpanel);
	
		
	}
	
	private void update(){
		chat.setText(""+Layout.isChatPaneVisible());
		vpanel.clear();
		JsArray<Participant> parts=Hangout.getParticipants();
		int size=parts.length();
		for(int i=0;i<size;i++){
			Participant pa=parts.get(i);
			HorizontalPanel p=new HorizontalPanel();
			p.add(new Label("H:"+Layout.getParticipantHighlight(pa.getHangoutId())));
			ParticipantButton bt=new ParticipantButton(pa);
			bt.getButton().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Button b=(Button) event.getSource();
					ParticipantButton pb=(ParticipantButton) b.getParent();
					Layout.setParticipantHighlight(pb.getHangoutId());
				}
			});
			p.add(bt);
			
			ParticipantButton clear=new ParticipantButton(pa,"clear");
			clear.getButton().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Button b=(Button) event.getSource();
					ParticipantButton pb=(ParticipantButton) b.getParent();
					Layout.clearParticipantHighlight(pb.getHangoutId());
				}
			});
			p.add(clear);
			
			
			vpanel.add(p);
		}
	}
}

public class StatePanel extends VerticalPanel{
	private VerticalPanel valuePanel;

	public StatePanel(){
		add(new Label("state"));
		Button bt=new Button("update");
		add(bt);
		bt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				update();
			}
		});
		
		HorizontalPanel addPanel=new HorizontalPanel();
		add(addPanel);
		final TextBox key=new TextBox();
		addPanel.add(key);
		final TextBox value=new TextBox();
		addPanel.add(value);
		Button addBt=new Button("add");
		addPanel.add(addBt);
		addBt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				State state=(State) State.createObject();
				state.set(key.getText(), value.getText());
				State.submitDelta(state);
			}
		});
		
		/*
		HorizontalPanel removePanel=new HorizontalPanel();
		add(removePanel);
		final TextBox remove_key=new TextBox();
		removePanel.add(remove_key);
		
		
		Button removeBt=new Button("remove");
		removePanel.add(removeBt);
		removeBt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				JsArrayString array=(JsArrayString) JsArrayString.createObject();
				array.setLength(0);
				array.push(remove_key.getText());
				valuePanel.add(new Label(""+array.get(0)));
				array.setLength(1);
				
				State state=(State) State.createObject();
				state.set("a", "");
				State state1=(State) State.createObject();
				State.removeDelta(state1);
			}
		});*/
		
		
		valuePanel = new VerticalPanel();
		add(valuePanel);
	}

	protected void update() {
		valuePanel.clear();
		State state=State.getState();
		if(state!=null){
			valuePanel.add(new Label("[states]"));
		}
		
		JsArrayString keys=state.keys();
		for(int i=0;i<keys.length();i++){
			String key=keys.get(i);
			String value=state.get(key);
			valuePanel.add(new Label(key+","+value));
		}
	}
	
	
}

public class AVPanel extends VerticalPanel{
	private Label mic;
	private Label camera;
	private Label speaker;
	private Label micMute;
	private Label camMute;

	private VerticalPanel avpartPanel;
	public AVPanel(){
		Button update=new Button("update");
		add(update);
		update.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				update();
			}
		});
		HorizontalPanel havePanel=new HorizontalPanel();
		add(havePanel);
		havePanel.setSpacing(8);
		
		havePanel.add(new Label("[Have]"));
		mic = new Label("mic:");
		havePanel.add(mic);
		camera = new Label("camera:");
		havePanel.add(camera);
		speaker = new Label("speaker:");
		havePanel.add(speaker);
		
		
		
		add(new Label("[Mute]"));
		HorizontalPanel hpanel=new HorizontalPanel();
		micMute = new Label("mic-mute:");
		hpanel.add(micMute);
		
		add(hpanel);
		Button micMuteBt=new Button("mute");
		hpanel.add(micMuteBt);
			micMuteBt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Av.setMicrophoneMute(true);	
			}
			});
		Button micunMuteBt=new Button("unmute");	
		hpanel.add(micunMuteBt);
		micunMuteBt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Av.setMicrophoneMute(false);	
			}
			});
		camMute = new Label("camera-mute:");
		HorizontalPanel hpanel2=new HorizontalPanel();
		hpanel2.add(camMute);
		add(hpanel2);
		Button camMuteBt=new Button("mute");
		hpanel2.add(camMuteBt);
		camMuteBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Av.setCameraMute(true);	
				}
				});
			Button camunMuteBt=new Button("unmute");	
			hpanel2.add(camunMuteBt);
			camunMuteBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Av.setCameraMute(false);	
				}
				});	
			avpartPanel=new VerticalPanel();
			add(avpartPanel);
	}
	
	public class AVParticipant extends HorizontalPanel{
		private Participant participant;
		public AVParticipant(final Participant participant){
			this.participant=participant;
			String vl=""+Av.getParticipantVolume(participant.getHangoutId());
			vl=vl.substring(0,Math.min(4,vl.length()));
			Label volume=new Label("Volume:"+vl);
			volume.setWidth("100px");
			add(volume);
			
			Label visible=new Label("Visible:"+Av.isParticipantVisible(participant.getHangoutId()));
			add(visible);
			Button visibleBt=new Button("visible");
			add(visibleBt);
			visibleBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Av.setParticipantVisible(participant.getHangoutId(), true);
				}
			});
			Button invisibleBt=new Button("invisible");
			add(invisibleBt);
			invisibleBt.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Av.setParticipantVisible(participant.getHangoutId(), false);
				}
			});
			
			if(Av.getAvatar(participant.getHangoutId()).equals("undefined")){
				add(new Label("UND"));
			}else{
				String url=""+Av.getAvatar(participant.getHangoutId());
				add(new Label(url));
			}
			
			
			
		}
		
	}
	

	protected void update() {
		mic.setText("mic:"+Av.hasMicrophone());
		camera.setText("camera:"+Av.hasCamera());
		speaker.setText("speaker:"+Av.hasSpeakers());
		
		camMute.setText("cam-mute:"+Av.getCameraMute());
		micMute.setText("mic-mute:"+Av.getMicrophoneMute());
		
		avpartPanel.clear();
		JsArray<Participant> parts=Hangout.getParticipants();
		int size=parts.length();
		for(int i=0;i<size;i++){
			avpartPanel.add(new AVParticipant(parts.get(i)));
		}
		
		final ValueListBox<Participant> vbox=new ValueListBox<Participant>(new Renderer<Participant>() {
			@Override
			public String render(Participant object) {
				if(object==null){
					return null;
				}
				return object.getDisplayName();
			}

			@Override
			public void render(Participant object, Appendable appendable)
					throws IOException {
				
			}
		});
		
		avpartPanel.add(vbox);
		
		
		
		
		
		HorizontalPanel setAvatorPanel=new HorizontalPanel();
		avpartPanel.add(setAvatorPanel);
		setAvatorPanel.add(new Label("img-url"));
		final TextBox url=new TextBox();
		url.setWidth("200px");
		setAvatorPanel.add(url);
		
		Button setBt=new Button("set avator");
		setAvatorPanel.add(setBt);
		setBt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Participant p=vbox.getValue();
				String urlText=url.getText();
				Av.setAvatar(p.getHangoutId(), urlText);
				
			}
		});
		Button clearBt=new Button("clear avator");
		avpartPanel.add(clearBt);
		clearBt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Participant p=vbox.getValue();
				Av.clearAvatar(p.getHangoutId());
			}
		});
		
		List<Participant> values = new ArrayList<Participant>();
		for(int i=0;i<size;i++){
			values.add(parts.get(i));
		}
		vbox.setValue(values.get(0));
		vbox.setAcceptableValues(values);
		
	}
}

	public class HangoutPanel extends VerticalPanel{
		private Label visible;
		private Label hangoutId;
		private Label locale;
		private Label participantId;

		public HangoutPanel(){
			HorizontalPanel control=new HorizontalPanel();
			add(control);
			Button bt=new Button("update");
			control.add(bt);
			bt.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					update();
				}
			});
			
			
			
			add(boldLabel("[visible]"));
			visible = new Label();
			add(visible);
			
			add(boldLabel("[hangout-id]"));
			hangoutId = new Label();
			add(hangoutId);
			
			add(boldLabel("[locale]"));
			locale = new Label();
			add(locale);
			
			add(boldLabel("[participantId]"));
			participantId = new Label();
			add(participantId);
			
			Button hide=new Button("hide application");
			hide.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Hangout.hideApp();
				}
			});
			add(hide);
		}
		
		public void update(){
			visible.setText(""+Hangout.isAppVisible());
			hangoutId.setText(Hangout.getHangoutId());
			locale.setText(Hangout.getLocale());
			participantId.setText(Hangout.getParticipantId());
		}
		
	}
	
	public String toLabel(Participant p){
		String ret="";
		ret+="hangoutid="+p.getHangoutId()+",";
		ret+="installed="+p.hasAppInstalled()+",";
		ret+="id="+p.getId()+",";
		ret+="displayName="+p.getDisplayName()+",";
		ret+="image-url="+p.getImageUrl();
		return ret;
	}

	
	public native String ready()/*-{
	  return ""+$wnd.gapi.hangout.isApiReady();
	}-*/;
	
	 private static ScriptElement createScriptElement(String src,String content) {
		    ScriptElement script = Document.get().createScriptElement();
		    script.setAttribute("type", "text/javascript");
		    if(src!=null){
		    	script.setAttribute("src",src);
		    }
		    if(content!=null){
		    	script.setText(content);
		    }
		    return script;
		  }
}
