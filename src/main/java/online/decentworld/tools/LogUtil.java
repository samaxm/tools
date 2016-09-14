package online.decentworld.tools;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 将各种类型转成String
 * @author Sammax
 *
 */
public class LogUtil {

	@SuppressWarnings("rawtypes")
	public static String toLogString(Map map){
		try{
		if( map==null){
			return "null";
		}else if(map.size()==0){
			return "empty map";
		}else{
			StringBuilder sb=new StringBuilder();
			sb.append("{");
			for(Object key:map.keySet()){
				sb.append(key).append(":").append(map.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("}");
			return sb.toString();
		}
		}catch(Exception ex){
			return "error";
		}
		
	}
	@SuppressWarnings("rawtypes")
	public static String toLogString(List list){
		try{
			if( list==null){
				return "null";
			}else if(list.size()==0){
				return "empty list";
			}else{
				StringBuilder sb=new StringBuilder();
				sb.append("[");
				for(int i=0;i<list.size();i++){
					sb.append(list.get(i)).append(",");
				}
				return sb.deleteCharAt(sb.length()-1).append("]").toString();
			}
		}catch(Exception ex){
			return "error";
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static String toLogString(Set set){
		try{
			if( set==null){
				return "null";
			}else if(set.size()==0){
				return "empty list";
			}else{
				StringBuilder sb=new StringBuilder();
				sb.append("[");
				Iterator i=set.iterator();
				while(i.hasNext()){
					sb.append(i.next()).append(",");
				}
				return sb.deleteCharAt(sb.length()-1).append("]").toString();
			}
		}catch(Exception ex){
			return "error";
		}
	}
	
	public static String toLogString(Object[] arr){
		try{
			if( arr==null){
				return "null";
			}else if(arr.length==0){
				return "empty";
			}else{
				StringBuilder sb=new StringBuilder();
				sb.append("[");
				for(int i=0;i<arr.length;i++){
					sb.append(arr[i]).append(",");
				}
				return sb.deleteCharAt(sb.length()-1).append("]").toString();
			}
		}catch(Exception ex){
			return "error";
		}
	}
	
	public static void main(String[] args) {
		Class<?>[] classes={LogUtil.class};
		System.out.println(toLogString(classes));
	}
}
