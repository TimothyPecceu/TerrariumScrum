<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Terrarium</title>
</head>
<body>
	<img src=<c:url value="/images/introImage.jpg"/>>
	<form method="post">
		<input type="text" name="breedte" placeholder="breedte" required/> <input type="text" name="hoogte" placeholder="hoogte" required/>
		<input type="submit" value="Maak terrarium"/>
	</form>
</body>
</html>