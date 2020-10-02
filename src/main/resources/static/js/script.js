function validateInputField(inputField, regExp) {
    var value = $(inputField).val();
    // var firstNameRegExp = new RegExp('^[A-Za-z]{2,20}$');
    var isValid = regExp.test(value);
    var validationContainer = $(inputField).closest(".validation-container");
    var messageContainer = $(validationContainer).find(".message-container.message-box");

    if (isValid) {
        $(validationContainer).removeClass("invalid");
        $(messageContainer).hide();
    } else {
        $(validationContainer).addClass("invalid");
        $(messageContainer).text("Input is invalid!").show();
    }
}


//input valid: 20-3-2002 invalid input: 33-13-2005, 29-02-2003
function validateDate() {

    var day = $(".date-of-birth #day").val();
    var month = $(".date-of-birth #month").val();
    var year = $(".date-of-birth #year").val();

    var OK;

    if (OK = (year > 1900) && (year < new Date().getFullYear())) {
        if (OK = (month <= 12 && month > 0)) {

            var LeapYear = (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0));

            if (OK = day > 0) {

                if (month == 2) {

                    OK = LeapYear ? day <= 29 : day <= 28;
                } else {
                    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                        OK = day <= 30;
                    } else {
                        OK = day <= 31;
                    }
                }
            }
        }
    }

    var validationContainer = $("li.date-of-birth .validation-container");
    var messageContainer = $(validationContainer).find(".message-container.message-box");

    if (OK) {
        $(validationContainer).removeClass("invalid");
        $(messageContainer).hide();
    } else {
        $(validationContainer).addClass("invalid");
        $(messageContainer).text("Input is invalid!").show();
    }


}

function validateGender() {

    var isValid = false;

    $("li.gender input").each(function () {

        if ($(this).is(":checked")) {
            isValid = true;
        }


    });

    var validationContainer = $("li.gender .validation-container");
    var messageContainer = $(validationContainer).find(".message-container.message-box");

    if (isValid) {

        console.log("e valid");

        $(validationContainer).removeClass("invalid");
        $(messageContainer).hide();
    } else {

        console.log("e invalid");


        $(validationContainer).addClass("invalid");
        $(messageContainer).text("Input is invalid!").show();
    }

}

function validateForm() {

    // alert("am validat tot");

    $("#firstName").focusout();
    $("#lastName").focusout();
    $("#email").focusout();

    validateDate();

    validateGender();

    validationWasTriggerd = true;


}

function errorsDisplayed() {

    return $(".invalid").length > 0;
}

var validationWasTriggerd = false;

function populateFirstName() {
    var firstName = $("#firstName").val();
    $("h3.js-inject span.firstName").text(firstName);
}


function stickyNavBar() {

  var navbar = document.getElementById("navigation-355596");
  var sticky = navbar.offsetTop;

  if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky-navbar")
  } else {
    navbar.classList.remove("sticky-navbar");
  }
}

$(document).ready(function () {

    window.onscroll = function() {stickyNavBar()};

    $("#firstName").focusout(function () {
        validateInputField(this, new RegExp('^[A-Za-z]{2,20}$'));
    });
    $("#lastName").focusout(function () {
        validateInputField(this, new RegExp('^[A-Za-z]{2,20}$'));
    });
    $("#email").focusout(function () {
        validateInputField(this, new RegExp('^(?!(.*\\.\\.).*)(?=.{6,255}$)[^@\\s]+@[^@\\s,]+\\.[a-zA-Z0-9\\u007f-\\uffff-]{2,}$'));
    });
    $("li.date-of-birth select").focusout(function () {
        validateDate();
    });


    $("#street").focusout(function () {
            validateInputField(this, new RegExp('^[A-Za-z 0-9\\.,]{2,50}$'));
    });

    $("#postalCode").focusout(function () {
            validateInputField(this, new RegExp('^[0-9]{6,6}$'));
    });

    $("#city").focusout(function () {
            validateInputField(this, new RegExp('^[A-Za-z ]{2,20}$'));
    });

    $("#mobileNumber").focusout(function () {
                validateInputField(this, new RegExp('^[0-9]{9,9}$'));
    });

    $("#viewPassword").click(function (e) {


        if ($("#viewPassword").text() == "Show"){
            $("#password").attr("type","text");
            $("#viewPassword").text("Hide");

        }else {
            $("#password").attr("type","password");
            $("#viewPassword").text("Show");
        }
    });

    $(".submitRegistration").click(function (e) {
        e.preventDefault();

        if (!validationWasTriggerd || validationWasTriggerd && errorsDisplayed()) {
            validateForm();
        }

        if (!errorsDisplayed()) {

            if ($(".form-view-1").is(":visible")) {

                $(".form-view-1").hide(500);
                $(".form-view-2").show(500);
                populateFirstName();

            } else if ($(".form-view-2").is(":visible")) {

                $(".form-view-2").hide(500);
                $(".form-view-3").show(500);
                /* populateFirstName();*/

            }


        }


    });

});




