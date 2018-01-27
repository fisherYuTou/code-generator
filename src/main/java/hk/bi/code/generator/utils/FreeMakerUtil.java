package hk.bi.code.generator.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeMakerUtil {


	private static Logger logger = LoggerFactory.getLogger(FreeMakerUtil.class);

	/**
	 *
	 * 获取模板文件
	 *
	 * @param name
	 * @return
	 */
	public  Template getTemplate(String name) {
		try {
			Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			String basePath =  System.getProperty("user.dir")+File.separator+"ftl";
			cfg.setDirectoryForTemplateLoading(new File(basePath));
			Template template = cfg.getTemplate(name);
			return template;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 控制台输出
	 *
	 * @param templateName
	 * @param root
	 */
	public   void print(String templateName, Map<String, Object> root) {

		try {
			Template template = getTemplate(templateName);
			template.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 生成文件
	 *
	 * @param templateName:模板名
	 * @param root:数据原型
	 * @param outputFile:输出文件(全路径名)
	 */
	public  void generateFile(String templateName, Map<String, Object> root, String outputFile) {

		FileWriter out = null;
		try {
			File file  = new File(outputFile);
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			// 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
			out = new FileWriter(file);
			Template temp = getTemplate(templateName);
			temp.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		System.out.println(FreeMakerUtil.class.getResource("/").toString()+"ftl");
	}

}
