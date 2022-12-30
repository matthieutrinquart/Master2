import 'Question.dart';

class QuestionRepository {
  List<Question> questions = [];


  QuestionRepository(){

  }

  List<Question> getFacileQuestion(){
    List<Question> questions = [];

    questions.add(new Question(questionText: "1 Litre de Coca-Cola contient 21 morceaux de sucre. Vrai ou faux ?", isCorrect: true,image: "images/questionnaire/coca.jpg"));
    questions.add(new Question(questionText: "Tous les tanks britanniques sont équipés pour boire le thé. Vrai ou faux ?", isCorrect: true,image: "images/questionnaire/british.png"));
    questions.add(new Question(questionText: "Une seule grenouille peut pondre jusqu’à 2000 œufs. Vrai ou faux ?", isCorrect: false,image: "images/questionnaire/grenouille.jpg"));
    questions.add(new Question(questionText: "Pour produire un pot de miel, les abeilles ont besoin d’environ 2 millions de fleurs. Vrai ou faux ?", isCorrect: true,image: "images/questionnaire/abeille.jpg"));
    questions.add(new Question(questionText: "La musique de Britney Spears a été utilisée pour faire fuir des pirates somaliens. Vrai ou faux ?", isCorrect: true,image: "images/questionnaire/britney.jfif"));
    questions.add(new Question(questionText: "Les escargots entraînent le décès d'environ 100.000 personnes chaque année. Vrai ou faux ?" , isCorrect: true,image: "images/questionnaire/escargot.jfif"));
    questions.add(new Question(questionText: "Les avocats sont toxiques pour les chats. Vrai ou faux ?", isCorrect: true,image: "images/questionnaire/avocat.jfif"));
    questions.add(new Question(questionText: "Avant 2011 en Russie, la bière n’était pas considérée comme un alcool, mais comme une boisson gazeuse. Vrai ou faux ?", isCorrect: false,image: "images/questionnaire/Russie.jpg"));
    questions.add(new Question(questionText: "La langue officielle des États-Unis d’Amérique est l’anglais. Vrai ou faux ?", isCorrect: false,image: "images/questionnaire/États-Unis.jpg"));

    return questions;
  }


  List<Question> getMoyenQuestion(){
    List<Question> questions = [];

    questions.add(new Question(questionText: "Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.", isCorrect: false,image: "images/questionnaire/Q1dif.jpeg"));
    questions.add(new Question(questionText: "Au XVIIe siècle, les carottes n’étaient pas orange. ?", isCorrect: true,image: "images/questionnaire/carrote.jfif"));
    questions.add(new Question(questionText: "Le coeur d'une crevette est logé dans sa tête. ?", isCorrect: true,image: "images/questionnaire/crevette.jfif"));
    questions.add(new Question(questionText: "Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", isCorrect: true,image: "images/questionnaire/brad.jpg"));
    questions.add(new Question(questionText: "Les huitres peuvent changer de sexe au moment de l’accouplement.", isCorrect: true,image: "images/questionnaire/huitre.jpeg"));
    questions.add(new Question(questionText: "Le corps humain d’un adulte possède 119 os.", isCorrect: false,image: "images/questionnaire/os.jpg"));
    questions.add(new Question(questionText: "Il est impossible de rêver et ronfler en même temps.", isCorrect: true,image: "images/questionnaire/dormir.jpg"));
    questions.add(new Question(questionText: "Le chocolat est toxique pour les chiens.", isCorrect: true,image: "images/questionnaire/chien.jfif"));
    questions.add(new Question(questionText: "Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", isCorrect: false,image: "images/questionnaire/marche.jfif"));


    return questions;
  }



  List<Question> getDifficulterQuestion(){
    List<Question> questions = [];

    questions.add(new Question(questionText: "1 litre d'huile (même de cuisine) peut contaminer jusqu'à 1 000 litres d'eau.", isCorrect: false,image: "images/questionnaire/huile.jfif"));
    questions.add(new Question(questionText: "Chaque année, le blocage des pompes des stations d'eau dû aux déchets inappropriés jetés dans les eaux usées de Drummondville coûte 100 000 \$", isCorrect: true,image: "images/questionnaire/dechet.jfif"));
    questions.add(new Question(questionText: "L'huile jetée dans les toilettes ou un évier reste liquide et se disperse dans l'eau.", isCorrect: false,image: "images/questionnaire/huile.jfif"));
    questions.add(new Question(questionText: "Une surverse (flushgate) est toujours liée à un problème d'origine industrielle.", isCorrect: false,image: "images/questionnaire/usine.jfif"));
    questions.add(new Question(questionText: "Les nappes souterraines alimentent en eau seulement les agriculteurs et les particuliers qui ont un puits artésien.", isCorrect: false,image: "images/questionnaire/agriculteur.png"));
    questions.add(new Question(questionText: "On améliore la récupération des eaux de pluie en raccordant directement ses gouttières aux égouts de la ville.", isCorrect: false,image: "images/questionnaire/eau.jfif"));
    questions.add(new Question(questionText: "Les engrais ne sont pas un danger pour les lacs et les rivières.", isCorrect: false,image: "images/questionnaire/engrais.jfif"));


    return questions;
  }
}