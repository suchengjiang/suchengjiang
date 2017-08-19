package cn.su.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.su.service.RegService;
import cn.su.vo.User;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * ��ȡ�����б�
		 * ��װ����
		 * ����JavaBean���ҵ��
		 * ת����Servlet
		 */
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�����б�
		Map<String, String []> map = request.getParameterMap();
		// ����User����
		User user = new User();
		// ��װ����
		try {
			BeanUtils.populate(user, map);
			// ��ɵ�½�Ĺ���
			RegService service = new RegService();
			// ��½����
			User succUser = service.loginUser(user);
			// ���succUser��null��˵����½���ɹ�
			if(succUser == null){
				// ��½���ɹ���ת������½ҳ��
				request.setAttribute("msg", "�û����������벻��ȷ");
				request.getRequestDispatcher("/reg/login.jsp").forward(request, response);
			}else{
				// ��ɼ�ס�û����Ĺ���
				// ��ȡ��ѡ�������
				String remember = request.getParameter("remember");
				// �ж�
				if(remember != null && "remember".equals(remember)){
					
					String username = succUser.getUsername();
					// �Ȱ����ĵ����Ʊ���
					username = URLEncoder.encode(username, "UTF-8");
					// ����
					// URLDecoder.decode(s, "UTF-8");
					
					// System.out.println(username);
					
					// ��ס�û��������浽cookie�� 	aaa-123456
					Cookie cookie = new Cookie("username", username);
					// ������Чʱ��
					cookie.setMaxAge(60*60);
					// ������Ч·��
					cookie.setPath("/");
					// ��дcookie
					response.addCookie(cookie);
				}
				
				// ��½�ɹ�����ת��ĳ��ҳ�棬�û���Ϣ��ʾ��ҳ����
				// �ض��򵽳ɹ���ҳ��
				// ��ʾ�û�����Ϣ
				request.getSession().setAttribute("succUser", succUser);
				response.sendRedirect(request.getContextPath()+"/reg/success.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
