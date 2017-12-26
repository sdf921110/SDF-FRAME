package com.sdf.codeGeneration.code.test;

public class Url2json {
	public static void main(String[] args) {
		int sun = 0;
		int count = 0;
//		String json = "{\"data\":[";
		String json = "{";
		String data = "";
//		String end = "]";
		String end = "}";

		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		String arry = "client_type=1&payWay=1&orderId=5&payFee=5349.09&appVersion=1.0.0&userId=1";
		// 计算出字符串里有多少个&符号
		for (int i = 0; i < arry.length(); i++) {
			if ("&".equals(arry.substring(i, i + 1))) {
				sun++;
			}
		}
		String a[] = arry.split("[&]");// 以&符号分隔
		// 将分隔出来的字符串加上，
		for (int i = 0; i < sun + 1; i++) {
			sb.append(a[i] + ",");
		}
		// 以=分隔
		String[] c = sb.toString().split("[=]");
		// 将分隔出来的字符串加上，
		for (int j = 0; j < c.length; j++) {
			sb2.append(c[j] + ",");
		}
		// 去掉最后面两个,
		String result = sb2.toString().substring(0, sb2.length() - 2);
		// 计算出字符串里有多少个,符号
		for (int i = 0; i < result.length(); i++) {
			if (",".equals(result.substring(i, i + 1))) {
				count++;
			}
		}
		// 以，分隔
		String[] d = result.toString().split("[,]");
		for (int i = 0; i < count + 1; i++) {
			if (i % 2 != 0) {// 字符下标为奇数时加上，
				data += "\"" + d[i] + "\",";
			} else {
				data += "\"" + d[i] + "\":";
			}
		}
		// 拼接json格式
		String jsonArray = (json + data).substring(0, (json + data).length() - 1) + end;
		System.out.println(jsonArray);
	}
}