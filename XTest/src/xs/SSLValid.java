package xs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class SSLValid {
	public static void ssl_main() throws Exception {
		trustAllHttpsCertificates();
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
    System.out.println(SendGET());
	}

	public static String SendGET(){  
	   String result="";//访问返回结果  
	   BufferedReader read=null;//读取访问结果        
	   try {  
	    //创建url  
	    URL realurl=new URL("https://www.sojson.com/open/api/lunar/json.shtml?date=2019-04-05");  
	    //打开连接  
	    URLConnection connection=realurl.openConnection();  
	     // 设置通用的请求属性  
	             connection.setRequestProperty("accept", "*/*");  
	             connection.setRequestProperty("connection", "Keep-Alive");  
	             connection.setRequestProperty("user-agent",  
	                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	             //建立连接  
	             connection.connect();  
	             // 获取所有响应头字段  
	             // 定义 BufferedReader输入流来读取URL的响应  
	             read = new BufferedReader(new InputStreamReader(  
	                     connection.getInputStream(),"UTF-8"));  
	             String line;//循环读取  
	             while ((line = read.readLine()) != null) {  
	                 result += line;  
	             }  
	   } catch (IOException e) {  
	    e.printStackTrace();  
	   }finally{  
	    if(read!=null){//关闭流  
	     try {  
	      read.close();  
	     } catch (IOException e) {  
	      e.printStackTrace();  
	     }  
	    }  
	   }  
	       
	   return result;   
	 }
	
	
	public static HostnameVerifier hv = new HostnameVerifier() {
   public boolean verify(String urlHostName, SSLSession session) {
       System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                          + session.getPeerHost());
       return true;
   }
 };

	private static void trustAllHttpsCertificates() throws Exception {
			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
			javax.net.ssl.TrustManager tm = new miTM();
			trustAllCerts[0] = tm;
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
			.getInstance("SSL");
			sc.init(null, trustAllCerts, null);
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements javax.net.ssl.TrustManager,javax.net.ssl.X509TrustManager {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
			}
			public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
		    	return true;
			}	
			public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
					return true;
			}
	
			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)throws java.security.cert.CertificateException {
				return;
			}	
			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)throws java.security.cert.CertificateException {
				return;
			}
	}

}
