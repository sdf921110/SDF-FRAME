package com.sdf.common.pojo;

import java.util.HashMap;
import java.util.Map;

public class Result
{
	private Map<String, Object> map;
	private MSG msg;

	public Result()
	{
		map = new HashMap<String, Object>();
		msg = new MSG();
		map.put("msg", msg);
	}

	public Map<String, Object> putMsg(String info, boolean isSuccess)
	{
		putMsg(info, isSuccess, 0);
		return map;
	}

	public Map<String, Object> putMsg(String info, boolean isSuccess, int code)
	{
		msg.setCode(code);
		msg.setInfo(info);
		msg.setSuccess(isSuccess);
		return map;
	}

	public Map<String, Object> putData(String key, Object value)
	{
		map.put(key, value);
		return map;
	}
	
	public Map<String, Object> getResult(){
		return map;
	}
}
