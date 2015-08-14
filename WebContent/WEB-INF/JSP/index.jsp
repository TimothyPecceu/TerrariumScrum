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
	<img src=<c:url value="/images/introImage.jpg"/>>
	<form method="post">
		<label><input type="number" name="breedte"
			placeholder="breedte" required autocomplete='off' /><span
			class='fout'>${fouten.breedte}</span></label><br> <label><input
			type="number" name="hoogte" placeholder="hoogte" required
			autocomplete='off' /><span class='fout'>${fouten.hoogte}</span></label><br>		
		<input type="submit" value="Maak terrarium" />
	</form>
</body>
</html>