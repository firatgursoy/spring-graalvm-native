/*
 * Copyright 2019 the original author or authors.
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
package org.springframework.graal.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Andy Clement
 */
public class TypeAccessRequestor {

	private Map<String, Integer> requestedAccesses = new HashMap<>();
	
	public void request(String type, Integer accessRequired) {
		if (type.indexOf("/")!=-1) {
			throw new IllegalStateException("Only pass dotted names to request(), name was: "+type);
		}
		Integer existsAlready = requestedAccesses.get(type);
		if (existsAlready != null) {
			requestedAccesses.put(type, existsAlready | accessRequired);//existsAlready.with(accessRequired));
		} else {
			requestedAccesses.put(type, accessRequired);
		}
	}
	
	public Set<Entry<String,Integer>> entrySet() {
		return requestedAccesses.entrySet();
	}

}
