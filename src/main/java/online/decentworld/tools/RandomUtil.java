package online.decentworld.tools;

import java.util.Random;

public class RandomUtil {

	public static int getRandomNum(int roof){
		Random random=new Random(System.currentTimeMillis());
		return random.nextInt(roof);
	}
}
