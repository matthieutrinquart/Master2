class Wind {
  double? speed;
  int? deg;
  double? gust;

  Wind({this.speed, this.deg, this.gust});

  Wind.fromJson(Map<String, dynamic> json) {
    if(json['speed'] is int){
      int val = json['speed'] as int;
      speed = val.toDouble();
    }else{
      speed =  json['speed'];
    }
    deg = json['deg'];
    if(json['temp_min'] is int){
      int val = json['temp_min'] as int;
      gust = val.toDouble();
    }else{
      gust =  json['temp_min'];
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['speed'] = this.speed;
    data['deg'] = this.deg;
    data['gust'] = this.gust;
    return data;
  }
}