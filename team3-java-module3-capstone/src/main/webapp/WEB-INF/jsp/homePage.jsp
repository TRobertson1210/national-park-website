<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="National Park Geek Home Page" />
<%@include file="common/header.jspf"%>

<c:forEach var="thisPark" items="${parks}">
	<c:url var="imgSrc" value="/img/parks/"/>
	<div class="media">
		<div class="media-left">
			<c:url var="parkDetailsUrl" value="/parkDetails">
				<c:param name="parkCode" value="${thisPark.parkCode}"/>
			</c:url>
			<a href="${parkDetailsUrl}"><img src="${imgSrc}${thisPark.parkCode}.jpg"/></a>
		</div>
		<div class="media-right info-box">
			<h3><c:out value="${thisPark.parkName} - ${thisPark.state}"/></h3>
			<c:out value="${thisPark.description}"/>
			<br><br><br>
			<span class="quote">"<c:out value="${thisPark.inspirationalQuote}"/>" -<c:out value="${thisPark.inspirationalQuoteSource}"/></span>
			<br><br><a href="${parkDetailsUrl}"><div class="btn btn-success button">
				Details
			</div></a>
		</div>
	</div>
</c:forEach>

<%@include file="common/footer.jspf"%>