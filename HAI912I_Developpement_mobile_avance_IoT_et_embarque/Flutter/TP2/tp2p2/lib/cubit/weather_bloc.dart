import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:meta/meta.dart';
import 'package:tp2p2/Network.dart';
import 'package:tp2p2/cubit/WeatherForecastModel.dart';

part 'weather_event.dart';
part 'weather_state.dart';

class WeatherBloc extends Bloc<WeatherEvent, WeatherState> {
  WeatherBloc() : super(WeatherInitial()) {
    on<LoadWeather>((event, emit) async {
      print("city  " + event.city);
      bool g = event.city == "";
      print("condition  "+ g.toString());
      if(event.city != ""){
        WeatherForecastModel weather = await Network.getWeatherForecast(
            cityname: event.city);
        emit(WeatherLoaded(weather: weather));
      }else{
        WeatherForecastModel weather = await Network.getWeatherForecastLocasisation(
            lat: event.lat, long: event.long);
        emit(WeatherLoaded(weather: weather));
      }
    }
      );
      }
}

