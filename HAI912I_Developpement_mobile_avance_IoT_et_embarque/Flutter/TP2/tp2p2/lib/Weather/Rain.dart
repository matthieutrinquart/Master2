class Rain {
  double? d3h;

  Rain({this.d3h});

  Rain.fromJson(Map<String, dynamic> json) {
    if(json['3h'] is int){
      int val = json['3h'] as int;
      d3h = val.toDouble();
    }else{
      d3h =  json['3h'];
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['3h'] = this.d3h;
    return data;
  }
}