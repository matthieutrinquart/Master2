part of 'weather_bloc.dart';



@immutable
abstract class WeatherEvent {}
class LoadWeather extends WeatherEvent{
   String city = "";
   double lat = 0;
   double long = 0;

  LoadWeather(this.city);
  LoadWeather.coord(this.lat,this.long);
}