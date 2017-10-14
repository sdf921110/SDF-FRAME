package com.sdf.core.service;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
	protected Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("static-access")
	protected BigDecimal getBigDecimal(Object bd) {
		if (bd == null) {
			return new BigDecimal(0);
		}
		if (bd instanceof BigDecimal) {
			BigDecimal b = (BigDecimal) bd;
			return b.setScale(2, b.ROUND_HALF_UP);
		}
		try {
			return new BigDecimal(bd + "");
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}

}
