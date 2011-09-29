package com.akjava.gwt.api.hangout.client.js.handler;

import com.akjava.gwt.api.hangout.client.js.StateMetadataMap;
import com.akjava.gwt.api.hangout.client.js.StringMap;
import com.google.gwt.core.client.JsArrayString;

public interface StateChangeListener {

	public void stateChange(StringMap adds,JsArrayString removes,StringMap state,StateMetadataMap metadata);
}
