$(document).ready(function () {
  //--------------- Weather 5-Day Forecast API Setup ----------------//
  var button = document.querySelector(".button");

  button.addEventListener("click", function () {
    $(".forecast-table").show();
    var inputValue = document.querySelector(".inputValue").value;
    const settings = {
      async: true,
      crossDomain: true,
      url: "https://aerisweather1.p.rapidapi.com/forecasts/" + inputValue,
      method: "GET",
      headers: {
        "X-RapidAPI-Host": "aerisweather1.p.rapidapi.com",
        "X-RapidAPI-Key": "redacted",
      },
    };

    $.ajax(settings).done(function (response) {
      let data = response.response;
      let location = inputValue;

      let days = data[0].periods;

      console.log(days[2]);

      for (let i = 0; i < 5; i++) {
        document.querySelector(".address" + i).innerHTML = location;
        document.querySelector("#day" + i).innerHTML = days[i].dateTimeISO;
        document.querySelector(".conditions" + i).innerHTML = days[i].weather;
        document.querySelector(".temp" + i).innerHTML +=
          days[i].maxFeelslikeF + "°F";
        document.querySelector(".hum" + i).innerHTML +=
          days[i].precipIN + " IN";
        document.querySelector(".wind" + i).innerHTML +=
          days[i].windSpeedMPH + " MPH";
        document.querySelector(".vis" + i).innerHTML += days[i].windDir;
      }
    });
  });
  $(".forecast-table").hide();
});