package commonsteps;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseUrl {
    public static Properties props;
    public static void propInitiate() {
        props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("inputs/Data.properties")));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
