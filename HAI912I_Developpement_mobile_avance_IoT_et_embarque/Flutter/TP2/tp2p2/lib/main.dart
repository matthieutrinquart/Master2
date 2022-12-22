import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:tp2p2/cubit/WeatherForecastModel.dart';
import 'package:tp2p2/cubit/weather_bloc.dart';
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: MultiBlocProvider(
        providers: [
          BlocProvider(
            create: (BuildContext context) => WeatherBloc(),
          )
        ],
        child: MyHomePage(title: 'Weather'),
      )
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final villerecherche = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          // Here we take the value from the MyHomePage object that was created by
          // the App.build method, and use it to set our appbar title.
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              _getTextField(),
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
          ),
        )
    );
  }

  BlocBuilder<WeatherBloc, WeatherState> _getWeather() {
    return BlocBuilder<WeatherBloc, WeatherState>(builder: (context, state) {
      if (state is WeatherInitial) {
        return const CircularProgressIndicator();
      } else if (state is WeatherLoaded) {
        return WeatherPage(state);
      } else {
        return new Text('marche pas');
      }
    }
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
              new Text(StringDate(state.weather.list
                  ?.elementAt(0)
                  ?.dtTxt) ?? '', textAlign: TextAlign.center),
                    getWeatherIcon(weatherDescription: state.weather.list?.elementAt(0).weather?.elementAt(0).main ?? ''),
              gettemperature(state)


            ]
        )
    );
  }
  Widget getWeatherIcon({required String weatherDescription}){
    switch(weatherDescription){
      case "Clear":
        return  Icon(FontAwesomeIcons.sun, size: 200 ,  color: Colors.pink );

      case "Clouds":
        return  Icon(FontAwesomeIcons.cloud, size: 200 ,  color: Colors.pink );


      case "Rain":
        return  Icon(FontAwesomeIcons.cloudRain, size: 200,  color: Colors.pink );


      case "Snow":
        return  Icon(FontAwesomeIcons.snowman, size: 200,  color: Colors.pink );


      default:
        return  Icon(FontAwesomeIcons.sun, size: 200,  color: Colors.pink );

    }
  }

  Widget gettemperature(WeatherLoaded state){
    print(state.weather.list?.elementAt(0).main?.temp);
    double cel = state.weather.list?.elementAt(0).main?.temp ?? 0 ;
    cel = cel - 273.15;
    String temp = cel.toStringAsFixed(1);
    print(temp);
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


}
