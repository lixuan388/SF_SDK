package com.ecity.java.web.SF.api;

import org.dom4j.Element;

public class OrderConfirmService extends OrderClass {
	

	public String OrderResponse_res_status="";
	public OrderConfirmService(String Orderid,String MailNo)
	{
		this.reqXml = "<Request service=\"OrderConfirmService\" lang=\"zh-CN\">\r\n" + 
		"<Head>"+SFApi.ClientCode+"</Head>\r\n" + 
		"<Body>\r\n" + 
		"<OrderConfirm\r\n" + 
		"orderid=\""+Orderid+"\"\r\n" + 
		"dealtype=\"2\">\r\n" + 
		"</OrderConfirm>\r\n" + 
		"</Body>\r\n" + 
		"</Request>";
	}
	
	@Override
	public void ReadOrderResponse(Element Response) {
		// TODO Auto-generated method stub

  	Element OrderResponse=Response.element("Body").element("OrderResponse");
  	this.OrderResponse_res_status=OrderResponse.attributeValue("res_status");
	}
}
