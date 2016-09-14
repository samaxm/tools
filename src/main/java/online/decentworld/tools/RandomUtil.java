package online.decentworld.tools;

import java.util.Random;

public class RandomUtil {
	private static Random random=new Random(System.currentTimeMillis());
	
	public static int getRandomNum(int roof){
		return random.nextInt(roof);
	}
}
