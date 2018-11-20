package com.ecity.java.web.SF.api;


import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.dto.WaybillDto;

public class OrderSearchService  extends OrderClass {

	
	

	public String OrderResponse_orderid="";
	public String OrderResponse_mailno="";
	public String OrderResponse_origincode="";
	public String OrderResponse_destcode="";
	public String OrderResponse_filter_result="";
	
	
	public OrderSearchService(String Orderid)
	{
		this.reqXml = "<Request service=\"OrderSearchService\"  lang=\"zh-CN\">\r\n" + 
				"<Head>"+SFApi.ClientCode+"</Head>\r\n" + 
				"<Body>\r\n" + 
				"<OrderSearch orderid='"+Orderid+"'/>\r\n" + 
				"</Body>\r\n" + 
				"</Request>";
	}
	
	
	@Override
	public void ReadOrderResponse(Element Response) {
		// TODO Auto-generated method stub

  	Element OrderResponse=Response.element("Body").element("OrderResponse");
  	this.OrderResponse_orderid=OrderResponse.attributeValue("orderid");
  	this.OrderResponse_mailno=OrderResponse.attributeValue("mailno"); 
  	this.OrderResponse_origincode=OrderResponse.attributeValue("origincode"); 
  	this.OrderResponse_destcode=OrderResponse.attributeValue("destcode");
  	this.OrderResponse_filter_result=OrderResponse.attributeValue("filter_result");
	}

}
