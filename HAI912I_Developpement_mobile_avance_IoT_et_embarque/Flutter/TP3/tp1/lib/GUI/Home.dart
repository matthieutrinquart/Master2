import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../business_logic/Bloc/categories/categories_bloc.dart';
import '../data/Model/Categorie.dart';
class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);


  final String title = 'QUIZZ';

  @override
  State<Home> createState() => _Home();
}

class _Home extends State<Home> {
  @override
  Widget build(BuildContext context) {
    context.read<CategoriesBloc>().add(LoadCategories());
    return Scaffold(
      backgroundColor:const Color(0xff0607d8b),
      appBar: AppBar(
        backgroundColor:const Color(0xff0607d8b),
        automaticallyImplyLeading: false,
        title: Center(
          child: Text(widget.title),
        ),
      ),
      body: Container(
        alignment: Alignment.topCenter,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            _getImage(),
            _getboutoncreatecategories(),
            _getCategories()

          ],
        ),
      ),
    );
  }
  Container _getImage() {
    return Container(
      alignment: Alignment.center,
      child: Container(
        width: 100.00,
        height: 100.00,
        child: Icon(
            Icons.quiz,
            color: Colors.black,
            size: 100,
          ),
      ),
    );

  }
  Card _getboutoncreatecategories() {
    return Card(
        color:Color(0xf0607d8b),
        child: Container(
          width: 250.00,
          height: 50.00,
          child: Container(
            width: 150.00,
            height: 50.00,
            child:TextButton(
              style: ButtonStyle(
                foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
              ),
              onPressed: () {
                setState(() {
                  Navigator.pushNamed(context,
                    '/CreateQuizz',
                  );
                });
              },
              child: Text("Créer une catégories",
                  textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
            ),
          ),
        )
    );

  }
  BlocBuilder<CategoriesBloc, CategoriesState> _getCategories() {
    return BlocBuilder<CategoriesBloc, CategoriesState>(builder: (context, state) {
      if (state is CategoriesInitial) {
        return new CircularProgressIndicator();
      } else if (state is CategoriesLoaded) {
        return Gridviewcategorie(state);
      } else {
        return new Text('marche pas');
      }
    }
    );
  }
  Container CategoriesPage(CategoriesLoaded state) {
    return Container(
      alignment: Alignment.center,
      child:Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Gridviewcategorie(state)
    ]
    )
    );


  }
  Container Gridviewcategorie(CategoriesLoaded state){
    return Container(
      width: 400,
        height: 400,
    child: Expanded(
        child: GridView.builder(
            padding: const EdgeInsets.all(20),
            itemCount: state.categories.length,
            scrollDirection: Axis.horizontal,
            shrinkWrap: true,
            primary: false,
            gridDelegate:
            new SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2 ),
            itemBuilder: (BuildContext context, int index) {
              return getCard(state.categories[index]);
            })));
  }
  Card getCard(Categorie contenu)  {
    return Card(
        color:Color(0xf0607d8b),
        child: Container(
        width: 150.00,
        height: 50.00,
        child: Container(
          width: 150.00,
          height: 50.00,
          child:TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () {
              setState(() {
                Navigator.pushNamed(context,
                  '/Quizz',
                    arguments: contenu.id
                );
              });
            },
            onLongPress: () {
              Navigator.pushNamed(context,
                  '/EditQuizz',
                  arguments: contenu.id
              );
            },
            child: Text(contenu.name,
                textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
          ),
        ),
      )
    );

    }





}
