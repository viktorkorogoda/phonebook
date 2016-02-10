/**
 * 
 */

		var phoneIndex = 0;
				
		addPhoneInputs = function(formType, phone) {
			phoneIndex++;
			
			var div = document.createElement("div");
			div.setAttribute("id", formType + "PhoneDiv" + phoneIndex);
			
			var type = document.createElement("input");
			type.setAttribute("id", formType + "TypePhone" + phoneIndex);
			type.setAttribute("name", "type");
			type.setAttribute("type", "text");
			type.setAttribute("class", "text-input-add phones-block rfield");
			type.setAttribute("placeholder", "home");
			
			var number = document.createElement("input");
			number.setAttribute("id", formType + "Number" + phoneIndex);
			number.setAttribute("name", "number");
			number.setAttribute("type", "text");
			number.setAttribute("class", "text-input-add phones-block rfield");
			number.setAttribute("placeholder", "+375 17 234 56 78");
			
			var isDefault = document.createElement("input");
			isDefault.setAttribute("id", formType + "IsDefaultPhone" + phoneIndex);
			isDefault.setAttribute("name", "phoneDefault");
			isDefault.setAttribute("type", "radio");
			isDefault.setAttribute("class", "defaultRadio");	
			isDefault.checked = true;
			
			if (phone !== undefined) {
				type.value = phone.type;
				number.value = phone.number;
				isDefault.value = phone.idPhone;
				isDefault.checked = phone.isDefault;
			}

			var removeBtn = document.createElement("input");
			removeBtn.setAttribute("id", formType + "RemoveBtn" + phoneIndex);
			removeBtn.setAttribute("type", "image");		
			removeBtn.setAttribute("src", "images/close.png");
			removeBtn.setAttribute("class", "delete-input-btn");	
			removeBtn.setAttribute("onclick", "$(\"#" + div.getAttribute("id") + "\").remove()");
				
			div.appendChild(isDefault);
			div.appendChild(type);
			div.appendChild(number);
			div.appendChild(removeBtn);
			
			
			$("#phoneNumbers" + formType + "Form").append(div);
		};
		
		
		var emailIndex = 0;
		
		addEmailInputs= function(formType, email) {
			emailIndex++;
			
			var divEmail = document.createElement("div");
			divEmail.setAttribute("id", formType + "EmailDiv" + emailIndex);
			
			var type = document.createElement("input");
			type.setAttribute("id", formType + "TypeEmail" + emailIndex);
			type.setAttribute("name", "type");
			type.setAttribute("type", "text");
			type.setAttribute("class", "text-input-add emails-block rfield");
			type.setAttribute("placeholder", "home");
			
			var address = document.createElement("input");
			address.setAttribute("id", formType + "Address" + emailIndex);
			address.setAttribute("name", "address");
			address.setAttribute("type", "email");
			address.setAttribute("class", "text-input-add emails-block rfield");
			address.setAttribute("placeholder", "email@mail.ru");
			
			var isDefault = document.createElement("input");
			isDefault.setAttribute("id", formType + "IsDefaultEmail" + emailIndex);
			isDefault.setAttribute("name", "emailDefault");
			isDefault.setAttribute("type", "radio");
			isDefault.setAttribute("class", "emails-block");
			isDefault.checked = true;
			
			if (email !== undefined) {
				type.value = email.type;
				address.value = email.address;
				isDefault.value = email.idEmail;
				isDefault.checked = email.isDefault;
			}

			var removeBtn = document.createElement("input");
			removeBtn.setAttribute("id", formType + "RemoveBtn" + emailIndex);
			removeBtn.setAttribute("type", "image");		
			removeBtn.setAttribute("src", "images/close.png");
			removeBtn.setAttribute("class", "delete-input-btn");		
			removeBtn.setAttribute("onclick", "$(\"#" + divEmail.getAttribute("id") + "\").remove()");
						
			divEmail.appendChild(isDefault);
			divEmail.appendChild(type);
			divEmail.appendChild(address);
			divEmail.appendChild(removeBtn);
			
			$("#emails" + formType + "Form").append(divEmail);
		};
		
		getContactDataJSON = function(formType) {			
			var firstName = $("#first-name-" + formType).val();
			var lastName = $("#last-name-" + formType).val();
			var middleName = $("#middle-name-" + formType).val();
			var address = $("#address-" + formType).val();
			var birthDate = $("#birth-date-" + formType).val();
			
			var phoneList = [];
			for (var i = 0; i < 50; i++) {
				if (!$("#" + formType + "Number" + i).val()) {
					continue;
				}
								
				var phone = {};
				phone.type = $("#" + formType + "TypePhone" + i).val();
				phone.number = $("#" + formType + "Number" + i).val();
				phone.isDefault = $("#" + formType + "IsDefaultPhone" + i).is(':checked');
				
				if(formType == "edit") {
					var isDefaultValue = $("#" + formType + "IsDefaultPhone" + i).val();
					if (isDefaultValue != 'on') {
						phone.idPhone = isDefaultValue;
					}
					phone.idContact = $("#idContact").val();
				}
				
				phoneList.push(phone);
			}
			
			var emailList = [];
			for (var i = 0; i < 50; i++) {
				if (!$("#" + formType + "Address" + i).val()) {
					continue;
				}
								
				var email = {};
				email.type = $("#" + formType + "TypeEmail" + i).val();
				email.address = $("#" + formType + "Address" + i).val();
				email.isDefault = $("#" + formType + "IsDefaultEmail" + i).is(':checked');
				
				if(formType == "edit") {
					var isDefaultValue = $("#" + formType + "IsDefaultEmail" + i).val();
					if (isDefaultValue != 'on') {
						email.idEmail = isDefaultValue;
					}
					email.idContact = $("#idContact").val();
				}
				
				emailList.push(email);
			}
			
			var contact = {
				firstName : firstName,
				lastName : lastName,
				middleName : middleName,
				address : address,
				birthDate : birthDate,
				phoneList : phoneList,
				emailList : emailList
			};
			
			if(formType == "edit"){
				contact.idContact = $("#idContact").val();
			}
			
			return JSON.stringify(contact);
		};		
		
		addNewContact = function() {
			var contactJSON = getContactDataJSON('add');
			
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url: "addNewContact",
				data : contactJSON,
				success : function (redirectURL) {
					window.location.href = redirectURL;
				}
			})
		};
		
		getContactToEdit = function() {		
			var idContact = $("input[name='contactIds']:checked").val();
			
			$.ajax({
				type : "GET",
				contentType : "application/json; charset=utf-8",
				url: "getContactById",
				data : {
					idContact : idContact
					},
				success : function(contact) {
					var formType = 'edit';
					$("#first-name-" + formType).val(contact.firstName);
					$("#last-name-" + formType).val(contact.lastName);
					$("#middle-name-" + formType).val(contact.middleName);
					$("#address-" + formType).val(contact.address);
					$("#birth-date-" + formType).val(contact.birthDate);
					$("#idContact").val(contact.idContact);
					
					for (var phoneIndex in contact.phoneList) {
						var phone = contact.phoneList[phoneIndex];
						addPhoneInputs("edit", phone);
					}
					
					for (var emailIndex in contact.emailList) {
						var email = contact.emailList[emailIndex];
						addEmailInputs("edit", email);
					}
				}
			});
		};
		
		sentEditContact = function() {
			var contactJSON = getContactDataJSON('edit');
			
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url: "editContact",
				data : contactJSON,
				success : function (redirectURL) {
					window.location.href = redirectURL;
				}
			})
		};

		$(document).ready(function(){
			addPhoneInputs("add");
		});
		$(document).ready(function(){
			addEmailInputs("add");
		});
		
		$("input[name='contactIds']").change(function() {
			if ($(this).is(':checked')) {
				$("tbody").children().css("background", "transparent");
				$(this).parent().parent().css("background-color", "#909090");
			}
		});
		$("#open-edit-form-btn").click(function(){
			var idContact = $("input[name='contactIds']:checked").val();
		});
		
		
		var addDialog;
		
		$(function() {
			
			addDialog = $("#div-add-contact-form").dialog({
			      autoOpen: false,
			      height: 750,
			      width: 700,
			      resizable: false,
			      modal: true
			    });
		});
		
		showAddDialog = function(dialogId) {
			$("#div-add-contact-form").find("input:text").val('');
			$("div[id^='addPhoneDiv']").remove();
			$("div[id^='addEmailDiv']").remove();
			addDialog.dialog("open");
		}
		
		
		var editDialog;
		
		$(function() {
			$("#div-edit-contact-form").find("input:text").val('');
			editDialog = $("#div-edit-contact-form").dialog({
			      autoOpen: false,
			      height: 750,
			      width: 700,
			      resizable: true,
			      modal: true
			    });
		});
		
		showEditDialog = function(dialogId) {
			$("#div-edit-contact-form").find("input:text").val('');
			$("div[id^='editPhoneDiv']").remove();
			$("div[id^='editEmailDiv']").remove();
			editDialog.dialog("open");
		}
		
		//validate email fields
		$("#emailsaddForm").on("blur", "input[id^='addAddress']", function() {
			if ($(this).val() != '') {
				var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
				if (pattern.test($(this).val())) {
					$(this).css({'border' : '1px solid #569b44'});
					valid =  true;
				} else {
					$(this).css({'border' : '1px solid #ff0000'});
				}
			} else {
				$(this).css({'border' : '1px solid #ff0000'});
			}
		});
		
		
		//Add form check fields	  
		$('.rfAdd').each(function() {
			// Объявляем переменные (форма и кнопка отправки)
			var form = $(this), btn = form.find('.btn_submit');

			// Добавляем каждому проверяемому полю, указание что поле пустое
			form.find('.rfield').addClass('empty_field');

			// Функция проверки полей формы
			function checkInput() {
				form.find('.rfield').each(function() {
					if ($(this).val() != '') {
						// Если поле не пустое удаляем класс-указание
						$(this).removeClass('empty_field');
					} else {
						// Если поле пустое добавляем класс-указание
						$(this).addClass('empty_field');

					}
				});
			}

			// Функция подсветки незаполненных полей
			function lightEmpty() {
				form.find('.empty_field').css({'border' : '1px solid #ff0000'});
				// Через полсекунды удаляем подсветку
				setTimeout(function() {
					form.find('.empty_field').removeAttr('style');
				}, 500);
			}

			// Проверка в режиме реального времени
			setInterval(function checkReal() {
				// Запускаем функцию проверки полей на заполненность
				checkInput();

				// Считаем к-во незаполненных полей
				var sizeEmpty = form.find('.empty_field').size();
				// Вешаем условие-тригер на кнопку отправки формы
				if (sizeEmpty > 0) {
					if (btn.hasClass('disabled')) {
						return false
					} else {
						btn.addClass('disabled')
					}
				} else {
					btn.removeClass('disabled')
				}
			}, 500);

			// Событие клика по кнопке отправить
			$("#add-contact-btn").click(function() {
				if ($(this).hasClass('disabled')) {
					// подсвечиваем незаполненные поля и форму не отправляем, если есть незаполненные поля
					lightEmpty();
				} else {
					// Все хорошо, все заполнено, отправляем форму
						addNewContact();
				
				}
			});

		
		});
		//Edit form check fields
		$('.rfEdit').each(function() {
			// Объявляем переменные (форма и кнопка отправки)
			var form = $(this), btn = form.find('.btn_submit');

			// Добавляем каждому проверяемому полю, указание что поле пустое
			form.find('.rfield').addClass('empty_field');

			// Функция проверки полей формы
			function checkInput() {
				form.find('.rfield').each(function() {
					if ($(this).val() != '') {
						// Если поле не пустое удаляем класс-указание
						$(this).removeClass('empty_field');
					} else {
						// Если поле пустое добавляем класс-указание
						$(this).addClass('empty_field');

					}
				});
			}

			// Функция подсветки незаполненных полей
			function lightEmpty() {
				form.find('.empty_field').css({'border' : '1px solid #ff0000'});
				// Через полсекунды удаляем подсветку
				setTimeout(function() {
					form.find('.empty_field').removeAttr('style');
				}, 500);
			}

			// Проверка в режиме реального времени
			setInterval(function checkReal() {
				// Запускаем функцию проверки полей на заполненность
				checkInput();

				// Считаем к-во незаполненных полей
				var sizeEmpty = form.find('.empty_field').size();
				// Вешаем условие-тригер на кнопку отправки формы
				if (sizeEmpty > 0) {
					if (btn.hasClass('disabled')) {
						return false
					} else {
						btn.addClass('disabled')
					}
				} else {
					btn.removeClass('disabled')
				}
			}, 500);
			
			$("#edit-contact-btn").click(function() {
				if ($(this).hasClass('disabled')) {
					// подсвечиваем незаполненные поля и форму не отправляем, если есть незаполненные поля
					lightEmpty();
				} else {

					// Все хорошо, все заполнено, отправляем форму
					sentEditContact();
				}
			});
		
		});
		