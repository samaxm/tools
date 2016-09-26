package online.decentworld.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Sammax on 2016/9/26.
 */
public class EnvironmentCofing {
    final public static Environment environment;
    private static Logger logger= LoggerFactory.getLogger(EnvironmentCofing.class);
    static {
        InputStream in=EnvironmentCofing.class.getClassLoader().getResourceAsStream("environment.properties");
        Properties p=new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            logger.warn("",e);
        }
        environment=Environment.valueOf(p.getProperty("environment").toUpperCase());
    }


    public static void main(String[] args) {
        System.out.println(EnvironmentCofing.environment);
    }
}
