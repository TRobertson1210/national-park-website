<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Favorite Park Survey Results" />
<%@include file="common/header.jspf"%>
<h1 style="padding-left: 20px;">National Park Geeks' Favorite Parks</h1>
<div class="winnerMessage">
<img src="img/parks/${rankMap.keySet().toArray()[0].parkCode}.jpg"/>
Winner: <c:out value="${rankMap.keySet().toArray()[0].parkName}!"/>
</div>
<table class="table">
	<tr>
		<th>Park</th>
		<th># of Votes</th>
	</tr>
	<c:forEach var="park" items="${rankMap.keySet()}">
		<tr>
			<td>
			<c:out value="${park.parkName}"/>
			</td>
			<td>
			<c:out value="${rankMap.get(park)}"/>
			</td>
		</tr>
	</c:forEach>
</table>
<%@include file="common/footer.jspf"%>