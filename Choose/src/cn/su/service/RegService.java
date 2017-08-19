package cn.su.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.su.utils.MyXmlUtil;
import cn.su.vo.User;

public class RegService {
	@SuppressWarnings("null")
	public int regUser(User user) throws Exception{
		// ʹ�ù����࣬����XML
		Document document = MyXmlUtil.getDocument(MyXmlUtil.PATH);
		// ����XML
		// ��ȡ���ڵ㣨���ĵ���
		Element root = document.getRootElement();
		// У���û������������Ƿ�����
		List<Element> uList = root.elements("user");
		// �ж�
		if(uList != null || uList.size() > 0){
			// XML�в�ѯ���ʹ���������Աȣ������ͬ�������ˣ�����
			// ��XML�л�ȡ��
			for (Element u : uList) {
				// ��ȡusername�ڵ���ı�����
				// u.element("username").getText();
				String xmlName = u.elementText("username");
				// ��ȡ���������User ������
				String uName = user.getUsername();
				// �����ˣ�����1
				if(xmlName.equals(uName)){
					return MyXmlUtil.NAMEEXIST;
				}
						
				// ��ȡxmlEamil
				String xmlEmail = u.elementText("email");
				String uEamil = user.getEmail();
				if(xmlEmail.equals(uEamil)){
					return MyXmlUtil.EMAILEXIST;
				}
			}
		}
		// ��Ҫ��XML��ӽڵ�
				Element uElement = root.addElement("user");
				// ����ӽڵ�
				uElement.addElement("username").setText(user.getUsername());
				uElement.addElement("password").setText(user.getPassword());
				uElement.addElement("nickname").setText(user.getRealname());
				uElement.addElement("email").setText(user.getEmail());
				uElement.addElement("sex").setText(user.getSex());
				uElement.addElement("born").setText(user.getBorn());
				uElement.addElement("tel").setText(user.getTel());
				// ��д
				MyXmlUtil.writeXml(document, MyXmlUtil.PATH);
				// ����1 �û��������ˣ�������2��������������0ע��ɹ�
				return MyXmlUtil.SUCCESS;
			}
			
			/**
			 * �û���½
			 * @param user
			 * @return
			 * @throws Exception 
			 */
			public User loginUser(User user) throws Exception {
				// ��ȡXML���û�����������ı�����
				Document document = MyXmlUtil.getDocument(MyXmlUtil.PATH);
				// ��ȡ���ڵ�
				Element root = document.getRootElement();
				// �Ȼ�ȡUser�Ľڵ�
				List<Element> uList = root.elements("user");
				if(uList != null && uList.size() > 0){
					for (Element u : uList) {
						// ��ȡusername��password���ı�����
						String xmlName = u.elementText("username");
						String uName = user.getUsername();
						
						String xmlPass = u.elementText("password");
						String uPass = user.getPassword();
						// ���Ե�½
						if(xmlName.equals(uName) && xmlPass.equals(uPass)){
							// ���ǳƺ�������뵽user������
							user.setRealname(u.elementText("realname"));
							user.setEmail(u.elementText("email"));
							return user;
						}
					}
				}
				return null;
			}

		}



