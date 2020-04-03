package com.web.common.initialize;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Initialize {
	Logger logger = LoggerFactory.getLogger(Initialize.class);

	abstract boolean initialize(Map<Object, Object> parameter);
	
}
