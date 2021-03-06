
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
	<h2>Dag ${terrarium.dag}</h2>
	<table>

		<c:forEach items="${terrarium.organismes}" var="organismes">
			<tr>
				<c:forEach items="${organismes}" var="organisme" varStatus="status">

					<c:choose>
						<c:when test="${not empty organisme}">
							<td><div class="image"><img class='vak'
								src=<c:url value="/images/${organisme}.png"/>>
								<c:if test="${organisme.levenskracht > 0}">
								<span class='levenskracht'><c:out value="${organisme.levenskracht}"/></span>
								</c:if></div></td>
						</c:when>
						<c:otherwise>
							<td><img class='vak' src=<c:url value="/images/aarde.jpg"/>></td>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</tr>	
		</c:forEach>

	</table>
	<form method="get">
		<input class='btn' type="submit" value="volgende dag" name="volgendeDag" />
	</form>
	<form method="post">

		<label> <input type="number" name="breedteNieuw"
			placeholder="Breedte" required autocomplete='off' /><span
			class='fout'>${fouten.breedte}</span>
		</label><br> <label><input type="number" name="hoogteNieuw"
			placeholder="Hoogte" required autocomplete='off' /><span class='fout'>${fouten.hoogte}</span></label><br>
		<input class='btn' type="submit" value="Nieuw Terrarium">
	</form>
</body>
</html>