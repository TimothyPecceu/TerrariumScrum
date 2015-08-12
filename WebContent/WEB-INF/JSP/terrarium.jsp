<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href=<c:url value="/styles/default.css"/>>
<title>Terrarium</title>
</head>
<body>
	<h1>Terrarium</h1>
	
	<c:forEach items="${terrarium}" var="entry">
		Key = ${entry.key.xLocatie}, <img src=<c:url value="/images/${entry.value}.png" /> title="${entry.value}"/><br>
	</c:forEach>
	<form>
		<input type="submit" value="volgende dag" name="volgendeDag" />
	</form>
</body>
</html>