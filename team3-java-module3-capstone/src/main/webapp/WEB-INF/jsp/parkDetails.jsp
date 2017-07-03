<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="${park.parkName} Details" />
<%@include file="common/header.jspf"%>
<c:url var="imgUrl" value="/img/parks/${park.parkCode}.jpg"/>
<div id="details">
	<img src="${imgUrl}" class="detailsPic">
	<p class="quote"><span>&ldquo;</span><c:out value="${park.inspirationalQuote}"/><span>&rdquo;</span></p>
	<p class="quoteSource"><c:out value="--${park.inspirationalQuoteSource}"/></p>
	<h3><c:out value="${park.parkName}"/><span class="littletext">(Park Code: <c:out value="${park.parkCode}"/>)</span></h3>
	<p class="description"><c:out value="${park.description}"/></p>
	<div class="media-left info-list">
		<ul>
			<li>State: <c:out value="${park.state}"/></li>
			<li>Year Founded: <c:out value="${park.yearFounded}"/></li>
			<li>Climate: <c:out value="${park.climate}"/></li>
			<li>Size: <c:out value="${park.acreage}"/> acres</li>
			<li>Elevation: <c:out value="${park.elevationInFeet}"/>ft.</li>
			<li>Over <c:out value="${park.numberOfAnimalSpecies}"/> species</li>
		</ul>
	</div>
	<div class="media-right info-list">
		<ul>
			<li>Number of Campsites: <c:out value="${park.numberOfCampsites}"/></li>
			<li>Annual Visitor Count: <c:out value="${park.annualVisitorCount}"/></li>
			<li>Miles of Trails: <c:out value="${park.milesOfTrail}"/></li>
			<li>Entry Fee: 
			<c:choose>
				<c:when test="${park.entryFee==0}">
        			Free! 
        		</c:when>    
				<c:otherwise>
					$<c:out value="${park.entryFee}"/> 
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
<c:forEach var="daysWeather" items="${weather}">
	
	<div id="day${daysWeather.fiveDayForecastValue}">
		<c:url var="weatherImgUrl" value="/img/weather/"/>
		<img src="${weatherImgUrl}${daysWeather.forecast}.png"/>
		<div>
		High: <c:choose>
		<c:when test="${celcius}">
			<fmt:formatNumber maxFractionDigits="0"><c:out value="${(daysWeather.high - 32) * 5 / 9}"/></fmt:formatNumber>&deg;C
		</c:when>
		<c:otherwise>
		<c:out value="${daysWeather.high}"/>&deg;F
		</c:otherwise>
		</c:choose>
		Low: <c:choose>
		<c:when test="${celcius}">
			<fmt:formatNumber maxFractionDigits="0"><c:out value="${(daysWeather.low - 32) * 5 / 9}"/></fmt:formatNumber>&deg;C
		</c:when>
		<c:otherwise>
		<c:out value="${daysWeather.low}"/>&deg;F
		</c:otherwise>
		</c:choose>
		</div>
	</div>
</c:forEach>
<div class="advisory">
<c:choose>
	<c:when test="${todaysWeather.forecast == 'sunny'}">
		<c:out value="There's a sun advisory, pack some extra sunblock."/>
	</c:when>
	<c:when test="${todaysWeather.forecast == 'snow'}">
		<c:out value="Make sure you pack your snowshoes."/>
	</c:when>
	<c:when test="${todaysWeather.forecast == 'rain'}">
		<c:out value="Pack raingear and waterproof shoes!"/>
	</c:when>
	<c:when test="${todaysWeather.forecast == 'thunderstorms'}">
		<c:out value="Seek shelter and avoid hiking on exposed ridges!"/>
	</c:when>
	<c:when test="${todaysWeather.high > 75}">
		<c:out value="It's hot, make sure to bring an extra gallon of water."/>
	</c:when>
	<c:when test="${todaysWeather.high - todaysWeather.low > 20}">
		<c:out value="There's a great variation in temperatures, wear breathable layers!"/>
	</c:when>
	<c:when test="${todaysWeather.low < 20}">
		<c:out value="Beware exposure to frigid temperatures, wear layers!"/>
	</c:when>
</c:choose>
</div>
<div id="temperatureChange">
	<c:url var="formAction" value="/parkDetails"/>
	<form method="POST" action="${formAction}">
		<div class="btn btn-danger">
			<input type="hidden" name="tempType" value="F"/>
			<input type="hidden" name="parkCode" value="${park.parkCode}"/>
			<input type="submit" name="false" value="Fahrenheit" cssClass="textColor"/>
		</div>
	</form>
	<form method="POST" action="${formAction}">
		<div class="btn btn-primary">
			<input type="hidden" name="tempType" value="C"/>
			<input type="hidden" name="parkCode" value="${park.parkCode}"/>
			<input type="submit" name="true" value="Celcius" cssClass="textColor"/>
		</div>
	</form>
	
</div>




<%@include file="common/footer.jspf"%>