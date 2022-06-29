$(document).ready(function () {
  // ------------------------ SHOW MODALS ---------------------------- //
  $(".container-xl .addStudentButton").on("click", function () {
    $(".myFormThree #addStudentModal").modal("show");
  });
  $(".container-xl .addTeacherButton").on("click", function () {
    $(".AddTeacherForm #addTeacherModal").modal("show");
  });
  $(".container-xl .addJogathonButton").on("click", function () {
    $(".addJogathonForm #addJogathonModal").modal("show");
  });
  $(".container-xl .addSponsorButton").on("click", function () {
    $(".addSponsorForm #addSponsorModal").modal("show");
  });
  $(".container-xl .addFamilyButton").on("click", function () {
    $(".addFamilyForm #addFamilyModal").modal("show");
  });
  $(".container-xl .addPledgeTypeButton").on("click", function () {
    $(".addPledgeTypeForm #addPledgeTypeModal").modal("show");
  });
  $(".container-xl .addClassroomButton").on("click", function () {
    $(".addClassroomForm #addClassroomModal").modal("show");
  });
  $(".container-xl .addPledgeButton").on("click", function () {
    $(".addPledgeForm #addPledgeModal").modal("show");
  });
  $("header #registerBtn").on("click", function () {
    $(".registerForm #registerModal").modal("show");
  });
  $("header #loginBtn").on("click", function () {
    $(".loginForm #loginModal").modal("show");
  });
  $(".container-xl .addPersonButton").on("click", function () {
    $(".addPersonForm #addPersonModal").modal("show");
  });
  $(".myFormThree #addStudent123").on("click", function (event) {
    event.preventDefault();
				// PREPARE FORM DATA
				var student = {
					firstName : $("#firstName").val(),
					lastName : $("#lastName").val(),
					contact : $("#contact").val(),
          gradeId : $("#grade").val(),
          familyId : $("#family").val()
				}
        
				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "addStudentTest",
					data : JSON.stringify(student),
					dataType : 'json',
					success : function(result) {
						if (result == "undefined" || result.length == 0) {
							location.href = "/students";
						} else {
							console.log(result);
              $(".myFormThree .errors").html('<p class="alert alert-danger errorMessage" id="errors">Error message</p>');
						}
						console.log(result);
					},
					error : function(e) {
						$(".myFormThree .errors").html('<p class="alert alert-danger errorMessage" id="errors">Please your input and try again.</p>');
						console.log("ERROR: ", e);
					}
				});
  });

  // ------------------------ EDIT STUDENT MODAL ---------------------------- //
  $(".table .eBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    console.log(href)
    $.get(href, function (list, status) {
      var student = list[0][0];
      var grade = list[1][0];
      var family = list[2][0];
      $(".myForm #id").val(student.id);
      $(".myForm #firstName").val(student.firstName);
      $(".myForm #lastName").val(student.lastName);
      $(".myForm #contact").val(student.contact);
      $(".activeStatus .selectpicker").selectpicker(
        "val",
        "" + student.active + ""
      );
      $("#grade option[value='" + grade.id + "']").prop("selected", true);
      if (family == null) {
        $(".familyDrop .selectpicker").selectpicker("val", "");
      } else {
        $(".familyDrop .selectpicker").selectpicker("val", "" + family.id + "");
      }
    });
    $(".myForm #editStudentModal").modal("show");
  });

  // ------------------------ EDIT LAPS MODAL ---------------------------- //
  $(".table .LBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (lap, status) {
      $(".editLapsForm #id").val(lap.id);
      $(".editLapsForm #laps").val(lap.laps);
    });
    $(".editLapsForm #editLapsModal").modal("show");
  });

  // ------------------------ EDIT TEACHER MODAL ---------------------------- //
  $(".table .etBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var teacher = objects[0];
      var family = objects[1];
      $(".editTeacherForm #id").val(teacher.id);
      $(".editTeacherForm #firstName").val(teacher.firstName);
      $(".editTeacherForm #lastName").val(teacher.lastName);
      $(".editTeacherForm #contact").val(teacher.contact);
      $(".familyDropT .selectpicker").selectpicker("val", "" + family.id + "");
      $(".activeStatusT .selectpicker").selectpicker(
        "val",
        "" + teacher.active + ""
      );
    });
    $(".editTeacherForm #editTeacherModal").modal("show");
  });

  // ------------------------ EDIT JOGATHON MODAL ---------------------------- //
  $(".table .ejBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (jogathon, status) {
      $(".editJogathonForm #id").val(jogathon.id);
      const [year, month, day] = jogathon.runDate.split("-");
      const result = [month, day, year].join("/");
      $(".editJogathonForm #runDate").val(result);
      $(".editJogathonForm #comments").val(jogathon.comments);
      $(".activeStatusJ .selectpicker").selectpicker(
        "val",
        "" + jogathon.active + ""
      );
    });
    $(".editJogathonForm #editJogathonModal").modal("show");
  });

  // ------------------------ EDIT SPONSOR MODAL ---------------------------- //
  $(".table .esBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (sponsor, status) {
      $(".editSponsorForm #id").val(sponsor.id);
      $(".editSponsorForm #firstName").val(sponsor.firstName);
      $(".editSponsorForm #lastName").val(sponsor.lastName);
      $(".editSponsorForm #phone").val(sponsor.phone);
      $(".editSponsorForm #addressOne").val(sponsor.addressOne);
      $(".editSponsorForm #addressTwo").val(sponsor.addressTwo);
      $(".editSponsorForm #city").val(sponsor.city);
      $(".editSponsorForm #stateOf").val(sponsor.stateOf);
      $(".editSponsorForm #zip").val(sponsor.zip);
    });
    $(".editSponsorForm #editSponsorModal").modal("show");
  });

  // ------------------------ EDIT FAMILY MODAL ---------------------------- //
  $(".table .efBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (listOfLists, status) {
      var family = listOfLists[0];
      var persons = listOfLists[1];
      var fam = family[0];
      var availPersons = listOfLists[2];

      var options = [];
      availPersons.forEach(function (item) {
        var option = '<option value="' + item.id + '">' + item.firstName +  ' ' + item.lastName + '</option>'
        options.push(option);
      });
      
      $('.personAvail .selectpicker').selectpicker('destroy');
      $('.personAvail #person').addClass('selectpicker');
      $('.personAvail .selectpicker').html(options);

      $(".editFamilyForm #id").val(fam.id);
      $(".editFamilyForm #familyName").val(fam.familyName);
      $(".editFamilyForm #phone").val(fam.phone);
      $(".editFamilyForm #addressOne").val(fam.addressOne);
      $(".editFamilyForm #addressTwo").val(fam.addressTwo);
      $(".editFamilyForm #city").val(fam.city);
      $(".editFamilyForm #stateOf").val(fam.stateOf);
      $(".editFamilyForm #zip").val(fam.zip);
      $(".activeStatusF .selectpicker").selectpicker(
        "val",
        "" + fam.active + ""
      );
      let ids = [];
      persons.forEach((element) => {
        console.log(element);
        ids.push(element.id);
      });
      var strIds = "[";
      for (i = 0; i < ids.length; i++) {
        strIds += '"' + ids[i] + '",';
      }
      strIds = strIds.slice(0, -1);
      strIds += "]";
      console.log(strIds);
      $(".personAvail .selectpicker").selectpicker(
        "val",
        JSON.parse(strIds)
      );
    });
    $(".editFamilyForm #editFamilyModal").modal("show");
  });

  // ------------------------ EDIT PLEDGE TYPE MODAL ---------------------------- //
  $(".table .ePBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (pledgeType, status) {
      $(".editPledgeTypeForm #id").val(pledgeType.id);
      $(".editPledgeTypeForm #pledgeName").val(pledgeType.pledgeName);
      $(".editPledgeTypeForm .selectpicker").selectpicker(
        "val",
        "" + pledgeType.active + ""
      );
    });
    $(".editPledgeTypeForm #editPledgeTypeModal").modal("show");
  });

  // ------------------------ EDIT CLASSROOM MODAL ---------------------------- //
  $(".table .ecBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (listOfLists, status) {
      var classroom = listOfLists[0][0];
      var grades = listOfLists[1];
      var availGrades = listOfLists[2];

      var options = [];
      availGrades.forEach(function (item) {
        var option = '<option value="' + item.id + '">' + item.gradeName + '</option>'
        options.push(option);
      });
      
      $('.gradeStatus .selectpicker').selectpicker('destroy');
      $('.gradeStatus #grade').addClass('selectpicker');
      $('.gradeStatus .selectpicker').html(options);
      //$('.gradeStatus .selectpicker').selectpicker('refresh');

      $(".editClassroomForm #id").val(classroom.id);
      $(".editClassroomForm #className").val(classroom.className);
      $(".activeStatusC .selectpicker").selectpicker(
        "val",
        "" + classroom.active + ""
      );
      let ids = [];
      var strIds = "[";
      if (grades.length != 0) {
        grades.forEach((element) => {
          ids.push(element.id);
        });
        for (i = 0; i < ids.length; i++) {
          strIds += '"' + ids[i] + '",';
        }
        strIds = strIds.slice(0, -1);
        strIds += "]";
        $(".gradeStatus .selectpicker").selectpicker("val", JSON.parse(strIds));
      } else {
        $(".gradeStatus .selectpicker").selectpicker("show");
      }
      
    });
    $(".editClassroomForm #editClassroomModal").modal("show");
  });

  // ------------------------ EDIT PLEDGE MODAL ---------------------------- //

  $(".table .eplBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var pledge = objects[0];
      var person = objects[1];
      var pledgeType = objects[2];
      var sponsor = objects[3];
      $(".editPledgeForm #id").val(pledge.id);
      $(".editPledgeForm #oneTime").val(pledge.oneTime);
      $(".editPledgeForm #perLap").val(pledge.perLap);
      $(".editPledgeForm #week").val(pledge.week);
      $(".collectedDrop .selectpicker").selectpicker(
        "val",
        "" + pledge.collected + ""
      );
      $(".receiptDrop .selectpicker").selectpicker(
        "val",
        "" + pledge.receipt + ""
      );
      $(".personDrop .selectpicker").selectpicker("val", "" + person.id + "");
      $(".pledgeTypeDrop .selectpicker").selectpicker(
        "val",
        "" + pledgeType.id + ""
      );
      if (sponsor != null) {
        $(".sponsorDrop .selectpicker").selectpicker(
          "val",
          "" + sponsor.id + ""
        );
      } else {
        $(".sponsorDrop .selectpicker").selectpicker("val", "0");
      }
      $(".activeDrop .selectpicker").selectpicker(
        "val",
        "" + pledge.active + ""
      );
    });
    $(".editPledgeForm #editPledgeModal").modal("show");
  });

  // ------------------------ EDIT PERSON MODAL ---------------------------- //
  $(".table .personEBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (list, status) {
      var person = list[0][0];
      var family = list[1][0];
      $(".editPersonForm #id").val(person.id);
      $(".editPersonForm #firstName").val(person.firstName);
      $(".editPersonForm #lastName").val(person.lastName);
      $(".editPersonForm #contact").val(person.contact);
      if (family == null) {
        $(".familyDropP .selectpicker").selectpicker("val", "");
      } else {
        $(".familyDropP .selectpicker").selectpicker(
          "val",
          "" + family.id + ""
        );
      }
    });
    $(".editPersonForm #editPersonModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE STUDENT MODAL ---------------------------- //
  $(".table .eBtnTwo").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (student, status) {
      $(".myFormTwo #id").val(student.id);
    });
    $(".myFormTwo #de-activateStudentModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE TEACHER MODAL ---------------------------- //
  $(".table .dtBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (person, status) {
      $(".deactivateTeacherForm #id").val(person.id);
    });
    $(".deactivateTeacherForm #de-activateTeacherModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE JOGATHON MODAL ---------------------------- //
  $(".table .djBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (jogathon, status) {
      $(".deactivateJogathonForm #id").val(jogathon.id);
    });
    $(".deactivateJogathonForm #de-activateJogathonModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE SPONSOR MODAL ---------------------------- //
  $(".table .dsBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (sponsor, status) {
      $(".deactivateSponsorForm #id").val(sponsor.id);
    });
    $(".deactivateSponsorForm #de-activateSponsorModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE FAMILY MODAL ---------------------------- //
  $(".table .dfBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (list, status) {
      var fam = list[0][0];
      $(".deactivateFamilyForm #id").val(fam.id);
    });
    $(".deactivateFamilyForm #de-activateFamilyModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE PERSON TYPE MODAL ---------------------------- //
  $(".table .dptBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (personType, status) {
      $(".deactivatePersonTypeForm #id").val(personType.id);
    });
    $(".deactivatePersonTypeForm #de-activatePersonTypeModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE CLASSROOM MODAL ---------------------------- //
  $(".table .dcBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (listOfLists, status) {
      var classroom = listOfLists[0][0];
      $(".deactivateClassroomForm #id").val(classroom.id);
    });
    $(".deactivateClassroomForm #de-activateClassroomModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE PLEDGE MODAL ---------------------------- //
  $(".table .dplBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var pledge = objects[0];
      $(".deactivatePledgeForm #id").val(pledge.id);
    });
    $(".deactivatePledgeForm #de-activatePledgeModal").modal("show");
  });

  // ------------------------ DE-ACTIVATE PERSON MODAL ---------------------------- //
  $(".table .personDEBTN").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (listOfLists, status) {
      var classroom = listOfLists[0][0];
      $(".deactivatePersonForm #id").val(classroom.id);
    });
    $(".deactivatePersonForm #de-activatePersonModal").modal("show");
  });

  // ------------------------ DETAIL STUDENT MODAL ---------------------------- //
  $(".table .dBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (list, status) {
      var student = list[0][0];
      var grade = list[1][0];
      var classroom = list[2][0];
      var family = list[3][0];
      $(".myFormFour #id").val(student.id);
      $(".myFormFour #firstName").val(student.firstName);
      $(".myFormFour #lastName").val(student.lastName);
      $(".myFormFour #contact").val(student.contact);
      $(".myFormFour #grade").val(grade.gradeName);
      if (classroom == null) {
        $(".myFormFour #classroom").val("None");
      } else {
        $(".myFormFour #classroom").val(classroom.className);
      }
      if (family == null) {
        $(".myFormFour #family").val("None");
      } else {
        $(".myFormFour #family").val(family.familyName);
      }
      $(".myFormFour .selectpicker").selectpicker(
        "val",
        "" + student.active + ""
      );
    });
    $(".myFormFour #detailsStudentModal").modal("show");
  });

  // ---------------------------- DETAIL TEACHER MODAL -------------------------//
  $(".table .tdBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var student = objects[0];
      var family = objects[1];
      $(".teacherDetailsForm #id").val(student.id);
      $(".teacherDetailsForm #firstName").val(student.firstName);
      $(".teacherDetailsForm #lastName").val(student.lastName);
      $(".teacherDetailsForm #contact").val(student.contact);
      if (family == null) {
        $(".teacherDetailsForm #family").val("None");
      } else {
        $(".teacherDetailsForm #family").val(family.familyName);
      }
      $(".teacherDetailsForm .selectpicker").selectpicker(
        "val",
        "" + student.active + ""
      );
    });
    $(".teacherDetailsForm #detailsTeacherModal").modal("show");
  });

  // ------------------------ DETAIL SPONSOR MODAL ---------------------------- //
  $(".table .sdBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (sposnor, status) {
      $(".sponsorDetailsForm #id").val(sposnor.id);
      $(".sponsorDetailsForm #firstName").val(sposnor.firstName);
      $(".sponsorDetailsForm #lastName").val(sposnor.lastName);
      $(".sponsorDetailsForm #phone").val(sposnor.phone);
      $(".sponsorDetailsForm #addressOne").val(sposnor.addressOne);
      $(".sponsorDetailsForm #addressTwo").val(sposnor.addressTwo);
      $(".sponsorDetailsForm #city").val(sposnor.city);
      $(".sponsorDetailsForm #stateOf").val(sposnor.stateOf);
      $(".sponsorDetailsForm #zip").val(sposnor.zip);
    });
    $(".sponsorDetailsForm #detailsSponsorModal").modal("show");
  });

  // ------------------------ DETAIL FAMILY MODAL ---------------------------- //
  $(".table .fdBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (listOfLists, status) {
      var fam = listOfLists[0][0];
      var persons = listOfLists[1];
      $(".familyDetailsForm #id").val(fam.id);
      $(".familyDetailsForm #familyName").val(fam.familyName);
      $(".familyDetailsForm #phone").val(fam.phone);
      $(".familyDetailsForm #addressOne").val(fam.addressOne);
      $(".familyDetailsForm #addressTwo").val(fam.addressTwo);
      $(".familyDetailsForm #city").val(fam.city);
      $(".familyDetailsForm #stateOf").val(fam.stateOf);
      $(".familyDetailsForm #zip").val(fam.zip);
      $(".familyDetailsForm .selectpicker").selectpicker(
        "val",
        "" + fam.active + ""
      );
      //$("#person option[value='" + element.id + "']").prop("selected", true);
      $('.familyDetailsForm #person').empty();
      $.each(persons, function (i, item) {
        $('.familyDetailsForm #person').append($('<option>', { 
            value: item.id,
            text : item.firstName + ' ' + item.lastName 
        }));
      });

    });
    $(".familyDetailsForm #detailsFamilyModal").modal("show");
  });

  // ---------------------------- DETAIL PERSON TYPE MODAL 2 -------------------------//
  $(".table .assBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (persons, status) {
      persons.forEach((element) => {
        $(".personAssDetailsForm #deets").append(
          "<p>" +
            element.id +
            ": " +
            element.firstName +
            " " +
            element.lastName +
            "</p>"
        );
      });
    });
    $(".personAssDetailsForm #detailsPersonAssModal").modal("show");
  });

  // ---------------------------- DETAIL PLEDGE TYPE MODAL -------------------------//
  $(".table .PdBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (pledgeType, status) {
      $(".pledgeTypeDetailsForm #id").val(pledgeType.id);
      $(".pledgeTypeDetailsForm #pledgeName").val(pledgeType.pledgeName);
      $(".pledgeTypeDetailsForm .selectpicker").selectpicker(
        "val",
        "" + pledgeType.active + ""
      );
    });
    $(".pledgeTypeDetailsForm #detailsPledgeTypeModal").modal("show");
  });

  // ---------------------------- DETAIL CLASSROOM MODAL 2 -------------------------//
  $(".table .assBtn2").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (persons, status) {
      persons.forEach((element) => {
        $(".studentAssDetailsForm #deetsStudents").append(
          "<p>" +
            element.id +
            ": " +
            element.firstName +
            " " +
            element.lastName +
            "</p>"
        );
      });
    });
    $(".studentAssDetailsForm #detailsStudentAssModal").modal("show");
  });

  // ------------------------ DETAIL PLEDGE MODAL ---------------------------- //
  $(".table .pldBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var pledge = objects[0];
      var person = objects[1];
      var pledgeType = objects[2];
      var sponsor = objects[3];
      $(".pledgeDetailsForm #total").val(pledge.total);
      $(".pledgeDetailsForm #oneTime").val(pledge.oneTime);
      $(".pledgeDetailsForm #perLap").val(pledge.perLap);
      $(".pledgeDetailsForm #week").val(pledge.week);
      $(".collectedDropD .selectpicker").selectpicker(
        "val",
        "" + pledge.collected + ""
      );
      $(".receiptDropD .selectpicker").selectpicker(
        "val",
        "" + pledge.receipt + ""
      );
      $(".personDropD .selectpicker").selectpicker("val", "" + person.id + "");
      $(".pledgeTypeDropD .selectpicker").selectpicker(
        "val",
        "" + pledgeType.id + ""
      );
      if (sponsor != null) {
        $(".sponsorDropD .selectpicker").selectpicker(
          "val",
          "" + sponsor.id + ""
        );
      } else {
        $(".sponsorDropD .selectpicker").selectpicker("val", "0");
      }
      $(".activeDropD .selectpicker").selectpicker(
        "val",
        "" + pledge.active + ""
      );
    });
    $(".pledgeDetailsForm #detailsPledgeModal").modal("show");
  });

  // ------------------------ DETAIL PERSON MODAL ---------------------------- //
  $(".table .personDBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (list, status) {
      var person = list[0][0];
      var family = list[1][0];
      $(".detailsPersonForm #id").val(person.id);
      $(".detailsPersonForm #firstName").val(person.firstName);
      $(".detailsPersonForm #lastName").val(person.lastName);
      $(".detailsPersonForm #contact").val(person.contact);
      if (family == null) {
        $(".familyDropP .selectpicker").selectpicker("val", "");
      } else {
        $(".familyDropP .selectpicker").selectpicker(
          "val",
          "" + family.id + ""
        );
      }
    });
    $(".detailsPersonForm #detailsPersonModal").modal("show");
  });

  // ------------------------ HIDE MODALS ---------------------------- //
  $(".close").on("click", function () {
    $(".myForm #editStudentModal").modal("hide");
    $(".myFormTwo #de-activateStudentModal").modal("hide");
    $(".myFormThree #addStudentModal").modal("hide");
    $(".myFormFour #detailsStudentModal").modal("hide");

    $(".AddTeacherForm #addTeacherModal").modal("hide");
    $(".editTeacherForm #editTeacherModal").modal("hide");
    $(".teacherDetailsForm #detailsTeacherModal").modal("hide");
    $(".deactivateTeacherForm #de-activateTeacherModal").modal("hide");

    $(".addJogathonForm #addJogathonModal").modal("hide");
    $(".editJogathonForm #editJogathonModal").modal("hide");
    $(".deactivateJogathonForm #de-activateJogathonModal").modal("hide");

    $(".addSponsorForm #addSponsorModal").modal("hide");
    $(".editSponsorForm #editSponsorModal").modal("hide");
    $(".sponsorDetailsForm #detailsSponsorModal").modal("hide");
    $(".deactivateSponsorForm #de-activateSponsorModal").modal("hide");

    $(".addFamilyForm #addFamilyModal").modal("hide");
    $(".editFamilyForm #editFamilyModal").modal("hide");
    $(".familyDetailsForm #detailsFamilyModal").modal("hide");
    $(".deactivateFamilyForm #de-activateFamilyModal").modal("hide");

    $(".personAssDetailsForm #deets").empty();
    $(".personAssDetailsForm #detailsPersonAssModal").modal("hide");

    $(".addPledgeTypeForm #addPledgeTypeModal").modal("hide");
    $(".editPledgeTypeForm #editPledgeTypeModal").modal("hide");
    $(".pledgeTypeDetailsForm #detailsPledgeTypeModal").modal("hide");
    $(".deactivatePledgeTypeForm #de-activatePledgeTypeModal").modal("hide");

    $(".addClassroomForm #addClassroomModal").modal("hide");
    $(".editClassroomForm #editClassroomModal").modal("hide");
    $(".classroomDetailsForm #detailsClassroomModal").modal("hide");
    $(".deactivateClassroomForm #de-activateClassroomModal").modal("hide");
    $(".studentAssDetailsForm #deetsStudents").empty();
    $(".studentAssDetailsForm #detailsStudentAssModal").modal("hide");

    $(".addPledgeForm #addPledgeModal").modal("hide");
    $(".editPledgeForm #editPledgeModal").modal("hide");
    $(".pledgeDetailsForm #detailsPledgeModal").modal("hide");
    $(".deactivatePledgeForm #de-activatePledgeModal").modal("hide");

    $(".editLapsForm #editLapsModal").modal("hide");

    $(".registerForm #registerModal").modal("hide");
    $(".loginForm #loginModal").modal("hide");

    $(".addPersonForm #addPersonModal").modal("hide");
    $(".editPersonForm #editPersonModal").modal("hide");
    $(".detailsPersonForm #detailsPersonModal").modal("hide");
    $(".deactivatePersonForm #de-activatePersonModal").modal("hide");

    $('.gradeStatus .selectpicker').empty();
  });

  // ------------------------TOOLTIP ---------------------------- //
  $('[data-toggle="tooltip"]').tooltip();

  // ------------------------ SELECT DESELCT BOXES ---------------------------- //
  var checkbox = $('table tbody input[type="checkbox"]');
  $("#selectAll").click(function () {
    if (this.checked) {
      checkbox.each(function () {
        this.checked = true;
      });
    } else {
      checkbox.each(function () {
        this.checked = false;
      });
    }
  });
  checkbox.click(function () {
    if (!this.checked) {
      $("#selectAll").prop("checked", false);
    }
  });

  // ------------------------- SEARCH ----------------------------------- //

  $("#myInput").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
    });
  });

  document.getElementById("year").innerHTML = 'Copyright © ' + new Date().getFullYear();

  // ------------------------- REPORTS ----------------------------------- //

});

$('#jogathon').append($('<option>', {
  text: 'Select an option...'
}))

$('#week').append($('<option>', {
  text: 'Select an option...'
}))

$('#week').append($('<option>', {
  value: 0,
  text: 'All Weeks'
}));

function getWeeks() {
  var id = $("#jogathon").find(":selected").val();
  var href = 'getWeeks/?id=' + id;
  $.get(href, function(weeks, status) {
    $('#week').append($('<option>', {
      text: 'Select an option...'
    })).prop('selected', true);
    $("#week").empty()
    $.each(weeks, function(i, week) {
      $('#week').append($('<option>', {
        value: week,
        text: "Week " + week
      }));
    });
  })
}

function getPledges() {
  $("#myTableReport").empty();
  var id = $("#week").find(":selected").val();
    var href = 'getPledges/?id=' + id;
    $.get(href, function(reports, status) {
      $.each(reports, function(i, report) {
        var $row = $('<tr>'+
        '<td>'+report.student+'</td>'+
        '<td>'+report.total+'</td>'+
        '</tr>');
        $('table> tbody').append($row);
    });
  })
}