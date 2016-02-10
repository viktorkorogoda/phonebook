<!DOCTYPE HTML>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Phone Book</title>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link href="css/styles.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="scripts/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="scripts/jQuery/jquery-ui-1.11.4/jquery-ui.js"></script>
	
	<meta charset="UTF-8">

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
			<div id="add-contact-btn-div">
				<input type="button" id="open-add-form-btn" class="btn btn-info btn-add-new" value="Add new contact" onclick = "showAddDialog()">
			</div>
			<div id="edit-contact-btn-div">
				<input type="button" id="open-edit-form-btn" class="btn btn-info btn-add-new" value="Edit contact" onclick="getContactToEdit();showEditDialog();">
			</div>
			
				<div id="div-add-contact-form" title="Add new contact" class="ui-dialog rfAdd" style="background-color: #FFFFFF">
					
									
					<label for="lastname">Last Name<span class="star">*</span></label>
					<input type="text" id="last-name-add" name="lastname" class="text-input-add rfield" placeholder="Ivanov" required>
				
					<label for="firstname">First Name<span class="star">*</span></label>
					<input type="text" id="first-name-add" name="firstname" class="text-input-add rfield"  placeholder="Ivan" required>
						
					<label for="middlename">Middle Name<span class="star">*</span></label>
					<input type="text" id="middle-name-add" name="middlename" class="text-input-add rfield" placeholder="Ivanovich" required>
					
					<label for="address">Address<span class="star">*</span></label>
					<input type="text" id="address-add" name="address" class="text-input-add rfield" placeholder="Minsk, Nezavisimosty 100" required>
					
					<label for="birthdate">Birth date<span class="star">*</span></label>
					<input type="text" id="birth-date-add" name="birthdate" class="text-input-add rfield" placeholder="15.06.1992" required>
					
					<div id="phoneNumbersaddForm">
						<label for="phoneNumbersAddFormLabel">Phone numbers<span class="star">*</span></label>
						<br/>
						<label class="adedInputLabel" style="margin-right: 10px;">Is default</label>
							<label class="adedInputLabel" style="margin-right: 250px;">Type</label>
								<label class="adedInputLabel">Number</label>
					</div>
					<input type="button" value="Add phone" class="btn btn-info btn-add-new" onclick="addPhoneInputs('add')">
					
					<div id="emailsaddForm">
						<label for="emailsAddFormLabel"  style="margin-top: 10px;">Emails<span class="star">*</span></label>
						<br/>
						<label class="adedInputLabel" style="margin-right: 10px;">Is default</label>
							<label class="adedInputLabel" style="margin-right: 250px;">Type</label>
								<label class="adedInputLabel">Email address</label>
					</div>
					<input type="button" value="Add email" class="btn btn-info btn-add-new" onclick="addEmailInputs('add')">
					
					<br/>
					<input type="button" id="add-contact-btn" value="Add contact" class="btn btn-info btn-add-new btn_submit"  >	
											
				</div>
			
			<div id="div-edit-contact-form" title="Edit contact" class="ui-dialog rfEdit" style="background-color: #FFFFFF">
				
					<input type="hidden" id="idContact">
					
					<label for="lastname">Last Name<span class="star">*</span></label>
					<input type="text" id="last-name-edit" name="lastname" class="text-input-add rfield"  placeholder="Ivanov" required>
					
					<label for="firstname">First Name<span class="star">*</span></label>
					<input type="text" id="first-name-edit" name="firstname" class="text-input-add rfield" placeholder="Ivan" required>
				
					<label for="middlename">Middle Name<span class="star">*</span></label>
					<input type="text" id="middle-name-edit" name="middlename" class="text-input-add rfield" placeholder="Ivanovich" required>
					
					<label for="address">Address<span class="star">*</span></label>
					<input type="text" id="address-edit" name="address" class="text-input-add rfield" placeholder="Minsk, Nezavisimosty 100" required>
					
					<label for="birthdate">Birth date<span class="star">*</span></label>
					<input type="text" id="birth-date-edit" name="birthdate" class="text-input-add rfield" placeholder="15.06.1992" required>
					
					<div id="phoneNumberseditForm">
						<label for="phoneNumbersEditFormLabel">Phone numbers<span class="star">*</span></label>
						<br/>
						<label class="adedInputLabel" style="margin-right: 10px;">Is default</label>
							<label class="adedInputLabel" style="margin-right: 250px;">Type</label>
								<label class="adedInputLabel">Number</label>
					</div>
					<input type="button" value="Add phone" class="btn btn-info btn-add-new" onclick="addPhoneInputs('edit')">
				
					<div id="emailseditForm">
						<label for="emailsEditFormLabel" style="margin-top: 10px;">Emails<span class="star">*</span></label>
						<br>
						<label class="adedInputLabel" style="margin-right: 10px;">Is default</label>
							<label class="adedInputLabel" style="margin-right: 250px;">Type</label>
								<label class="adedInputLabel">Email address</label>
					</div>
					
					<input type="button" value="Add email" class="btn btn-info btn-add-new" onclick="addEmailInputs('edit')">
					<br>
					
					<input type="button" id="edit-contact-btn" value="Edit contact" class="btn btn-info btn-add-new btn_submit" ">	
						
			</div>
			
			<div>
				<c:if test="${not empty contacts}">
				
					<table class="user-table table table-bordered" id="client-table">
						<thead>
							<tr>
								<th></th>
								<th>First name Имя</th>
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
									<td><input type="radio" name="contactIds" value="${contact.idContact}"/></td>
									<td>${contact.firstName}</td>
									<td>${contact.middleName}</td>
									<td>${contact.lastName}</td>
									<td>${contact.birthDate}</td>
									<td>${contact.address}</td>
									<td>
										<c:forEach items="${contact.phoneList}" var="phone">
											${phone.type} : ${phone.number}	${phone.isDefault}
											<br/>
										</c:forEach>
									</td>
									<td>
										<c:forEach items="${contact.emailList}" var="email">
											${email.type}	:	${email.address}	${email.isDefault}
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
	<script type="text/javascript" src="scripts/scripts.js"></script>
	<script type="text/javascript">
		
		
		
	</script>
</body>
</html>