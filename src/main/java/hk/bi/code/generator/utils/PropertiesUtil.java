package hk.bi.code.generator.utils;

import java.io.*;
import java.util.Properties;

/** properties工具类
 *  @author yuyanwu
 *  @date 2018-1-24
 */
public class PropertiesUtil {

    /** 获取Properties
     *
     * @param fileName 文件名称
     * @return Properties
     */
    public static Properties getProperties(String fileName){
        File file = new File(System.getProperty("user.dir")+File.separator+fileName);
        String value = "";
        InputStream is = null;
        Properties p = null;
        try {
            is = new FileInputStream(file);
            p = new Properties();
            p.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }

}
