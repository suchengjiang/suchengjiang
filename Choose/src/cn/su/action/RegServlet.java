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
		// 获取请求头
				String referer = request.getHeader("referer");
				if(referer == null || !referer.endsWith("reg.jsp")){
					// 做处理
					// 获取虚拟路径
					// request获取客户机的信息（重定向）
					response.sendRedirect(request.getContextPath()+"/reg.jsp"); // 加项目
					return;
				}
		// 获取参数列表
		@SuppressWarnings("unchecked")
		Map<String, String []> map1 = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map1);
		
		RegService reg =new RegService();
		int flag = reg.regUser(user);
		
		if(flag ==MyXmlUtil.EMAILEXIST){
			request.setAttribute("msg", "邮箱已存在");
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
		}
		else if(flag ==MyXmlUtil.NAMEEXIST){
			request.setAttribute("msg", "用户名已存在");
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
		}
		else if(flag ==MyXmlUtil.SUCCESS){
			request.setAttribute("msg", "注册成功");
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
