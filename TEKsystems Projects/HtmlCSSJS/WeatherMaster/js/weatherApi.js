$(document).ready(function () {
  //--------------- Weather API Setup ----------------//
  var button = document.querySelector(".button");
  var address = document.querySelector(".address");
  var conditions = document.querySelector(".conditions");
  var temp = document.querySelector(".temp");

  button.addEventListener("click", function () {
    var inputValue = document.querySelector(".inputValue").value;
    if (inputValue == null || inputValue == "") {
      alert("Please enter a proper search query!");
    } else {
      const settings = {
        async: true,
        crossDomain: true,
        url: "https://aerisweather1.p.rapidapi.com/forecasts/" + inputValue,
        method: "GET",
        headers: {
          "X-RapidAPI-Host": "aerisweather1.p.rapidapi.com",
          "X-RapidAPI-Key":
            "redacted",
        },
      };

      $("#tableMain").show();
      $.ajax(settings).done(function (response) {
        //console.log(response);
        let data = response.response;
        let location = inputValue;
        let condish = data[0].periods[0].weather;
        let presTemp = data[0].periods[0].maxFeelslikeF;

        address.innerHTML = location;
        conditions.innerHTML = condish;
        temp.innerHTML = presTemp + "°F";

        console.log(data);
      });
    }
  });

  $("#tableMain").hide();
});
