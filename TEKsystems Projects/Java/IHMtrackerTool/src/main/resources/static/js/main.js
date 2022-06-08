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

  // ------------------------ EDIT STUDENT MODAL ---------------------------- //
  $(".table .eBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var student = objects[0];
      var grade = objects[1];
      var family = objects[2];
      $(".myForm #id").val(student.id);
      $(".myForm #firstName").val(student.firstName);
      $(".myForm #lastName").val(student.lastName);
      $(".myForm #contact").val(student.contact);
      $(".myForm #active").val(student.active);
      $("#grade option[value='" + grade.id + "']").prop("selected", true);
      if (family == null) {
        $("#family option[value='0']").prop("selected", true);
      } else {
        $("#family option[value='" + family.id + "']").prop("selected", true);
      }
        
    });
    $(".myForm #editStudentModal").modal("show");
  });

  // ------------------------ EDIT TEACHER MODAL ---------------------------- //
  $(".table .etBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var student = objects[0];
      var family = objects[1];
      $(".editTeacherForm #id").val(student.id);
      $(".editTeacherForm #firstName").val(student.firstName);
      $(".editTeacherForm #lastName").val(student.lastName);
      $(".editTeacherForm #contact").val(student.contact);
      $(".editTeacherForm #active").val(student.active);
      if (family == null) {
        $("#family option[value='0']").prop("selected", true);
      } else {
        $("#family option[value='" + family.id + "']").prop("selected", true);
      }
        
    });
    $(".editTeacherForm #editTeacherModal").modal("show");
  });

  // ------------------------ EDIT JOGATHON MODAL ---------------------------- //
  $(".table .ejBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (jogathon, status) {
      $(".editJogathonForm #id").val(jogathon.id);
      $(".editJogathonForm #runDate").val(jogathon.runDate);
      $(".editJogathonForm #comments").val(jogathon.comments);
      $(".editJogathonForm #active").val(jogathon.active);
        
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
      $(".editSponsorForm #state").val(sponsor.stateOf);
      $(".editSponsorForm #zip").val(sponsor.zip);
      $(".editSponsorForm #active").val(sponsor.active);
        
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
      $(".editFamilyForm #id").val(fam.id);
      $(".editFamilyForm #familyName").val(fam.familyName);
      $(".editFamilyForm #phone").val(fam.phone);
      $(".editFamilyForm #addressOne").val(fam.addressOne);
      $(".editFamilyForm #addressTwo").val(fam.addressTwo);
      $(".editFamilyForm #city").val(fam.city);
      $(".editFamilyForm #stateOf").val(fam.stateOf);
      $(".editFamilyForm #zip").val(fam.zip);
      $(".editFamilyForm #active").val(fam.active);
      persons.forEach(element => {
        $("#person option[value='" + element.id + "']").prop("selected", true);
      });
        
    });
    $(".editFamilyForm #editFamilyModal").modal("show");
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

  // ------------------------ DETAIL STUDENT MODAL ---------------------------- //
  $(".table .dBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (objects, status) {
      var student = objects[0];
      var grade = objects[1];
      var classroom = objects[2];
      var family = objects[3];
      $(".myFormFour #id").val(student.id);
      $(".myFormFour #firstName").val(student.firstName);
      $(".myFormFour #lastName").val(student.lastName);
      $(".myFormFour #contact").val(student.contact);
      $(".myFormFour #classroom").val(classroom.className);
      $(".myFormFour #grade").val(grade.gradeName);
      if (family == null) {
        $(".myFormFour #family").val("None");
      } else {
        $(".myFormFour #family").val(family.familyName);
      }
      $(".myFormFour #active").val(student.active);
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
      $(".teacherDetailsForm #active").val(student.active);
    });
    $(".teacherDetailsForm #detailsTeacherModal").modal("show");
  });

  // ---------------------------- DETAIL JOGATHON MODAL -------------------------//
  $(".table .jdBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (jogathon, status) {
      $(".jogathonDetailsForm #id").val(jogathon.id);
      $(".jogathonDetailsForm #runDate").val(jogathon.runDate);
      $(".jogathonDetailsForm #comments").val(jogathon.comments);
      $(".jogathonDetailsForm #active").val(jogathon.active);
    });
    $(".jogathonDetailsForm #detailsJogathonModal").modal("show");
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
      $(".sponsorDetailsForm #state").val(sposnor.stateOf);
      $(".sponsorDetailsForm #zip").val(sposnor.zip);
      $(".sponsorDetailsForm #active").val(sposnor.active);
        
    });
    $(".sponsorDetailsForm #detailsSponsorModal").modal("show");
  });

  // ------------------------ EDIT FAMILY MODAL ---------------------------- //
  $(".table .fdBtn").on("click", function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    $.get(href, function (listOfLists, status) {
      var family = listOfLists[0];
      var persons = listOfLists[1];
      var fam = family[0];
      $(".familyDetailsForm #id").val(fam.id);
      $(".familyDetailsForm #familyName").val(fam.familyName);
      $(".familyDetailsForm #phone").val(fam.phone);
      $(".familyDetailsForm #addressOne").val(fam.addressOne);
      $(".familyDetailsForm #addressTwo").val(fam.addressTwo);
      $(".familyDetailsForm #city").val(fam.city);
      $(".familyDetailsForm #stateOf").val(fam.stateOf);
      $(".familyDetailsForm #zip").val(fam.zip);
      $(".familyDetailsForm #active").val(fam.active);
      persons.forEach(element => {
        $("#person option[value='" + element.id + "']").prop("selected", true);
      });
        
    });
    $(".familyDetailsForm #detailsFamilyModal").modal("show");
  });

  // ------------------------ HIDE STUDENT MODALS ---------------------------- //
  $(".myForm .close").on("click", function (event) {
    $(".myForm #editStudentModal").modal("hide");
  });
  $(".myFormTwo .close").on("click", function (event) {
    $(".myFormTwo #de-activateStudentModal").modal("hide");
  });
  $(".myFormThree .close").on("click", function (event) {
    $(".myFormThree #addStudentModal").modal("hide");
  });
  $(".myFormFour .close").on("click", function (event) {
    $(".myFormFour #detailsStudentModal").modal("hide");
  });

  // ------------------------- HIDE TEACHER MODALS --------------------- //
  $(".AddTeacherForm .close").on("click", function (event) {
    $(".AddTeacherForm #addTeacherModal").modal("hide");
  });
  $(".editTeacherForm .close").on("click", function (event) {
    $(".editTeacherForm #editTeacherModal").modal("hide");
  });
  $(".teacherDetailsForm .close").on("click", function (event) {
    $(".teacherDetailsForm #detailsTeacherModal").modal("hide");
  });
  $(".deactivateTeacherForm .close").on("click", function (event) {
    $(".deactivateTeacherForm #de-activateTeacherModal").modal("hide");
  });

  // ------------------------- HIDE JOGATHON MODALS --------------------- //
  $(".addJogathonForm .close").on("click", function (event) {
    $(".addJogathonForm #addJogathonModal").modal("hide");
  });
  $(".editJogathonForm .close").on("click", function (event) {
    $(".editJogathonForm #editJogathonModal").modal("hide");
  });
  $(".jogathonDetailsForm .close").on("click", function (event) {
    $(".jogathonDetailsForm #detailsJogathonModal").modal("hide");
  });
  $(".deactivateJogathonForm .close").on("click", function (event) {
    $(".deactivateJogathonForm #de-activateJogathonModal").modal("hide");
  });

  // ------------------------- HIDE SPOSNOR MODALS --------------------- //
  $(".addSponsorForm .close").on("click", function (event) {
    $(".addSponsorForm #addSponsorModal").modal("hide");
  });
  $(".editSponsorForm .close").on("click", function (event) {
    $(".editSponsorForm #editSponsorModal").modal("hide");
  });
  $(".sponsorDetailsForm .close").on("click", function (event) {
    $(".sponsorDetailsForm #detailsSponsorModal").modal("hide");
  });
  $(".deactivateSponsorForm .close").on("click", function (event) {
    $(".deactivateSponsorForm #de-activateSponsorModal").modal("hide");
  });

  // ------------------------- HIDE SPOSNOR MODALS --------------------- //
  $(".addFamilyForm .close").on("click", function (event) {
    $(".addFamilyForm #addFamilyModal").modal("hide");
  });
  $(".editFamilyForm .close").on("click", function (event) {
    $(".editFamilyForm #editFamilyModal").modal("hide");
  });
  $(".familyDetailsForm .close").on("click", function (event) {
    $(".familyDetailsForm #detailsFamilyModal").modal("hide");
  });
  $(".deactivateFamilyForm .close").on("click", function (event) {
    $(".deactivateFamilyForm #de-activateFamilyModal").modal("hide");
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

  // ------------------------- SEARCH TESTING ----------------------------------- //

  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });

});
