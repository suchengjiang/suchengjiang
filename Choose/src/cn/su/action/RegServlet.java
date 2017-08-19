package cn.su.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.su.service.RegService;
import cn.su.utils.MyXmlUtil;
import cn.su.vo.User;

public class RegServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response, Map map)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charest=UTF-8");
		// ��ȡ����ͷ
				String referer = request.getHeader("referer");
				if(referer == null || !referer.endsWith("reg.jsp")){
					// ������
					// ��ȡ����·��
					// request��ȡ�ͻ�������Ϣ���ض���
					response.sendRedirect(request.getContextPath()+"/reg.jsp"); // ����Ŀ
					return;
				}
		// ��ȡ�����б�
		@SuppressWarnings("unchecked")
		Map<String, String []> map1 = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map1);
		
		RegService reg =new RegService();
		int flag = reg.regUser(user);
		
		if(flag ==MyXmlUtil.EMAILEXIST){
			request.setAttribute("msg", "�����Ѵ���");
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
		}
		else if(flag ==MyXmlUtil.NAMEEXIST){
			request.setAttribute("msg", "�û����Ѵ���");
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
		}
		else if(flag ==MyXmlUtil.SUCCESS){
			request.setAttribute("msg", "ע��ɹ�");
	}
		}
	catch (Exception e) {
		e.printStackTrace();
	}}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
