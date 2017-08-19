package cn.su.utils;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class MyXmlUtil {

	public static final String PATH ="d:\\user.xml";
	public static final int NAMEEXIST=1;
	public static final int EMAILEXIST=2;
	public static final int SUCCESS=0;
	public static Document getDocument(String path) throws Exception{
		// 解析器对象
		SAXReader reader = new SAXReader();
		// 解析
		return reader.read(path);
	}
	// 回写（XMLWriter）
	public static void writeXml(Document document,String path) throws Exception{
		// 格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
		// 进行回写
		writer.write(document);
		// 关闭流
		writer.close();
	}
		
}
