


import 'City.dart';
import 'Liste.dart';

class WeatherForecastModel {
  String? cod;
  int? message;
  int? cnt;
  List<Liste>? list;
  City? city;

  WeatherForecastModel(
      {this.cod, this.message, this.cnt, this.list, this.city});

  WeatherForecastModel.fromJson(Map<String, dynamic> json) {
    cod = json['cod'];
    message = json['message'];
    cnt = json['cnt'];
    if (json['list'] != null) {
      list = <Liste>[];
      json['list'].forEach((v) {
        list!.add(new Liste.fromJson(v));
      });
    }
    city = json['city'] != null ? new City.fromJson(json['city']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['cod'] = this.cod;
    data['message'] = this.message;
    data['cnt'] = this.cnt;
    if (this.list != null) {
      data['list'] = this.list!.map((v) => v.toJson()).toList();
    }
    if (this.city != null) {
      data['city'] = this.city!.toJson();
    }
    return data;
  }
}