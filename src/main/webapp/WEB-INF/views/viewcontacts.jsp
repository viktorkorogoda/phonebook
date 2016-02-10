<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Phone book</title>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/jtable.css" rel="stylesheet" type="text/css"></link>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js" -->
<!-- 	type="text/javascript"></script> -->
<script src="scripts/jquery-2.1.3.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.js" type="text/javascript"></script>
<script src="scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>
<script src="scripts/jtable.2.4.0/external/json2.js" type="text/javascript"></script>
</head>
<body>
	<div class="mainArea">
		<div class="menu-container">
			<div class="menu" id="menu">
				<ul class="menu-tabs">
					<li id="main-page" class="menu-list"><a href="index">Main</a></li>
					<li id="orders-list" class="menu-list"><a href="viewcontacts">View contacts</a></li>
				</ul>
			</div>
		</div>
		<div class="content-area">
			<h1>Contact list</h1>

			<div>
				<c:if test="${not empty contacts}">
				
					<table class="user-table table table-bordered" id="client-table">
						<thead>
							<tr>
								<th>First name</th>
								<th>Middle name</th>
								<th>Last name</th>
								<th>Birth date</th>
								<th>Address</th>
								<th>Phone numbers</th>
								<th>Emails</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${contacts}" var="contact">
								<tr>
									<td>${contact.firstName}</td>
									<td>${contact.middleName}</td>
									<td>${contact.lastName}</td>
									<td>${contact.birthDate}</td>
									<td>${contact.address}</td>
									<td>
										<c:forEach items="${contact.phoneList}" var="phone">
											${phone.type} : ${phone.number} ${phone.isDefault}
											<br/>
										</c:forEach>
									</td>
									<td>
										<c:forEach items="${contact.emailList}" var="email">
											${email.type} : ${email.address} ${email.isDefault}
											<br/>
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty contacts}">
					<h3 align="center">
						There is no contacts in the database
					</h3>
				</c:if>
				
			</div>
		</div>
	</div>
</body>
</html>