import 'dart:convert';

import 'package:http/http.dart';

import 'cubit/WeatherForecastModel.dart';

class Network{

  static Future<WeatherForecastModel> getWeatherForecast({required String cityname}) async{
    var finalUrl = "https://api.openweathermap.org/data/2.5/forecast?q="+cityname+"&appid=a04d4f3230f373b71e8237e6b76cfe26";

    final response = await get(Uri.parse(finalUrl));
    print("URL : ${Uri.encodeFull(finalUrl)}");
    if(response.statusCode == 200){
      print("weather data : ${response.body}");
      return WeatherForecastModel.fromJson(json.decode(response.body));

    }else{
      throw Exception("Error getting weather forecast");
    }
  }
//  String cityName = "Eyguières";
//  String cles = "a04d4f3230f373b71e8237e6b76cfe26";

//  var finalUrl = "https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid="+cles;

//  "https://api.openweathermap.org/data/2.5/forecast?q=montpellier&appid=a04d4f3230f373b71e8237e6b76cfe26"


}