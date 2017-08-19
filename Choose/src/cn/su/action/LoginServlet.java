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
		 * 获取参数列表
		 * 封装数据
		 * 调用JavaBean完成业务
		 * 转发到Servlet
		 */
		request.setCharacterEncoding("UTF-8");
		// 获取参数列表
		Map<String, String []> map = request.getParameterMap();
		// 创建User对象
		User user = new User();
		// 封装数据
		try {
			BeanUtils.populate(user, map);
			// 完成登陆的功能
			RegService service = new RegService();
			// 登陆功能
			User succUser = service.loginUser(user);
			// 如果succUser是null，说明登陆不成功
			if(succUser == null){
				// 登陆不成功，转发到登陆页面
				request.setAttribute("msg", "用户名或者密码不正确");
				request.getRequestDispatcher("/reg/login.jsp").forward(request, response);
			}else{
				// 完成记住用户名的功能
				// 获取复选框的内容
				String remember = request.getParameter("remember");
				// 判断
				if(remember != null && "remember".equals(remember)){
					
					String username = succUser.getUsername();
					// 先把中文的名称编码
					username = URLEncoder.encode(username, "UTF-8");
					// 解码
					// URLDecoder.decode(s, "UTF-8");
					
					// System.out.println(username);
					
					// 记住用户名，保存到cookie中 	aaa-123456
					Cookie cookie = new Cookie("username", username);
					// 设置有效时间
					cookie.setMaxAge(60*60);
					// 设置有效路径
					cookie.setPath("/");
					// 回写cookie
					response.addCookie(cookie);
				}
				
				// 登陆成功，跳转到某个页面，用户信息显示到页面上
				// 重定向到成功的页面
				// 显示用户的信息
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
