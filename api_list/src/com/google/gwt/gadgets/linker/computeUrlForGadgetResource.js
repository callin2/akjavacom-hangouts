
/**
 * original code written by Google
 * I modified for my app
 */

/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * Transform a resource into URL before making a request. This can be overridden
 * if some sort of proxy mechanism is needed.  This is modeled after the logic 
 * originally coded in ResourceInjectsionUtils.java
 */
function computeUrlForResource(resource) {
	
	var tmpBase = $wnd.gadgets.util.getUrlParameters()['url'];  
	  tmpBase = tmpBase.substring(0,  tmpBase.lastIndexOf('/') + 1);
	//useless like - app://334799408970/hangout
	  
  var cacheSpec;
  if (resource.match(/\.cache\.(js|html)$/)) {
	// Allow GWT resources marked named as cachabele to be cached for one year.
	cacheSpec = {refreshInterval:31536000};
  }
  /** YOU MUST REPLACE HERE **/
  resource="http://api.akjava.com/api_list/"+resource;
  
  /* Prepend anything that is not a fully qualified URL with the module base URL */
  if (!resource.match(/^[a-zA-Z]+:\/\//)) {
	  resource = __MODULE_FUNC__.__moduleBase + resource;
  }
  return $wnd.gadgets.io.getProxyUrl(resource, cacheSpec);
}
