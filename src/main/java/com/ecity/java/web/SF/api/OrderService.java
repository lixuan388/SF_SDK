package com.ecity.java.web.SF.api;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.dto.WaybillDto;

public class OrderService extends OrderClass {


	
	
	public String OrderResponse_filter_result="";
	public String OrderResponse_destcode=""; 
	public String OrderResponse_mailno=""; 
	public String OrderResponse_origincode=""; 
	public String OrderResponse_orderid="";
	
	public String rls_info_rls_errormsg=""; 
	public String rls_info_invoke_result=""; 
	public String rls_info_rls_code="";
	
	
	public String rls_detail_waybillNo=""; 
	public String rls_detail_sourceTransferCode=""; 
	public String rls_detail_sourceCityCode=""; 
	public String rls_detail_sourceDeptCode=""; 
	public String rls_detail_sourceTeamCode=""; 
	public String rls_detail_destCityCode=""; 
	public String rls_detail_destDeptCode=""; 
	public String rls_detail_destRouteLabel=""; 
	public String rls_detail_proName=""; 
	public String rls_detail_cargoTypeCode=""; 
	public String rls_detail_limitTypeCode=""; 
	public String rls_detail_expressTypeCode=""; 
	public String rls_detail_xbFlag=""; 
	public String rls_detail_printFlag=""; 
	public String rls_detail_twoDimensionCode=""; 
	public String rls_detail_proCode=""; 
	public String rls_detail_printIcon=""; 
	public String rls_detail_errMsg="";
	
	
	String dProvince;
	String dCity;
	String dCounty;
	String dCompany;
	String dContact;
	String dTel;
	String dAddress;
	String dCargo;
	
	
	public OrderService(String Orderid,String Province,String City,String County,String Company,String Contact,String Tel,String Address,String Cargo)
	{
		this.dProvince=Province;
		this.dCity=City;
		this.dCounty=County;
		this.dCompany=Company;
		this.dContact=Contact;
		this.dTel=Tel;
		this.dAddress=Address;
		this.dCargo=Cargo;
		
		this.reqXml = "<Request service=\"OrderService\" lang=\"zh-CN\">\r\n" + 
				"<Head>"+SFApi.ClientCode+"</Head> \r\n" + 
				"<Body> \r\n" + 
				"	<Order \r\n" + 
				"		orderid='"+Orderid+"' \r\n" +
				"		j_company='"+SFApi.J_Company+"'\r\n" +
				"		j_contact='"+SFApi.J_Contact+"'\r\n" +  
				"		j_tel='"+SFApi.J_Tel+"'\r\n" + 
				"		j_mobile='"+SFApi.J_Tel+"'\r\n" + 
				"		j_province='"+SFApi.J_Province+"'\r\n" + 
				"		j_city='"+SFApi.J_City+"'	\r\n" + 
				"		j_county='"+SFApi.J_County+"'	\r\n" + 
				"		j_address='"+SFApi.J_Address+"'\r\n" + 
				
				"		express_type='1' \r\n" + 

				"		d_province='"+Province+"' \r\n" + 
				"		d_city='"+City+"' \r\n" + 
				"		d_county='"+County+"'\r\n" + 
				"		d_company='"+Company+"' \r\n" + 
				"		d_contact='"+Contact+"' \r\n" + 
				"		d_tel='"+Tel+"' \r\n" + 
				"		d_mobile='"+Tel+"' \r\n" + 
				"		d_address='"+Address+"'	\r\n" + 
				"		parcel_quantity='1' \r\n" + 
				"		pay_method='3'\r\n" + 
				"		custid ='"+SFApi.CustID+"'	\r\n" + 
				"remark=\"\">\r\n" +
				"<Cargo name='"+Cargo+"'>\r\n" + 
				"</Cargo>" + 

				"	</Order>\r\n" + 
				"</Body> \r\n" + 
				"</Request>";
	}
	

