<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p align="center">管理员登录 </p>
<table width="75%" align="center">
  
  <tr> 
    <td align="center" valign="top">
     <form name="loginform" action="login2.jsp">
      <table>
        <tr> 
          <td>帐 号：</td>
          <td><input name="username" type="text" ></td>
        </tr>
        <tr> 
          <td>密 码：</td>
          <td><input name="password" type="password" ></td>
        </tr>        
        <tr> 
          <td align="right">
	<input name="submit" type="submit" value="登录" onClick="javascript:return(checkform());">
          </td>
          <td><input name="reset" type="reset" value="重置"></td>
        </tr>
      </table>
      </form>
	</td>
  </tr>
</table>
</body>
</html>