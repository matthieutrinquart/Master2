part of 'weather_bloc.dart';


@immutable
abstract class WeatherState extends Equatable{
const WeatherState();
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class WeatherInitial extends WeatherState {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class WeatherLoaded extends WeatherState{
  final WeatherForecastModel weather;

  const WeatherLoaded({required this.weather});


  @override
  // TODO: implement props
  List<Object> get props =>[weather];
}
