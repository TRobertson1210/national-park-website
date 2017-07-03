<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="pageTitle" value="NPGeek Daily Survey" />
<%@include file="common/header.jspf"%>
<c:url var="formAction" value="/surveySubmit"/>
<div class="formPadding">
<form:form action="${formAction}" method="POST" modelAttribute="survey">
	<div class="form-group">
		<form:label path="parkCode">Your Favorite National Park:</form:label>
		<form:select path="parkCode">
			<c:forEach var="park" items="${parks}">
				<form:option value="${park.parkCode}"><c:out value="${park.parkName}"/></form:option>
			</c:forEach>
		</form:select>
	</div>
	
	
	<div class="form-group">
		<label for="email">Email:</label>
		<form:input path="email"/>
			<form:errors path="email" cssClass="error errorMessage"/>
	</div>
	<div class="form-group">
		<form:label path="state">Select State of Residence:</form:label>
		<form:select path="state">
			<form:option value="AL">Alabama</form:option>
			<form:option value="AK">Alaska</form:option>
			<form:option value="AZ">Arizona</form:option>
			<form:option value="AR">Arkansas</form:option>
			<form:option value="CA">California</form:option>
			<form:option value="CO">Colorado</form:option>
			<form:option value="CT">Connecticut</form:option>
			<form:option value="DE">Delaware</form:option>
			<form:option value="DC">District Of Columbia</form:option>
			<form:option value="FL">Florida</form:option>
			<form:option value="GA">Georgia</form:option>
			<form:option value="HI">Hawaii</form:option>
			<form:option value="ID">Idaho</form:option>
			<form:option value="IL">Illinois</form:option>
			<form:option value="IN">Indiana</form:option>
			<form:option value="IA">Iowa</form:option>
			<form:option value="KS">Kansas</form:option>
			<form:option value="KY">Kentucky</form:option>
			<form:option value="LA">Louisiana</form:option>
			<form:option value="ME">Maine</form:option>
			<form:option value="MD">Maryland</form:option>
			<form:option value="MA">Massachusetts</form:option>
			<form:option value="MI">Michigan</form:option>
			<form:option value="MN">Minnesota</form:option>
			<form:option value="MS">Mississippi</form:option>
			<form:option value="MO">Missouri</form:option>
			<form:option value="MT">Montana</form:option>
			<form:option value="NE">Nebraska</form:option>
			<form:option value="NV">Nevada</form:option>
			<form:option value="NH">New Hampshire</form:option>
			<form:option value="NJ">New Jersey</form:option>
			<form:option value="NM">New Mexico</form:option>
			<form:option value="NY">New York</form:option>
			<form:option value="NC">North Carolina</form:option>
			<form:option value="ND">North Dakota</form:option>
			<form:option value="OH">Ohio</form:option>
			<form:option value="OK">Oklahoma</form:option>
			<form:option value="OR">Oregon</form:option>
			<form:option value="PA">Pennsylvania</form:option>
			<form:option value="RI">Rhode Island</form:option>
			<form:option value="SC">South Carolina</form:option>
			<form:option value="SD">South Dakota</form:option>
			<form:option value="TN">Tennessee</form:option>
			<form:option value="TX">Texas</form:option>
			<form:option value="UT">Utah</form:option>
			<form:option value="VT">Vermont</form:option>
			<form:option value="VA">Virginia</form:option>
			<form:option value="WA">Washington</form:option>
			<form:option value="WV">West Virginia</form:option>
			<form:option value="WI">Wisconsin</form:option>
			<form:option value="WY">Wyoming</form:option>
		</form:select>
	</div>
	<div class="form-group">
		<form:label path="activityLevel">Your Activity Level</form:label>
		<form:select path="activityLevel">
			<form:option value="inactive">Inactive</form:option>
			<form:option value="sedentary">Sedentary</form:option>
			<form:option value="active">Active</form:option>
			<form:option value="extremelyActive">Extremely Active</form:option>
		</form:select>
	</div>
	<div class="form-group">
		<input type="submit" class="submit-button" value="Submit"/>
	</div>


</form:form>
</div>





<%@include file="common/footer.jspf"%>