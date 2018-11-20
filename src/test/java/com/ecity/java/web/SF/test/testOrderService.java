package com.ecity.java.web.SF.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecity.java.web.SF.api.OrderService;

import com.sf.dto.WaybillDto;
import com.sf.util.MyJsonUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.util.Base64ImageTools;

public class testOrderService {
	
		// TODO Auto-generated method stub
		public static void main(String[] args) {
			String Orderid="LS"+new Date().getTime();
			String Province="广东省";
			String City="广州市";
			String County="";
			String Company="";
			String Contact="风一样的旭哥";
			String Tel="33992159";
			String Address="海珠区宝芝林大厦701室";
			String Cargo="资料";
			
			
			OrderService order=new OrderService(Orderid, Province, City, County, Company, Contact, Tel, Address, Cargo);
			order.GetXml(order.Send());
			if (order.Head.equals("ERR"))
			{
				System.out.println("ERROR:"+order.ERROR);
				System.out.println("ERROR code:"+order.ERRORCode);
			}
			

			URL myURL = null;
			try {
				myURL = new URL("http://localhost:4040/sf/waybill/print?type=V3.0.FM_poster_100mm210mm&output=image");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection httpConn = null;
			try {
				httpConn = (HttpURLConnection) myURL.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			try {
				httpConn.setRequestMethod("POST");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
			
			
			httpConn.setConnectTimeout(5000);
			httpConn.setReadTimeout(2 * 5000);
			
			List<WaybillDto> waybillDtoList = new ArrayList<WaybillDto>();

			WaybillDto dto=order.getWayBill();
			waybillDtoList.add(dto);
			
			
			System.out.println("请求参数： "+MyJsonUtil.object2json(dto));
			
		
			
			ObjectMapper objectMapper = new ObjectMapper();
			StringWriter stringWriter = new StringWriter();
			try {
				objectMapper.writeValue(stringWriter,waybillDtoList);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				httpConn.getOutputStream().write(stringWriter.toString().getBytes());
				httpConn.getOutputStream().flush();
				httpConn.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStream in = null;
			try {
				in = httpConn.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			BufferedReader in2=new BufferedReader(new InputStreamReader(in));

			String y="";
			
		    
			String strImg="";
			try {
				while((y=in2.readLine())!=null){
					 
						strImg=y.substring(y.indexOf("[")+1,y.length()-"]".length()-1);
						if(strImg.startsWith("\"")){
							strImg=strImg.substring(1,strImg.length());
						}
						if(strImg.endsWith("\"")){
							strImg=strImg.substring(0,strImg.length()-1);
						}
						  
					   }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		        //将换行全部替换成空    
			strImg=strImg.replace("\\n", ""); 	    
			//System.out.println(strImg); 
			
		    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
			String dateStr = format.format(new Date());
			
			if(strImg.contains("\",\"")){
				//如子母单及签回单需要打印两份或者以上
				String[] arr = strImg.split("\",\""); 

				/**输出图片到本地 支持.jpg、.png格式**/		
			    for(int i = 0; i < arr.length; i++) {  
			    	Base64ImageTools.generateImage(arr[i].toString(), "D:\\temp\\qiaoWay"+dateStr+"-"+i+".jpg");
			    	
			    }
				}else{
		            Base64ImageTools.generateImage(strImg, "D:\\temp\\qiaoWaybill"+dateStr+".jpg");	
					
				}
		
		
	    //     System.exit(0);
		}
		
		
	
}
