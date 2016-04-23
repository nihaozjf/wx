/** 

* @Title: StringToInt.java

* @Package com.wskj.util

* @Description: TODO(用一句话描述该文件做�?��)

* @author A18ccms A18ccms_gmail_com 

* @date 2014-2-21 下午08:23:13

* @version V1.0 

*/ 
package com.akmi.jyxt.utils;

import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**

 * @ClassName: StringToInt

 * @Description: TODO

 * @author hongwei
 * @date 2014-2-21 下午08:23:13

 * @editor hongwei
 * @editdate 2014-2-21 下午08:23:13


 */
public class StringUtil {
	
    /**  
     * 设置下载文件中文件的名称  
     *   
     * @param filename  
     * @param request  
     * @return  
     */    
    public static String encodeFilename(String filename, HttpServletRequest request) {    
        /**  
         * 获取客户端浏览器和操作系统信�? 
         * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
         */    
        String agent = request.getHeader("USER-AGENT");    
        try {    
          if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
            String newFileName = URLEncoder.encode(filename, "UTF-8");    
            newFileName = StringUtils.replace(newFileName, "+", "%20");    
            if (newFileName.length() > 150) {    
              newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
              newFileName = StringUtils.replace(newFileName, " ", "%20");    
            }    
            return newFileName;    
          }    
          if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
            return MimeUtility.encodeText(filename, "UTF-8", "B");    
        
          return filename;    
        } catch (Exception ex) {    
          return filename;    
        }    
      }   

    /**判断字符串不为空
     * @param sourcestr
     * @return
     */
    public static Boolean stringnotNull(String sourcestr)
    {
    	if (sourcestr!= null &&!sourcestr.equals(""))
    		return true;
    	else
    		return false;
    }
    
    /**
     * 判断对象不为空
     * @param souobj
     * @return
     */
    public static Boolean objnotNull(String souobj)
    {
    	if (souobj!= null &&!souobj.toString().equals(""))
    		return true;
    	else
    		return false;
    }
    
    
	 public static int stringtoInt(String sourcestr)
	 {
		 
		 int retint=0;
		  if(sourcestr!=null)
		  {
			  try
			  {
				  retint=Integer.parseInt(sourcestr);
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retint;
		 
	 }
	 
	 public static int objtoInt(Object sourceobj)
	 {
		 
		 int retint=0;
		  if(sourceobj!=null)
		  {
			  try
			  {
				  retint=Integer.parseInt(sourceobj.toString().trim());
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retint;
		 
	 }
	 
	 public static int objtoInt(Object sourceobj,int caseint)
	 {
		 
		 int retint=caseint;
		  if(sourceobj!=null)
		  {
			  try
			  {
				  retint=Integer.parseInt(sourceobj.toString().trim());
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retint;
		 
	 }
	 
	 public static short objtoShort(Object sourceobj)
	 {
		 
		 short retshort=0;
		  if(sourceobj!=null)
		  {
			  try
			  {
				  retshort=Short.parseShort(sourceobj.toString());
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retshort;
		 
	 }
	 
	 public static float objtoFloat(Object sourceobj)
	 {
		 
		 float retfloat=0;
		  if(sourceobj!=null)
		  {
			  try
			  {
				  retfloat=Float.parseFloat(sourceobj.toString());
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retfloat; 
	 }
	 
	 public static boolean objtoboolean(Object sourceobj)
	 {
		 
		 boolean retboolean=false;
		  if(sourceobj!=null)
		  {
			  try
			  {
				  retboolean=Boolean.parseBoolean(sourceobj.toString());
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retboolean; 
	 }
	 
	 public static Double objtoDouble(Object sourceobj)
	 {
		 
		 Double retdouble=0.0;
		  if(sourceobj!=null)
		  {
			  try
			  {
				  retdouble=Double.parseDouble(sourceobj.toString());
			  }
			  catch(Exception ex){  
			  }
		  }
		 return retdouble;
		 
	 }
	 /**
	 * @param strings
	 * @return
	 */
	public static int[] StringToArray(String ...strings )
	 {
	   int[] retint={0,0,0,0,0};
	   String param="";
	   String suffixparam="";
	   String intparam="";
	    
       for(int i=0;i<strings.length;i++)
       {
       	   param=strings[i];
       	   if(param.length()>1)
       	   {
	       	   suffixparam=param.substring(0,1).trim();
	       	   intparam=param.substring(1).trim();
	       	   if(suffixparam.endsWith("d"))
	       	   {
	       		  retint[0]=stringtoInt(intparam);
	       	   }
	       	   else if(suffixparam.endsWith("l"))
	       	   {
	       		   retint[1]=stringtoInt(intparam);
	       	   }
	       	   else if(suffixparam.endsWith("m"))
	       	   {
	       		   retint[2]=stringtoInt(intparam);
	       	   }
	       	   else if(suffixparam.endsWith("n"))
	       	   {
	       		   retint[3]=stringtoInt(intparam);
	       	   }
	       	   else if(suffixparam.endsWith("p"))
	       	   {
	       		   retint[4]=stringtoInt(intparam);
	       	   }
       	   }    	   
       }
       System.out.println(retint[0]);
       return retint;
	 }
	
	
	public static String getLimitLengthString( String str,int len){
		
		
		return getLimitLengthString(str, len,"utf-8");
	}
	/**
	 * 截取指定长度字符串
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getLimitLengthString( String str,int len,String charsetName){
		try{
		int counterOfDoubleByte = 0;
		byte[] b = str.getBytes(charsetName);
		if(b.length <= len)
		return str;
		for(int i = 0; i < len; i++){
		if(b[i] < 0)
		counterOfDoubleByte++;
		}
		
		if(counterOfDoubleByte %3 == 0)
		    return new String(b,0,len, charsetName)+"...";
		else if(counterOfDoubleByte %3 ==1)
		    return new String(b,0,len-1, charsetName)+"...";
		else 
			return new String(b,0,len-2, charsetName)+"...";
		}
		catch(Exception ex){

		return "";
		}
		}
	
}
