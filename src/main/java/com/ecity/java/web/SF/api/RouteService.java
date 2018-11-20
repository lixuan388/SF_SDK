package com.ecity.java.web.SF.api;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;

public class RouteService extends OrderClass {
	
	public String RouteResponse_mailno="";
	public ArrayList<Route> RouteList=new ArrayList<Route>();
	public RouteService(String OrderID)
	{
		this.reqXml ="<Request service='RouteService' lang='zh-CN'>\r\n" + 
		"<Head>"+SFApi.ClientCode+"</Head>\r\n" + 
		"<Body>\r\n" + 
		"<RouteRequest\r\n" +
		"tracking_type='2'\r\n" +
		"method_type='1'\r\n" + 
		"tracking_number='"+OrderID+"'/>\r\n" +
		"</Body>\r\n" + 
		"</Request>";
	}

	@Override
	public void ReadOrderResponse(Element Response) {
		// TODO Auto-generated method stub
  	Element RouteResponse=Response.element("Body").element("RouteResponse");
  	this.RouteResponse_mailno=RouteResponse.attributeValue("mailno");
  	Iterator<Element> routeList=RouteResponse.elementIterator("Route");
  	while (routeList.hasNext())
  	{
  		Element RouteElement=routeList.next();
  		String remark=RouteElement.attributeValue("remark");
  		
  		String accept_time=RouteElement.attributeValue("accept_time");
  		String accept_address=RouteElement.attributeValue("accept_address");
  		String opcode=RouteElement.attributeValue("opcode");
  		Route route =new Route(remark, accept_time, accept_address, opcode);
  		this.RouteList.add(route);
  	}
	}
	
	public class Route
	{
		public String remark;
		public String accept_time;
		public String accept_address;
		public String opcode;
		public Route(String remark,String accept_time,String accept_address,String opcode)
		{
			this.remark=remark;
			this.accept_time=accept_time;
			this.accept_address=accept_address;
			this.opcode=opcode;
		}
	}
}
