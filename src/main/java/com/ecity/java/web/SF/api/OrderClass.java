package com.ecity.java.web.SF.api;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sf.csim.express.service.CallExpressServiceTools;

public class OrderClass {
	public String reqXml="";
	public String respXml="";
	
	public String Head="";
	public String ERROR="";
	public String ERRORCode="";

	
	
	public String Send()
	{
		CallExpressServiceTools client=CallExpressServiceTools.getInstance();		 
		System.out.println("OrderService请求报文："+this.reqXml);
		this.respXml= client.callSfExpressServiceByCSIM(SFApi.ReqURL, this.reqXml, SFApi.ClientCode, SFApi.CheckWord);
		//GetXml(respXml);
		if (respXml != null) {
				 System.out.println("--------------------------------------");
				 System.out.println("返回报文: "+ respXml);
				 System.out.println("--------------------------------------");
		}		
		return this.respXml;
//		GetXml(respXml);
	}
	
	public void GetXml(String XmlString)
	{
		SAXReader reader = new SAXReader();  
    try {  
        Document document = null;
				try {
					document = reader.read(new ByteArrayInputStream(XmlString.getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
        Element Response = document.getRootElement();  
        Element Head=Response.element("Head");
        this.Head=Head.getText();
        if (this.Head.equals("OK"))
        {
        	ReadOrderResponse(Response);
        }
        else
        {
          Element ERROR=Response.element("ERROR");
          this.ERROR=ERROR.getText();          
          this.ERRORCode= ERROR.attribute("code").getText();
        }
    } catch (DocumentException e) {
        e.printStackTrace();  
    }  
	}
	
	public void ReadOrderResponse(Element Response)
	{
		
	}
}
