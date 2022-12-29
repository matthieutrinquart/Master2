
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:geolocator/geolocator.dart';
import '../business_logic/bloc/weather_bloc.dart';

class Home extends StatefulWidget {
  const Home({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<Home> createState() => _Home();
}

class _Home extends State<Home> {
  final villerecherche = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Align(
          alignment: Alignment.center,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              SizedBox(height: 100),
              _getTextField(),
              _getButton(),
              SizedBox(height: 20),
              _getWeather()


            ],
          ),
        )
    );
  }


  Container _getTextField() {
    return Container(
        child: TextFormField(
          controller: villerecherche,
          cursorColor: Colors.grey[800],
          decoration: InputDecoration(
            labelText: "Enter City Name",
            fillColor: Colors.white70,
            filled: true,
            suffixIcon: IconButton(
              icon: Icon(Icons.search),
              color: Colors.grey[800],
              onPressed: () {
                context.read<WeatherBloc>().add(
                    LoadWeather(villerecherche.text));
              },
            ),
            labelStyle: TextStyle(color: Colors.grey),
            focusedBorder: OutlineInputBorder(
              borderSide: const BorderSide(color: Colors.grey, width: 2.0),
            ),
            enabledBorder: OutlineInputBorder(
              borderSide: const BorderSide(color: Colors.grey, width: 2.0),
            ),
          ),
        )
    );
  }

  BlocBuilder<WeatherBloc, WeatherState> _getWeather() {
    return BlocBuilder<WeatherBloc, WeatherState>(builder: (context, state) {
      if (state is WeatherInitial) {
        return WeatherPageInit();
      } else if (state is WeatherLoaded) {
        return WeatherPage(state);
      } else {
        return new Text('marche pas');
      }
    }
    );
  }
  Container WeatherPageInit() {
    return new Container(
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              new Text( ".....,..", textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.bold)),
              new Text("....,...... .., ....", textAlign: TextAlign.center),
              Icon(FontAwesomeIcons.question,size :200,color : Colors.pink),
              SizedBox(height: 10),
              gettemperatureinit(),
              getdetailInit(),
              SizedBox(height: 20),
              new Text("5-DAY WEATHER FORECAST", textAlign: TextAlign.center),
              getForeCastInit()

            ]
        )
    );
  }

  Container WeatherPage(WeatherLoaded state) {
    String Ville = state.weather.city?.name ?? "";
    String pays = state.weather.city?.country ?? "";
    return new Container(
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              new Text(Ville + "," + pays, textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.bold)),
              new Text(StringDate(state.weather.list?.elementAt(0)?.dtTxt) ?? '', textAlign: TextAlign.center),
              getWeatherIcon(weatherDescription: state.weather.list?.elementAt(0).weather?.elementAt(0).main ?? '',size :200,color : Colors.pink),
              SizedBox(height: 10),
              gettemperature(state),
              getdetail(state),
              SizedBox(height: 20),
              new Text("5-DAY WEATHER FORECAST", textAlign: TextAlign.center),
              getForeCast(state)

            ]
        )
    );
  }
  Widget getWeatherIcon({required String weatherDescription ,required double size ,required Color color  }){
    switch(weatherDescription){
      case "Clear":
        return  Icon(FontAwesomeIcons.sun, size: size ,  color: color );

      case "Clouds":
        return  Icon(FontAwesomeIcons.cloud, size: size ,  color: color );


      case "Rain":
        return  Icon(FontAwesomeIcons.cloudRain, size: size,  color: color );


      case "Snow":
        return  Icon(FontAwesomeIcons.snowman, size: size,  color: color );


      default:
        return  Icon(FontAwesomeIcons.sun, size: size,  color: color );

    }
  }



  Widget gettemperature(WeatherLoaded state){
    double cel = state.weather.list?.elementAt(0).main?.temp ?? 0 ;
    cel = cel - 273.15;
    String temp = cel.toStringAsFixed(1);
    return new Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Text(temp + "°C", textAlign: TextAlign.center ,  style: TextStyle(fontWeight: FontWeight.bold, fontSize : 40)),
        SizedBox(width: 10),
        Text(state.weather.list?.elementAt(0).weather?.elementAt(0).description ?? '0', textAlign: TextAlign.left ,  style: TextStyle(fontSize : 12)),
      ],
    );
  }
  Widget gettemperatureinit(){
    return new Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Text("....°C", textAlign: TextAlign.center ,  style: TextStyle(fontWeight: FontWeight.bold, fontSize : 40)),
        SizedBox(width: 10),
        Text("......", textAlign: TextAlign.left ,  style: TextStyle(fontSize : 12)),
      ],
    );
  }
  Widget getdetail(WeatherLoaded state){
    String wind = state.weather.list?.elementAt(0).wind?.speed.toString() ?? '0';
    String humidity = state.weather.list?.elementAt(0).main?.humidity.toString() ?? '0';
    double cel = state.weather.list?.elementAt(0).main?.temp ?? 0 ;
    cel = cel - 273.15;
    String temp = cel.toStringAsFixed(1);
    return new Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text(wind+ "m/h ", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
              Icon(FontAwesomeIcons.wind, size: 25 ,  color: Colors.black )
            ]
        ),
        SizedBox(width: 10),
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text(humidity+ " %", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
              Icon(FontAwesomeIcons.droplet, size: 25 ,  color: Colors.black )
            ]
        ),
        SizedBox(width: 10),
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text(temp+ " °C", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
              Icon(FontAwesomeIcons.temperatureFull, size: 25 ,  color: Colors.black )
            ]
        ),
      ],
    );
  }

  Widget getdetailInit(){
    return new Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text("....m/h ", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
              Icon(FontAwesomeIcons.wind, size: 25 ,  color: Colors.black )
            ]
        ),
        SizedBox(width: 10),
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text("....%", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
              Icon(FontAwesomeIcons.droplet, size: 25 ,  color: Colors.black )
            ]
        ),
        SizedBox(width: 10),
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text("....°C", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
              Icon(FontAwesomeIcons.temperatureFull, size: 25 ,  color: Colors.black )
            ]
        ),
      ],
    );
  }


  String? day(String? date) {
    var tm = DateTime.parse(date!);
    switch (tm.weekday) {
      case 1:
        return "monday";
      case 2:
        return "tuesday";
      case 3:
        return "wednesday";
      case 4:
        return "thursday";
      case 5:
        return "friday";
      case 6:
        return "saturday";
      case 7:
        return "sunday";
    }
  }
  Container getForeCast(WeatherLoaded state){
    String day1 = day(state.weather.list?.elementAt(8)?.dtTxt)?? '' ;
    String day2 = day(state.weather.list?.elementAt(16)?.dtTxt)?? '' ;
    String day3 = day(state.weather.list?.elementAt(24)?.dtTxt)?? '' ;
    String day4 = day(state.weather.list?.elementAt(32)?.dtTxt)?? '' ;
    return Container(
        margin: EdgeInsets.symmetric(vertical: 20.0),
        height: 100.0,
        child : ListView(
          scrollDirection: Axis.horizontal,
          children: <Widget>[
            getBlocForeCast(day1, state.weather.list?.elementAt(8).main?.tempMax,state.weather.list?.elementAt(8).main?.tempMin,state.weather.list?.elementAt(8).main?.humidity,state.weather.list?.elementAt(8).wind?.speed,state.weather.list?.elementAt(8).weather?.elementAt(0).main),
            getBlocForeCast(day2, state.weather.list?.elementAt(16).main?.tempMax,state.weather.list?.elementAt(16).main?.tempMin,state.weather.list?.elementAt(16).main?.humidity,state.weather.list?.elementAt(16).wind?.speed,state.weather.list?.elementAt(16).weather?.elementAt(0).main),
            getBlocForeCast(day3, state.weather.list?.elementAt(24).main?.tempMax,state.weather.list?.elementAt(24).main?.tempMin,state.weather.list?.elementAt(24).main?.humidity,state.weather.list?.elementAt(24).wind?.speed,state.weather.list?.elementAt(24).weather?.elementAt(0).main),
            getBlocForeCast(day4, state.weather.list?.elementAt(32).main?.tempMax,state.weather.list?.elementAt(32).main?.tempMin,state.weather.list?.elementAt(32).main?.humidity,state.weather.list?.elementAt(32).wind?.speed,state.weather.list?.elementAt(32).weather?.elementAt(0).main),
          ],
        )
    );
  }
  Container getForeCastInit(){

    return Container(
        margin: EdgeInsets.symmetric(vertical: 20.0),
        height: 100.0,
        child : ListView(
          scrollDirection: Axis.horizontal,
          children: <Widget>[
            getBlocForeCastInit(),
            getBlocForeCastInit(),
            getBlocForeCastInit(),
            getBlocForeCastInit(),
          ],
        )
    );
  }
  Container getBlocForeCast(String? day, double? uppertemp, double? downtemp, int? humidity, double? wind , String? descritpion){
    double u = uppertemp ??0;
    double d = downtemp ??0;
    uppertemp = u - 273.15;
    downtemp = d- 273.15;
    return  Container(
        margin: const EdgeInsets.only(left: 10.0, right: 10.0),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10),
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: <Color>[
              Colors.pink,
              Colors.white
            ],
          ),
        ),
        width: 140.0,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(day ?? '', textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  CircleAvatar(
                    radius: 30,
                    backgroundColor: Colors.white,
                    child: getWeatherIcon(weatherDescription: descritpion ?? '' , size : 40 , color : Colors.black),
                  ),
                  SizedBox(width: 15),
                  Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text(uppertemp.toStringAsFixed(1) + " °C",style: TextStyle(fontSize : 10)),
                              SizedBox(width: 2),
                              Icon(FontAwesomeIcons.circleArrowUp, size: 10,  color: Colors.black )
                            ]
                        ),
                        SizedBox(width: 5),

                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text(downtemp.toStringAsFixed(1) + " °C",style: TextStyle(fontSize : 10)),
                              SizedBox(width: 2),
                              Icon(FontAwesomeIcons.circleArrowDown, size: 10,  color: Colors.black )
                            ]
                        ),
                        SizedBox(width: 5),
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text("Hum: ${humidity?.toStringAsFixed(1)} %",style: TextStyle(fontSize : 10)),
                            ]
                        ),
                        SizedBox(width: 5),
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text("Win : ${wind?.toStringAsFixed(1)} m/h",style: TextStyle(fontSize : 10)),
                            ]
                        )
                      ]
                  )
                ]
            )

          ],
        )
    );
  }

  Container getBlocForeCastInit(){
    return  Container(
        margin: const EdgeInsets.only(left: 10.0, right: 10.0),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10),
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: <Color>[
              Colors.pink,
              Colors.white
            ],
          ),
        ),
        width: 140.0,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text("....", textAlign: TextAlign.center ,  style: TextStyle(fontSize : 15)),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  CircleAvatar(
                    radius: 30,
                    backgroundColor: Colors.white,
                    child: Icon(FontAwesomeIcons.question , size : 40 , color : Colors.black),
                  ),
                  SizedBox(width: 15),
                  Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text("....°C",style: TextStyle(fontSize : 10)),
                              SizedBox(width: 2),
                              Icon(FontAwesomeIcons.circleArrowUp, size: 10,  color: Colors.black )
                            ]
                        ),
                        SizedBox(width: 5),

                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text(".....°C",style: TextStyle(fontSize : 10)),
                              SizedBox(width: 2),
                              Icon(FontAwesomeIcons.circleArrowDown, size: 10,  color: Colors.black )
                            ]
                        ),
                        SizedBox(width: 5),
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text("Hum: ....%",style: TextStyle(fontSize : 10)),
                            ]
                        ),
                        SizedBox(width: 5),
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              Text("Win : ..... m/h",style: TextStyle(fontSize : 10)),
                            ]
                        )
                      ]
                  )
                ]
            )

          ],
        )
    );
  }
  String? StringDate(String? date) {
    var tm = DateTime.parse(date ?? '');
    String? month;
    String? day;
    switch (tm.month) {
      case 1:
        month = "january";
        break;
      case 2:
        month = "february";
        break;
      case 3:
        month = "march";
        break;
      case 4:
        month = "april";
        break;
      case 5:
        month = "may";
        break;
      case 6:
        month = "june";
        break;
      case 7:
        month = "july";
        break;
      case 8:
        month = "august";
        break;
      case 9:
        month = "september";
        break;
      case 10:
        month = "october";
        break;
      case 11:
        month = "november";
        break;
      case 12:
        month = "december";
        break;
    }
    switch (tm.weekday) {
      case 1:
        day = "monday";
        break;
      case 2:
        day = "tuesday";
        break;
      case 3:
        day = "wednesday";
        break;
      case 4:
        day = "thursday";
        break;
      case 5:
        day = "friday";
        break;
      case 6:
        day = "saturday";
        break;
      case 7:
        day = "sunday";
        break;
    }

    return day! + ", " + month! + " " + tm.day.toString() +", " + tm.year.toString();
  }
  Container _getButton() {
    return Container(
        alignment: Alignment.center,
        child:Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[

              Card(
                color:Colors.pink,
                child: Container(
                  width: 150.00,
                  height: 50.00,
                  child:TextButton(
                    style: ButtonStyle(
                      foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                    ),
                    onPressed: () {
                      setState(() async {
                        bool servicestatus = await Geolocator
                            .isLocationServiceEnabled();

                        if (servicestatus) {
                          print("GPS service is enabled");
                        } else {
                          print("GPS service is disabled.");
                        }
                        LocationPermission permission = await Geolocator
                            .checkPermission();

                        if (permission == LocationPermission.denied) {
                          permission = await Geolocator.requestPermission();
                          if (permission == LocationPermission.denied) {
                            print('Location permissions are denied');
                          } else
                          if (permission == LocationPermission.deniedForever) {
                            print("'Location permissions are permanently denied");
                          } else {
                            print("GPS Location service is granted");
                          }
                        } else {
                          print("GPS Location permission granted.");
                        }
                        Position position = await Geolocator.getCurrentPosition(
                            desiredAccuracy: LocationAccuracy.high);
                        print(position.longitude); //Output: 80.24599079
                        print(position.latitude); //Output: 29.6593457

                        context.read<WeatherBloc>().add(
                            LoadWeather.coord(
                                position.latitude, position.longitude));
                      });
                    },


                    child: Text('Localisation',
                        textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
                  ),
                ),
              )
            ]
        )
    );
  }

}