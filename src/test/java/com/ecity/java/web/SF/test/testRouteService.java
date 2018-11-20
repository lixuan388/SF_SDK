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
import com.ecity.java.web.SF.api.RouteService.Route;
import com.ecity.java.web.SF.api.SFApi;
import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.dto.WaybillDto;
import com.sf.util.MyJsonUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.util.Base64ImageTools;

public class testRouteService {
	// TODO Auto-generated method stub
	public static void main(String[] args) {
		
		RouteService order=new RouteService("LS1537604643921");
		order.GetXml(order.Send());
		if (order.Head.equals("ERR"))
		{
			System.out.println("ERROR:"+order.ERROR);
			System.out.println("ERROR code:"+order.ERRORCode);
		}
		else 
		{
			System.out.println("RouteResponse_mailno:"+order.RouteResponse_mailno);
			for (int i =0;i<order.RouteList.size();i++)
			{
				Route route= order.RouteList.get(i);

				System.out.println("----route----");
				System.out.println("remark:"+route.remark);
				System.out.println("accept_time:"+route.accept_time);
				System.out.println("accept_address:"+route.accept_address);
				System.out.println("opcode:"+route.opcode);
				
			}
		}
		
	}
	
		
	
}
