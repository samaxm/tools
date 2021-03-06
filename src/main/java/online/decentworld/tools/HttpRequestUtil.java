package online.decentworld.tools;

import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;

/**
 * http请求工具类
 * @author Sammax
 *
 */
public class HttpRequestUtil {
	
	private static OkHttpClient client=new OkHttpClient();
	public static String Content_Type="Content-Type";
	public static String application_json="application/json";
	public static int SUCCESS=200;
	
	public enum Method{
		GET,POST,PUT;
	}
	
	public static Response GET(String url,HttpHeader... headers) throws IOException{
		Builder builder=new Request.Builder();
		builder.url(url);
		if(headers!=null){
			for(HttpHeader header:headers){
				builder.addHeader(header.getName(), header.getValue());
			}
		}
		return client.newCall(builder.build()).execute();
	}
	
	
	public static Response JSON(String url,String json,Method method,HttpHeader... headers) throws IOException{
		Builder builder=new Request.Builder();
		RequestBody body=RequestBody.create(MediaType.parse("JSON"),json);
		builder.url(url);
		if(headers!=null){
			for(HttpHeader header:headers){
				builder.addHeader(header.getName(), header.getValue());
			}
		}
		if(method==Method.POST){
			builder.post(body);
		}else{
			builder.put(body);
		}
		return client.newCall(builder.build()).execute();
	}
}
