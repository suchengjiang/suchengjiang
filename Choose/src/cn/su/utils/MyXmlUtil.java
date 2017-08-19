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
		// ����������
		SAXReader reader = new SAXReader();
		// ����
		return reader.read(path);
	}
	// ��д��XMLWriter��
	public static void writeXml(Document document,String path) throws Exception{
		// ��ʽ
		OutputFormat format = OutputFormat.createPrettyPrint();
		// ���ñ���
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
		// ���л�д
		writer.write(document);
		// �ر���
		writer.close();
	}
		
}
