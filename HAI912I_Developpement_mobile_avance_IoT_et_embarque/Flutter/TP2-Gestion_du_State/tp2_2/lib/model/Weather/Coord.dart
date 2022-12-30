class Coord {
  double? lat;
  double? lon;

  Coord({this.lat, this.lon});

  Coord.fromJson(Map<String, dynamic> json) {
    if(json['lat'] is int){
      int val = json['lat'] as int;
      lat = val.toDouble();
    }else{
      lat =  json['lat'];
    }
    if(json['lon'] is int){
      int val = json['lon'] as int;
      lon = val.toDouble();
    }else{
      lon =  json['lon'];
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['lat'] = this.lat;
    data['lon'] = this.lon;
    return data;
  }
}