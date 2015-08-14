
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
	<form method="post">
		<table>
			<c:forEach items="${terrarium.organismes}" var="organismes" varStatus="y">
				<tr>
					<c:forEach items="${organismes}" var="organisme" varStatus="x">
						<td class='vak' background=<c:url value="/images/aarde.jpg"/>><input type="checkbox" checked="checked" name="posities" value="${y.index},${x.index}"></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>

		<label><input type="number" name="aantalPlanten"
			placeholder="aantal Planten" autocomplete='off' /><span class='fout'>${fouten.planten}</span></label><br>
		<label><input type="number" name="aantalHerbivoren"
			placeholder="aantal Herbivoren" autocomplete='off' /><span
			class='fout'>${fouten.herbivoren}</span></label><br> <label><input
			type="number" name="aantalCarnivoren" placeholder="aantal Carnivoren"
			autocomplete='off' /><span class='fout'>${fouten.carnivoren}</span></label><br>
		<label><input type="number" name="aantalMensen"
			placeholder="aantal Mensen" autocomplete='off' /><span class='fout'>${fouten.mensen}</span></label><br>
		<input type="submit" value="Maak terrarium" />
	</form>
</body>
</html>