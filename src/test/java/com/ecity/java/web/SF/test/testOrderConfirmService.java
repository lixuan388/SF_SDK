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

import com.ecity.java.web.SF.api.OrderConfirmService;
import com.ecity.java.web.SF.api.OrderSearchService;
import com.ecity.java.web.SF.api.OrderService;
import com.ecity.java.web.SF.api.RouteService;
import com.ecity.java.web.SF.api.SFApi;
import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.dto.WaybillDto;
import com.sf.util.MyJsonUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.util.Base64ImageTools;

public class testOrderConfirmService {
	

	// TODO Auto-generated method stub
	public static void main(String[] args) {
		
		OrderConfirmService order=new OrderConfirmService("LS1537604643921","");
		order.GetXml(order.Send());
		if (order.Head.equals("ERR"))
		{
			System.out.println("ERROR:"+order.ERROR);
			System.out.println("ERROR code:"+order.ERRORCode);
		}
		else 
		{
			System.out.println("OrderResponse_res_status:"+order.OrderResponse_res_status);
		}
		
	}
	
}
