package online.decentworld.tools;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IDUtil {

	private static String stringBase = "1234567890qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNPQRSTUVWXYZ";
	private static String stringBaseUP = "1234567890ABCDEFGHIJKLMNPQRSTUVWXYZ";



	public static String getDWID() {
		UUID uniqueKey = UUID.randomUUID();
		String str = uniqueKey.toString().replace("-", "");
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(str);
		String fs = m.replaceAll("").trim().substring(0, 10);
		return fs;
	}

	public static String getRandomString(int len){
		StringBuilder sb=new StringBuilder();
		Random random=new Random();
		for(int i=0;i<len;i++) {
			int index = random.nextInt(stringBaseUP.length() - 1);
			sb.append(stringBaseUP.charAt(index));
		}
		return sb.toString();
	}

	public  static String createRandomCode(){
		String retStr = "";  
		String strTable = "1234567890";  
		int len = strTable.length();  
		boolean bDone = true;  
		do {  
			retStr = "";  
			int count = 0;  
			for (int i = 0; i < 4; i++) {  
				double dblR = Math.random() * len;  
				int intR = (int) Math.floor(dblR);  
				char c = strTable.charAt(intR);  
				if (('0' <= c) && (c <= '9')) {  
					count++;  
				}  
				retStr += strTable.charAt(intR);  
			}  
			if (count >= 2) {  
				bDone = false;  
			}  
		} while (bDone);  

		return retStr;  
	}  
	

	public static String randomToken(){
		String retStr = "";  
		String strTable = "1234567890ABCDEFGHIJKLMNPQRSTUVWXYZ";  
		int len = strTable.length();  
		boolean bDone = true;  
		do {  
			retStr = "";  
			int count = 0;  
			for (int i = 0; i < 16; i++) {  
				double dblR = Math.random() * len;  
				int intR = (int) Math.floor(dblR);  
				char c = strTable.charAt(intR);  
				if (('0' <= c) && (c <= '9')) {  
					count++;  
				}  
				retStr += strTable.charAt(intR);  
			}  
			if (count >= 2) {  
				bDone = false;  
			}  
		} while (bDone);  

		return retStr;  
	}
	
	public static void main(String[] args) {
		for (int i=0;i<100;i++) {
			System.out.println(getRandomString(4));
		}
	}
}