	@Override
	public void ReadOrderResponse(Element Response) {
		// TODO Auto-generated method stub

  	Element OrderResponse=Response.element("Body").element("OrderResponse");
		this.OrderResponse_filter_result=OrderResponse.attributeValue("filter_result");
  	this.OrderResponse_destcode=OrderResponse.attributeValue("destcode"); 
  	this.OrderResponse_mailno=OrderResponse.attributeValue("mailno"); 
  	this.OrderResponse_origincode=OrderResponse.attributeValue("origincode");
  	this.OrderResponse_orderid=OrderResponse.attributeValue("orderid");
  	
  	Element rls_info=OrderResponse.element("rls_info");
  	
  	this.rls_info_rls_errormsg=rls_info.attributeValue("rls_errormsg");
  	this.rls_info_invoke_result=rls_info.attributeValue("invoke_result"); 
  	this.rls_info_rls_code=rls_info.attributeValue("rls_code");
  	if (this.rls_info_rls_code.equals("1000"))
  	{
  		Element rls_detail=rls_info.element("rls_detail");
  		this.rls_detail_waybillNo=rls_detail.attributeValue("waybillNo");
  		this.rls_detail_sourceTransferCode=rls_detail.attributeValue("sourceTransferCode");
  		this.rls_detail_sourceCityCode=rls_detail.attributeValue("sourceCityCode");
  		this.rls_detail_sourceDeptCode=rls_detail.attributeValue("sourceDeptCode");
  		this.rls_detail_sourceTeamCode=rls_detail.attributeValue("sourceTeamCode");
  		this.rls_detail_destCityCode=rls_detail.attributeValue("destCityCode");
  		this.rls_detail_destDeptCode=rls_detail.attributeValue("destDeptCode");
  		this.rls_detail_destRouteLabel=rls_detail.attributeValue("destRouteLabel");
  		this.rls_detail_proName=rls_detail.attributeValue("proName");
  		this.rls_detail_cargoTypeCode=rls_detail.attributeValue("cargoTypeCode");
  		this.rls_detail_limitTypeCode=rls_detail.attributeValue("limitTypeCode");
  		this.rls_detail_expressTypeCode=rls_detail.attributeValue("expressTypeCode");
  		this.rls_detail_xbFlag=rls_detail.attributeValue("xbFlag");
  		this.rls_detail_printFlag=rls_detail.attributeValue("printFlag");
  		this.rls_detail_twoDimensionCode=rls_detail.attributeValue("twoDimensionCode");
  		this.rls_detail_proCode=rls_detail.attributeValue("proCode");
  		this.rls_detail_printIcon=rls_detail.attributeValue("printIcon");
  		this.rls_detail_errMsg=rls_detail.attributeValue("errMsg");
  	}
	}
//
//
//	public void GetXml(String XmlString)
//	{
//		//<?xml version='1.0' encoding='UTF-8'?>
////			<Response service="OrderService">
////				<Head>OK</Head>
////				<Body>
////					<OrderResponse 
////						filter_result="2" 
////						destcode="020" 
////						mailno="444006203631" 
////						origincode="755" 
////						orderid="LS1537453043916">
////						<rls_info 
////							rls_errormsg="444006203631:" 
////							invoke_result="OK" 
////							rls_code="1000">
////							<rls_detail 
////								waybillNo="444006203631" 
////								sourceTransferCode="755W" 
////								sourceCityCode="755" 
////								sourceDeptCode="755BF" 
////								sourceTeamCode="018" 
////								destCityCode="020" 
////								destDeptCode="020" 
////								destRouteLabel="020" 
////								proName="顺丰标快" 
////								cargoTypeCode="C201" 
////								limitTypeCode="T4" 
////								expressTypeCode="B1" 
////								xbFlag="0" 
////								printFlag="000000000" 
////								twoDimensionCode="MMM={'k1':'','k2':'020','k3':'','k4':'T4','k5':'444006203631','k6':''}" 
////								proCode="T4" 
////								printIcon="00000000" 
////								errMsg=" dest:sssNOT_MATCH_ADDR{&quot;src&quot;:&quot;queryAip&quot;,&quot;flag&quot;:0}"/>
////						</rls_info>
////					</OrderResponse>
////				</Body>
////			</Response>
//		SAXReader reader = new SAXReader();  
//    try {  
//        Document document = null;
//				try {
//					document = reader.read(new ByteArrayInputStream(XmlString.getBytes("UTF-8")));
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
//        Element Response = document.getRootElement();  
//        Element Head=Response.element("Head");
//        this.Head=Head.getText();
//        if (this.Head.equals("OK"))
//        {
//        	Element OrderResponse=Response.element("Body").element("OrderResponse");
//        	
//        }
//        else
//        {
//          Element ERROR=Response.element("ERROR");
//          this.ERROR=ERROR.getText();          
//          this.ERRORCode= ERROR.attribute("code").getText();
//        }
//    } catch (DocumentException e) {
//        e.printStackTrace();  
//    }  
//	}
	
