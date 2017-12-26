package com.sdf.codeGeneration.code.test;

public class Test {

	public static void main(String[] args) {
		// System.err.println(new BigDecimal("null"));

		// String countryCode = "+49";
		// countryCode = countryCode.indexOf("+") != -1 ?
		// countryCode.substring(1) : countryCode;
		// System.err.println(countryCode);
		
		
//		URL url = null;  
//		String picpath = "http://xiyougou.top/download.img?path=base64/sys_platform/2017-07-25/20170725140153_487022.png";
//		try {  
//            url = new URL(picpath);  
//            DataInputStream dataInputStream = new DataInputStream(url.openStream());  
//            String imageName = "D://11.png";  
//            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));  
//
//            byte[] buffer = new byte[1024];  
//            int length;  
//
//            while ((length = dataInputStream.read(buffer)) > 0) {  
//                fileOutputStream.write(buffer, 0, length);  
//            }  
//
//            dataInputStream.close();  
//            fileOutputStream.close();  
//        } catch (MalformedURLException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
		
		
//		String idcardImg = "http://xiyougou.top/download.img?path=vsd,http://xiyougou.top/download.img?path=kkl";
//		String idcardImgNew = "";
//		String IMAGE_SERVER = "http://xiyougou.top/download.img?path=";
//		
//		String[] imgs = idcardImg.split(",");
//		
//		for(String img : imgs){
//			int index = img.indexOf(IMAGE_SERVER);
//			if (index!=-1) {
//				img = img.substring(index+IMAGE_SERVER.length());
//			}
//			idcardImgNew = idcardImgNew + img + ",";
//		}
//		
//		System.err.println(idcardImgNew.substring(0,idcardImgNew.length()-1));
//		
//		
//		String dString = "";
//		
//		System.err.println(dString.indexOf(","));
		
		
		
//		[{"goodsId":1,"service":"","servicePrice":0,"number":7,"attributeId":1,"remarks":""}]
//		[{"goodsId":5,"service":"","servicePrice":0,"number":1,"attributeId":15,"remarks":""},{"goodsId":5,"service":"","servicePrice":0,"number":1,"attributeId":15,"remarks":""}]
		
 String ids = "222,66";
		
		String[] idArr = ids.split(",");

		boolean flag = false;
		for (String id : idArr) {
			System.err.println(id);
		}
		

	}
}
