package com.lengjing.info.realtime.RealTimeHbaseData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;







import org.mortbay.log.Log;

import scala.util.regexp.Base.RegExp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class App extends Thread
{
    public static void main( String[] args ) throws Exception
    {
    	String str="{\"inputCompanyName\": \"普宁市吉美制衣有限公司\", \"taskId\": \"null\", \"KeyPerson_Info\": [{\"KeyPerson_Info:keyperson_position\": \"董事长,总经理\", \"KeyPerson_Info:keyperson_name\": \"秦雄\", \"KeyPerson_Info:registrationno\": \"91445281734124395Q\", \"KeyPerson_Info:enterprisename\": \"普宁市吉美制衣有限公司\", \"rowkey\": \"普宁市吉美制衣有限公司_06_91445281734124395Q_2017-03-091\"}, {\"KeyPerson_Info:keyperson_position\": \"董事\", \"KeyPerson_Info:keyperson_name\": \"秦丽玲\", \"KeyPerson_Info:registrationno\": \"91445281734124395Q\", \"KeyPerson_Info:enterprisename\": \"普宁市吉美制衣有限公司\", \"rowkey\": \"普宁市吉美制衣有限公司_06_91445281734124395Q_2017-03-092\"}, {\"KeyPerson_Info:keyperson_position\": \"董事\", \"KeyPerson_Info:keyperson_name\": \"陈嘉容\", \"KeyPerson_Info:registrationno\": \"91445281734124395Q\", \"KeyPerson_Info:enterprisename\": \"普宁市吉美制衣有限公司\", \"rowkey\": \"普宁市吉美制衣有限公司_06_91445281734124395Q_2017-03-093\"}], \"Registered_Info\": [{\"Registered_Info:registrationno\": \"91445281734124395Q\", \"Registered_Info:registeredcapital\": \"700万香港元\", \"Registered_Info:legalrepresentative\": \"秦雄\", \"Registered_Info:businessscope\": \"生产：各式服装，色织布。(依法须经批准的项目，经相关部门批准后方可开展经营活动)〓\", \"Registered_Info:tyshxy_code\": \"91445281734124395Q\", \"Registered_Info:approvaldate\": \"2016年03月09日\", \"Registered_Info:residenceaddress\": \"流沙东中河经济技术开发区\", \"Registered_Info:validityto\": \"2020年01月15日\", \"Registered_Info:establishmentdate\": \"2002年01月16日\", \"Registered_Info:province\": \"广东省\", \"Registered_Info:lastupdatetime\": \"2017-03-09 11:51:49\", \"rowkey\": \"普宁市吉美制衣有限公司_01_91445281734124395Q_\", \"Registered_Info:enterprisetype\": \"有限责任公司(台港澳法人独资)\", \"Registered_Info:registrationinstitution\": \"普宁市工商行政管理局\", \"Registered_Info:registrationstatus\": \"存续\", \"Registered_Info:enterprisename\": \"普宁市吉美制衣有限公司\", \"Registered_Info:validityfrom\": \"2002年01月16日\"}], \"Shareholder_Info\": [{\"Shareholder_Info:registrationno\": \"91445281734124395Q\", \"Shareholder_Info:rjmx\": [{\"认缴出资日期\": \"\", \"认缴出资方式\": \"货币出资\", \"认缴出资额(万元)\": \"700万香港元\"}], \"Shareholder_Info:subscripted_amount\": \"700万香港元\", \"Shareholder_Info:subscripted_capital\": \"700万香港元\", \"Shareholder_Info:shareholder_name\": \"香港骏丰贸易公司\", \"rowkey\": \"普宁市吉美制衣有限公司_04_91445281734124395Q_2017-03-091\", \"Shareholder_Info:shareholder_certificationtype\": \"登记证\", \"Shareholder_Info:subscripted_time\": \"\", \"Shareholder_Info:actualpaid_capital\": \"700万香港元\", \"Shareholder_Info:actualpaid_time\": \"2009年10月29日\", \"Shareholder_Info:shareholder_certificationno\": \"31096888-000-07-01-7\", \"Shareholder_Info:enterprisename\": \"普宁市吉美制衣有限公司\", \"Shareholder_Info:actualpaid_amount\": \"700万香港元\", \"Shareholder_Info:actualpaid_method\": \"货币出资\", \"Shareholder_Info:sjmx\": [{\"实缴出资日期\": \"2009年10月29日\", \"实缴出资额(万元)\": \"700万香港元\", \"实缴出资方式\": \"货币出资\"}], \"Shareholder_Info:subscripted_method\": \"货币出资\", \"Shareholder_Info:shareholder_type\": \"企业法人\"}], \"Changed_Announcement\": [], \"accountId\": \"null\"}";
    	LoadHbaseData.loadData(str.replaceAll("\r", "").replaceAll("\n", ""));
    	
    	
    	/*InputStreamReader inputRead;
		try {
			inputRead = new InputStreamReader(
						new FileInputStream("/home/zyx/hbaseBatch/2016-09-19.log"), "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputRead);
    		String lineTxt = null;
    		while ((lineTxt = bufferedReader.readLine()) != null) {
    			JSONObject jsonobj=JSONObject.parseObject(lineTxt.replaceAll("\r", "").replaceAll("\n", ""));
    			String companyName=jsonobj.getString("inputCompanyName");
				JSONArray jsonarray=jsonobj.getJSONArray("Registered_Info");
				//Log.info(lineTxt);
				if(jsonarray!=null){
					JSONObject  obj= jsonarray.getJSONObject(0);
	    			if(obj.containsKey("Registered_Info:province") && obj.get("Registered_Info:province").equals("山东省")){
	    				if(obj.containsKey("Registered_Info:registrationno") && obj.get("Registered_Info:registrationno").equals("")){
	    					Log.info(lineTxt);
	    				};
	    			}
				}    			
    		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/// 考虑到编码格式
    		
    		/*String str="{\"inputCompanyName\": \"金坛市三胜电子有限公司\", \"taskId\": \"null\", \"accountId\": \"null\"}";
    		JSONObject jsonobj=JSONObject.parseObject(str);
			String companyName=jsonobj.getString("inputCompanyName");
			JSONArray jsonarray=jsonobj.getJSONArray("Registered_Info");
			System.out.println(jsonarray!=null);*/
    		/*InputStreamReader inputRead = new InputStreamReader(
    				new FileInputStream("d:\\yubing.lei\\Desktop\\hivetest\\2016-09-06\\0"), "utf-8");// 考虑到编码格式
    		BufferedReader bufferedReader = new BufferedReader(inputRead);
    		String lineTxt = null;
    		while ((lineTxt = bufferedReader.readLine()) != null) {
    	    	LoadHbaseData.loadData(lineTxt.replaceAll("\r", "").replaceAll("\n", ""));
    			
    	}*/
    	/*for(int i=0;i<24;i++){
    		InputStreamReader inputRead = new InputStreamReader(
    				new FileInputStream("d:\\yubing.lei\\Desktop\\hivetest\\2016-09-07\\"+i), "utf-8");// 考虑到编码格式
    		BufferedReader bufferedReader = new BufferedReader(inputRead);
    		String lineTxt = null;
    		while ((lineTxt = bufferedReader.readLine()) != null) {
    	    	LoadHbaseData.loadData(lineTxt.replaceAll("\r", "").replaceAll("\n", ""));
    			
    		}
    	}*/
    	
    	/*try {
			//LoadHbaseData.loadData(str.replaceAll("\r", "").replaceAll("\n", ""));
    		JSONObject	jsonobj = new JSONObject(str);
    		org.codehaus.jettison.json.JSONArray jsonarray=jsonobj.getJSONArray("Registered_Info");
    		for(int i=0;i<jsonarray.length();i++){
				JSONObject t = jsonarray.getJSONObject(i);
				Iterator<Object> it=t.keys();
				while(it.hasNext()){
					String key=it.next().toString();
					String value=t.getString(key);
					System.out.println(key+"---"+value);
				}
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	/*String regex="([\\u4e00-\\u9fa5]+)";
    	String str="30.000000万人民币";
    	Matcher matcher = Pattern.compile(regex).matcher(str);
    	if(matcher.find()){
    		System.out.println(matcher.group(0));
    	}*/
    	
    }
}