	public WaybillDto getWayBill()
	{
		WaybillDto dto = new WaybillDto();
		
		
		
    //这个必填 
		dto.setAppId(SFApi.ClientCode);//对应clientCode
		dto.setAppKey(SFApi.CheckWord);//对应checkWord
	
		dto.setMailNo(this.OrderResponse_mailno);
		
		//收件人信息  
		dto.setConsignerProvince(this.dProvince);
		dto.setConsignerCity(this.dCity);
		dto.setConsignerCounty(this.dCounty);
		dto.setConsignerAddress(this.dAddress); //详细地址建议最多30个字  字段过长影响打印效果
		dto.setConsignerCompany(this.dCompany);
		dto.setConsignerMobile(this.dTel);
		dto.setConsignerName(this.dContact);
		dto.setConsignerShipperCode("");
		dto.setConsignerTel(this.dTel);
		
		
		//寄件人信息
		dto.setDeliverProvince(SFApi.J_Province);
		dto.setDeliverCity(SFApi.J_City);
		dto.setDeliverCounty(SFApi.J_County);
		dto.setDeliverCompany(SFApi.J_Company);
		dto.setDeliverAddress(SFApi.J_Address);//详细地址建议最多30个字  字段过长影响打印效果
		dto.setDeliverName(SFApi.J_Contact);
		dto.setDeliverMobile(SFApi.J_Tel);
		dto.setDeliverShipperCode("");
		dto.setDeliverTel(SFApi.J_Tel);
		
		
		dto.setDestCode(this.rls_detail_destDeptCode);//目的地代码 参考顺丰地区编号
		dto.setZipCode(this.rls_detail_sourceDeptCode);//原寄地代码 参考顺丰地区编号
		
		//签回单号  签单返回服务 会打印两份快单 其中第二份作为返寄的单
		//如客户使用签单返还业务则需打印“POD”字段，用以提醒收派员此件为签单返还快件。	
		// dto.setReturnTrackingNo("755123457778");
		
		//陆运E标示
		//业务类型为“电商特惠、顺丰特惠、电商专配、陆运件”则必须打印E标识，用以提示中转场分拣为陆运	
		dto.setElectric("E");
		
		
		//快递类型	
		//1 ：标准快递   2.顺丰特惠   3： 电商特惠   5：顺丰次晨  6：顺丰即日  7.电商速配   15：生鲜速配		
		dto.setExpressType(1);
				
		//COD代收货款金额,只需填金额, 单位元- 此项和月结卡号绑定的增值服务相关
		dto.setCodValue("0");
		
		dto.setInsureValue("0");//声明货物价值的保价金额,只需填金额,单位元
		dto.setMonthAccount(SFApi.CustID);//月结卡号  
		dto.setPayMethod(1);//
		
		
		/**丰密运单相关-如非使用丰密运单模板 不需要设置以下值**/
		dto.setDestRouteLabel(this.rls_detail_destRouteLabel);
		dto.setPrintIcon(this.rls_detail_printIcon);
		dto.setProCode("");
		dto.setAbFlag("A");
		dto.setXbFlag(this.rls_detail_xbFlag);
		dto.setCodingMapping("");
		dto.setCodingMappingOut("");
		dto.setDestTeamCode("");
		dto.setSourceTransferCode(this.rls_detail_sourceTransferCode);
		//对应下订单设置路由标签返回字段twoDimensionCode 该参数是丰密面单的二维码图
		dto.setQRCode(this.rls_detail_twoDimensionCode);
		//客户个性化Logo 必须是个可以访问的图片本地路径地址或者互联网图片地址
	   // dto.setCustLogo("D:\\qiao.jpg");
	    
		
//			sourceTransferCode="755W" 
//			sourceCityCode="755" 
//			sourceDeptCode="755BF" 
//			sourceTeamCode="018" 
//			destCityCode="020" 
//			destDeptCode="020" 
//			destRouteLabel="020" 
//			proName="顺丰标快" 
//			cargoTypeCode="C201" 
//			limitTypeCode="T4" 
//			expressTypeCode="B1" 
//			xbFlag="0" 
//			printFlag="000000000" 
//			twoDimensionCode="MMM={'k1':'','k2':'020','k3':'','k4':'T4','k5':'444006203631','k6':''}" 
//			c="T4" 
//			printIcon="00000000" 
//			errMsg=" dest:sssNOT_MATCH_ADDR{&quot;src&quot;:&quot;queryAip&quot;,&quot;flag&quot;:0}"/>
				
				
		
		//加密项
		dto.setEncryptCustName(true);//加密寄件人及收件人名称
		dto.setEncryptMobile(true);//加密寄件人及收件人联系手机		
		
		
		
//		CargoInfoDto cargo = new CargoInfoDto();
//		cargo.setCargo("苹果7S");
//		cargo.setCargoCount(1);
//		cargo.setCargoUnit("件");
//		cargo.setSku("00015645");
//		cargo.setRemark("手机贵重物品 小心轻放");
//		
//		CargoInfoDto cargo2 = new CargoInfoDto();
//		cargo2.setCargo("苹果macbook pro");
//		cargo2.setCargoCount(1);
//		cargo2.setCargoUnit("件");
//		cargo2.setSku("00015646");
//		cargo2.setRemark("笔记本贵重物品 小心轻放");
//		
//		List<CargoInfoDto> cargoInfoList = new ArrayList<CargoInfoDto>();
//		cargoInfoList.add(cargo2);
//		cargoInfoList.add(cargo);
		
//		dto.setCargoInfoDtoList(cargoInfoList);
		return dto;
	}
	
	
	public String WayBillPprint(String url,byte[] b )
	{
		URL myURL = null;
		try {
			myURL = new URL(url);
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
		
		try {
			httpConn.getOutputStream().write(b);
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
		System.out.println(strImg); 
		return strImg;
	}
}
