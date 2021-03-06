package cn.su.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.su.utils.MyXmlUtil;
import cn.su.vo.User;

public class RegService {
	@SuppressWarnings("null")
	public int regUser(User user) throws Exception{
		// 使用工具类，操作XML
		Document document = MyXmlUtil.getDocument(MyXmlUtil.PATH);
		// 解析XML
		// 获取根节点（查文档）
		Element root = document.getRootElement();
		// 校验用户名或者邮箱是否重名
		List<Element> uList = root.elements("user");
		// 判断
		if(uList != null || uList.size() > 0){
			// XML中查询，和传入过来做对比，如果相同，重名了，返回
			// 从XML中获取的
			for (Element u : uList) {
				// 获取username节点的文本内容
				// u.element("username").getText();
				String xmlName = u.elementText("username");
				// 获取传入进来的User 的名称
				String uName = user.getUsername();
				// 重名了，返回1
				if(xmlName.equals(uName)){
					return MyXmlUtil.NAMEEXIST;
				}
						
				// 获取xmlEamil
				String xmlEmail = u.elementText("email");
				String uEamil = user.getEmail();
				if(xmlEmail.equals(uEamil)){
					return MyXmlUtil.EMAILEXIST;
				}
			}
		}
		// 需要在XML添加节点
				Element uElement = root.addElement("user");
				// 添加子节点
				uElement.addElement("username").setText(user.getUsername());
				uElement.addElement("password").setText(user.getPassword());
				uElement.addElement("nickname").setText(user.getRealname());
				uElement.addElement("email").setText(user.getEmail());
				uElement.addElement("sex").setText(user.getSex());
				uElement.addElement("born").setText(user.getBorn());
				uElement.addElement("tel").setText(user.getTel());
				// 回写
				MyXmlUtil.writeXml(document, MyXmlUtil.PATH);
				// 返回1 用户名重名了，返回是2邮箱重名，返回0注册成功
				return MyXmlUtil.SUCCESS;
			}
			
			/**
			 * 用户登陆
			 * @param user
			 * @return
			 * @throws Exception 
			 */
			public User loginUser(User user) throws Exception {
				// 获取XML中用户名和密码的文本内容
				Document document = MyXmlUtil.getDocument(MyXmlUtil.PATH);
				// 获取根节点
				Element root = document.getRootElement();
				// 先获取User的节点
				List<Element> uList = root.elements("user");
				if(uList != null && uList.size() > 0){
					for (Element u : uList) {
						// 获取username和password的文本内容
						String xmlName = u.elementText("username");
						String uName = user.getUsername();
						
						String xmlPass = u.elementText("password");
						String uPass = user.getPassword();
						// 可以登陆
						if(xmlName.equals(uName) && xmlPass.equals(uPass)){
							// 把昵称和邮箱存入到user对象中
							user.setRealname(u.elementText("realname"));
							user.setEmail(u.elementText("email"));
							return user;
						}
					}
				}
				return null;
			}

		}



