package	com.ecity.java.web.SF.test;


import java.util.Date;

import com.ecity.java.web.SF.api.OrderService;
import com.sf.csim.express.service.CallExpressServiceTools;


public class TestCallExpressService {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		String reqXml = "<Request service=\"OrderService\" lang=\"zh-CN\">\r\n" + 
				"<Head>SLKJ2019</Head>\r\n" + 
				"<Body>\r\n" + 
				"<Order\r\n" + 
				"orderid=\"QIAO-20171231001\"\r\n" + 
				"j_company=\"罗湖火车站\"\r\n" + 
				"j_contact=\"张三丰\"\r\n" + 
				"j_tel=\"13810744\"\r\n" + 
				"j_mobile=\"13111744\"\r\n" + 
				"j_province=\"广东省\"\r\n" + 
				"j_city=\"深圳\"\r\n" + 
				"j_county=\"福田区\"\r\n" + 
				"j_address=\"罗湖火车站东区调度室\"\r\n" + 
				"d_company=\"顺丰速运\"\r\n" + 
				"d_contact=\"张无忌\"  \r\n" + 
				"d_tel=\"15819050\"\r\n" + 
				"d_mobile=\"15539050\"\r\n" + 
				"d_address=\"北京市海淀区中关村\"\r\n" + 
				"express_type=\"1\"\r\n" + 
				"pay_method=\"1\"\r\n" + 
				"parcel_quantity=\"1\"\r\n" + 
				"cargo_length=\"33\"\r\n" + 
				"cargo_width=\"33\"\r\n" + 
				"cargo_height=\"33\"\r\n" + 
				"remark=\"\">\r\n" + 
				"<Cargo\r\n" + 
				"name=\"LV1\"\r\n" + 
				"count=\"3\"\r\n" + 
				"unit=\"a\"\r\n" + 
				"weight=\"\"\r\n" + 
				"amount=\"\"\r\n" + 
				"currency=\"\"\r\n" + 
				"source_area=\"\">\r\n" + 
				"</Cargo>\r\n" + 
				"<Cargo\r\n" + 
				"name=\"LV2\"\r\n" + 
				"count=\"3\"\r\n" + 
				"unit=\"a\"\r\n" + 
				"weight=\"\"\r\n" + 
				"amount=\"\"\r\n" + 
				"currency=\"\"\r\n" + 
				"source_area=\"\">\r\n" + 
				"</Cargo>\r\n" + 
				"<AddedService\r\n" + 
				"name=\"COD\"\r\n" + 
				"value=\"3000\"\r\n" + 
				"value1=\"7551234567\">\r\n" + 
				"</AddedService>\r\n" + 
				"<AddedService\r\n" + 
				"name=\"INSURE\"\r\n" + 
				"value=\"2304.23\">\r\n" + 
				"</AddedService>\r\n" + 
				"<AddedService\r\n" + 
				"name=\"URGENT\">\r\n" + 
				"</AddedService>\r\n" + 
				"<Extra\r\n" + 
				"e1=\"abc\"\r\n" + 
				"e2=\"abc\"/>\r\n" + 
				"</Order>\r\n" + 
				"</Body>\r\n" + 
				"</Request>";

       //丰桥平台公共测试账号  
       //SLKJ2019
       //FBIqMkZjzxbsZgo7jTpeq7PD8CVzLT4Q
	    String reqURL="https://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
        String clientCode="JMSDFLYGJLXSGZF";//此处替换为您在丰桥平台获取的顾客编码
        String checkword="BopvFUgra3qSMzEbtOfpQ1pPrBvqYxwJ";//此处替换为您在丰桥平台获取的校验码
        CallExpressServiceTools client=CallExpressServiceTools.getInstance();     
        String myReqXML=reqXml.replace("SLKJ2019",clientCode);
        System.out.println("请求报文："+myReqXML);
        String respXml= client.callSfExpressServiceByCSIM(reqURL, myReqXML, clientCode, checkword);
      
		 if (respXml != null) {
             System.out.println("--------------------------------------");
             System.out.println("返回报文: "+ respXml);
             System.out.println("--------------------------------------");
         }			
	}
	
	/*********************标准返回报文参考*****************************/
	//1.下订单-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444000010085" origincode="755" orderid="QIAO-20171017001"/></Body></Response>

   		
	//2.订单结果查询 -请求返回结果
	//<?xml version='1.0' encoding='UTF-8'?><Response service="OrderSearchService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444000010085,819000008006,819000008015" origincode="755" orderid="QIAO-20171017001"/></Body></Response>

	
	//3.订单取消-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderConfirmService"><Head>OK</Head><Body><OrderConfirmResponse res_status="2" orderid="QIAO-20171017001"/></Body></Response>
	
	//4.订单筛选-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderFilterService"><Head>OK</Head><Body><OrderFilterResponse filter_result="1" orderid="QIAO-20171017001"/></Body></Response>

	//5.路由查询-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="RouteService"><Head>OK</Head><Body><RouteResponse mailno="444000010085" orderid="QIAO-20171017001"><Route remark="已经收件" accept_time="2017-08-31 02:01:44" accept_address="广东省深圳市软件产业基地" opcode="50"/><Route remark="已经收件" accept_time="2017-08-31 02:01:44" accept_address="广东省深圳市软件产业基地" opcode="80"/></RouteResponse></Body></Response>
	
	//6.路由推送
	
	//7.子单号申请-请求返回结果
	//<?xml version='1.0' encoding='UTF-8'?><Response service="OrderZDService"><Head>OK</Head><Body><OrderZDResponse><OrderZDResponse main_mailno="444000010085" mailno_zd="819000008006,819000008015" orderid="QIAO-20171017001"/></OrderZDResponse></Body></Response>

	

}