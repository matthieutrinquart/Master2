import 'Question.dart';

class QuestionRepository {
  List<Question> questions = [];


  QuestionRepository(){
    questions.add(new Question(questionText: "Matthieu est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Martin est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.", isCorrect: false));
    questions.add(new Question(questionText: "Au XVIIe siècle, les carottes n’étaient pas orange. ?", isCorrect: true));
    questions.add(new Question(questionText: "Le coeur d'une crevette est logé dans sa tête. ?", isCorrect: true));
    questions.add(new Question(questionText: "Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", isCorrect: true));
    questions.add(new Question(questionText: "Les huitres peuvent changer de sexe au moment de l’accouplement.", isCorrect: true));
    questions.add(new Question(questionText: "Le corps humain d’un adulte possède 119 os.", isCorrect: false));
    questions.add(new Question(questionText: "Il est impossible de rêver et ronfler en même temps.", isCorrect: true));
    questions.add(new Question(questionText: "Le chocolat est toxique pour les chiens.", isCorrect: true));
    questions.add(new Question(questionText: "Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", isCorrect: false));
    
  }

  List<Question> getFacileQuestion(){
    List<Question> questions = [];

    questions.add(new Question(questionText: "Matthieu est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Martin est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.", isCorrect: false));
    questions.add(new Question(questionText: "Au XVIIe siècle, les carottes n’étaient pas orange. ?", isCorrect: true));
    questions.add(new Question(questionText: "Le coeur d'une crevette est logé dans sa tête. ?", isCorrect: true));
    questions.add(new Question(questionText: "Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", isCorrect: true));
    questions.add(new Question(questionText: "Les huitres peuvent changer de sexe au moment de l’accouplement.", isCorrect: true));
    questions.add(new Question(questionText: "Le corps humain d’un adulte possède 119 os.", isCorrect: false));
    questions.add(new Question(questionText: "Il est impossible de rêver et ronfler en même temps.", isCorrect: true));
    questions.add(new Question(questionText: "Le chocolat est toxique pour les chiens.", isCorrect: true));
    questions.add(new Question(questionText: "Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", isCorrect: false));


    return questions;
  }


  List<Question> getMoyenQuestion(){
    List<Question> questions = [];

    questions.add(new Question(questionText: "Matthieu est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Martin est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.", isCorrect: false));
    questions.add(new Question(questionText: "Au XVIIe siècle, les carottes n’étaient pas orange. ?", isCorrect: true));
    questions.add(new Question(questionText: "Le coeur d'une crevette est logé dans sa tête. ?", isCorrect: true));
    questions.add(new Question(questionText: "Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", isCorrect: true));
    questions.add(new Question(questionText: "Les huitres peuvent changer de sexe au moment de l’accouplement.", isCorrect: true));
    questions.add(new Question(questionText: "Le corps humain d’un adulte possède 119 os.", isCorrect: false));
    questions.add(new Question(questionText: "Il est impossible de rêver et ronfler en même temps.", isCorrect: true));
    questions.add(new Question(questionText: "Le chocolat est toxique pour les chiens.", isCorrect: true));
    questions.add(new Question(questionText: "Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", isCorrect: false));


    return questions;
  }



  List<Question> getDifficulterQuestion(){
    List<Question> questions = [];

    questions.add(new Question(questionText: "Matthieu est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Martin est-il un génie ?", isCorrect: true));
    questions.add(new Question(questionText: "Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.", isCorrect: false));
    questions.add(new Question(questionText: "Au XVIIe siècle, les carottes n’étaient pas orange. ?", isCorrect: true));
    questions.add(new Question(questionText: "Le coeur d'une crevette est logé dans sa tête. ?", isCorrect: true));
    questions.add(new Question(questionText: "Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", isCorrect: true));
    questions.add(new Question(questionText: "Les huitres peuvent changer de sexe au moment de l’accouplement.", isCorrect: true));
    questions.add(new Question(questionText: "Le corps humain d’un adulte possède 119 os.", isCorrect: false));
    questions.add(new Question(questionText: "Il est impossible de rêver et ronfler en même temps.", isCorrect: true));
    questions.add(new Question(questionText: "Le chocolat est toxique pour les chiens.", isCorrect: true));
    questions.add(new Question(questionText: "Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", isCorrect: false));


    return questions;
  }
}