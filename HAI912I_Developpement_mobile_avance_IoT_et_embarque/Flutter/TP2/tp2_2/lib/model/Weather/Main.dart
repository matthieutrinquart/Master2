class Main {
  double? temp;
  double? feelsLike;
  double? tempMin;
  double? tempMax;
  int? pressure;
  int? seaLevel;
  int? grndLevel;
  int? humidity;
  double? tempKf;

  Main(
      {this.temp,
        this.feelsLike,
        this.tempMin,
        this.tempMax,
        this.pressure,
        this.seaLevel,
        this.grndLevel,
        this.humidity,
        this.tempKf});

  Main.fromJson(Map<String, dynamic> json) {
    if(json['temp'] is int){
      int val = json['temp'] as int;
      temp = val.toDouble();
    }else{
      temp =  json['temp'];
    }
    if(json['feels_like'] is int){
      int val = json['feels_like'] as int;
      feelsLike = val.toDouble();
    }else{
      feelsLike =  json['feels_like'];
    }
    if(json['temp_min'] is int){
      int val = json['temp_min'] as int;
      tempMin = val.toDouble();
    }else{
      tempMin =  json['temp_min'];
    }
    if(json['temp_max'] is int){
      int val = json['temp_max'] as int;
      tempMax = val.toDouble();
    }else{
      tempMax =  json['temp_max'];
    }
    pressure = json['pressure'];
    seaLevel = json['sea_level'];
    grndLevel = json['grnd_level'];
    humidity = json['humidity'];
    if(json['temp_kf'] is int){
      int val = json['temp_kf'] as int;
      tempKf = val.toDouble();
    }else{
      tempKf =  json['temp_kf'];
    }
    print("ICI2");
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['temp'] = this.temp;
    data['feels_like'] = this.feelsLike;
    data['temp_min'] = this.tempMin;
    data['temp_max'] = this.tempMax;
    data['pressure'] = this.pressure;
    data['sea_level'] = this.seaLevel;
    data['grnd_level'] = this.grndLevel;
    data['humidity'] = this.humidity;
    data['temp_kf'] = this.tempKf;
    return data;
  }
}
