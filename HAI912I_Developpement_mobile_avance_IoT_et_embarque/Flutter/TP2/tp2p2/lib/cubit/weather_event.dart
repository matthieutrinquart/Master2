part of '../cubit/weather_bloc.dart';

@immutable
abstract class WeatherEvent {}
class LoadWeather extends WeatherEvent{
  final String city;

  LoadWeather(this.city);
}