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

import com.ecity.java.web.SF.api.OrderSearchService;
import com.ecity.java.web.SF.api.OrderService;
import com.ecity.java.web.SF.api.SFApi;
import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.dto.WaybillDto;
import com.sf.util.MyJsonUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.util.Base64ImageTools;

public class testOrderSearchService {
	
		// TODO Auto-generated method stub
		public static void main(String[] args) {
//			doOrderSearchService();
			
			OrderSearchService order=new OrderSearchService("LS1537582420975");
			order.GetXml(order.Send());
			if (order.Head.equals("ERR"))
			{
				System.out.println("ERROR:"+order.ERROR);
				System.out.println("ERROR code:"+order.ERRORCode);
			}
			else 
			{

				System.out.println("OrderResponse_orderid:"+order.OrderResponse_orderid);
				System.out.println("OrderResponse_mailno:"+order.OrderResponse_mailno);
				System.out.println("OrderResponse_origincode:"+order.OrderResponse_origincode);
				System.out.println("OrderResponse_destcode:"+order.OrderResponse_destcode);
				System.out.println("OrderResponse_filter_result:"+order.OrderResponse_filter_result);
			}
			
		}
		public static void doOrderSearchService()
		{
			// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444006216706" origincode="755" orderid="LS1537582420975"><rls_info rls_errormsg="444006216706:" invoke_result="OK" rls_code="1000"><rls_detail waybillNo="444006216706" sourceTransferCode="755W" sourceCityCode="755" sourceDeptCode="755BF" sourceTeamCode="018" destCityCode="020" destDeptCode="020" destRouteLabel="020" proName="顺丰标快" cargoTypeCode="C201" limitTypeCode="T4" expressTypeCode="B1" xbFlag="0" printFlag="000000000" twoDimensionCode="MMM={'k1':'','k2':'020','k3':'','k4':'T4','k5':'444006216706','k6':''}" proCode="T4" printIcon="00000000" errMsg=" dest:sssNOT_MATCH_ADDR{&quot;src&quot;:&quot;queryAip&quot;,&quot;flag&quot;:0}"/></rls_info></OrderResponse></Body></Response>
			String orderid="LS15375824209752";
			String reqXml = "<Request service=\"OrderSearchService\"  lang=\"zh-CN\">\r\n" + 
					"<Head>"+SFApi.ClientCode+"</Head>\r\n" + 
					"<Body>\r\n" + 
					"<OrderSearch orderid='"+orderid+"'/>\r\n" + 
					"</Body>\r\n" + 
					"</Request>";
			String respXml;
			
			CallExpressServiceTools client=CallExpressServiceTools.getInstance();		 
			System.out.println("OrderService请求报文："+reqXml);
			respXml= client.callSfExpressServiceByCSIM(SFApi.ReqURL, reqXml, SFApi.ClientCode, SFApi.CheckWord);
			//GetXml(respXml);
			if (respXml != null) {
					 System.out.println("--------------------------------------");
					 System.out.println("返回报文: "+ respXml);
					 System.out.println("--------------------------------------");
			}		

		}
		
	
}
