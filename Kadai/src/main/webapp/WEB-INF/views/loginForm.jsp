<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Session サンプル</title>
<style type="text/css">
.error { color: #ff0000; }
table {
	border-collapse: separate;
	border-spacing: 10px;
}
</style>
</head>
<body>
	<h1>ログイン画面</h1>
	<hr />
	<form:form modelAttribute="loginModel">
	<div class="error">${errorMessage}</div>
	<table>
	<tr>
		<td>ログインID</td>
		<td><form:input path="userId" /></td>
		<td><form:errors path="userId" element="div" cssClass="error" /></td>
	</tr>
	<tr>
		<td>パスワード</td>
		<td><form:password path="password" /></td>
		<td><form:errors path="password" element="div" cssClass="error" /></td>
	</tr>
	<tr><td colspan="3"><input type="submit" value="ログイン" /></td></tr>
	</table>
	</form:form>
	<hr />

</body>
</html>