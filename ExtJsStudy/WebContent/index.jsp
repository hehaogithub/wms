<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hh.domain.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理主页</title>
<link rel="stylesheet" type="text/css"
	href="Demo/ExtJs4/resources/css/ext-all.css">

<script type="text/javascript" src="Demo/ExtJs4/ext-all.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript" src="Demo/app.js"></script>
</head>
<body>
<%       
        User loginUser = (User) session.getAttribute("loginaccount");
        if(loginUser == null){
            response.sendRedirect("login.jsp");
            return;
        }
        String loginAccount = loginUser.getUser_loginaccount();
        String name = loginUser.getUser_name();
     %>
     <script>
      var account = '<%=loginAccount%>';
      var name = '<%=name%>';
     </script>
     
	<iframe id='contentIframe' name='contentIframe'
		style='height: 100%; width: 100%'></iframe>
</body>
</html>