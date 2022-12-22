import 'Question.dart';

class QuestionRepository {
  List<Question> questions = [];


  QuestionRepository(){
    questions.add(new Question("Matthieu est-il un génie ?", true));
    questions.add(new Question("Martin est-il un génie ?", true));
    questions.add(new Question("Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.",false));
    questions.add(new Question("Au XVIIe siècle, les carottes n’étaient pas orange. ?",true));
    questions.add(new Question("Le coeur d'une crevette est logé dans sa tête. ?", true));
    questions.add(new Question("Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", true));
    questions.add(new Question("Les huitres peuvent changer de sexe au moment de l’accouplement.", true));
    questions.add(new Question("Le corps humain d’un adulte possède 119 os.",  false));
    questions.add(new Question("Il est impossible de rêver et ronfler en même temps.", true));
    questions.add(new Question("Le chocolat est toxique pour les chiens.",  true));
    questions.add(new Question("Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", false));
    
  }

  List<Question> getFacileQuestion(){
    List<Question> questions = [];
    questions.add(new Question("Ceci est une question facile ?", true));
    questions.add(new Question("Matthieu est-il un génie ?", true));
    questions.add(new Question("Martin est-il un génie ?", true));
    questions.add(new Question("Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.",false));
    questions.add(new Question("Au XVIIe siècle, les carottes n’étaient pas orange. ?",true));
    questions.add(new Question("Le coeur d'une crevette est logé dans sa tête. ?", true));
    questions.add(new Question("Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", true));
    questions.add(new Question("Les huitres peuvent changer de sexe au moment de l’accouplement.", true));
    questions.add(new Question("Le corps humain d’un adulte possède 119 os.",  false));
    questions.add(new Question("Il est impossible de rêver et ronfler en même temps.", true));
    questions.add(new Question("Le chocolat est toxique pour les chiens.",  true));
    questions.add(new Question("Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", false));


    return questions;
  }


  List<Question> getMoyenQuestion(){
    List<Question> questions = [];
    questions.add(new Question("Ceci est une question moyen ?", true));
    questions.add(new Question("Matthieu est-il un génie ?", true));
    questions.add(new Question("Martin est-il un génie ?", true));
    questions.add(new Question("Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.",false));
    questions.add(new Question("Au XVIIe siècle, les carottes n’étaient pas orange. ?",true));
    questions.add(new Question("Le coeur d'une crevette est logé dans sa tête. ?", true));
    questions.add(new Question("Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", true));
    questions.add(new Question("Les huitres peuvent changer de sexe au moment de l’accouplement.", true));
    questions.add(new Question("Le corps humain d’un adulte possède 119 os.",  false));
    questions.add(new Question("Il est impossible de rêver et ronfler en même temps.", true));
    questions.add(new Question("Le chocolat est toxique pour les chiens.",  true));
    questions.add(new Question("Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", false));


    return questions;
  }



  List<Question> getDifficulterQuestion(){
    List<Question> questions = [];
    questions.add(new Question("Ceci est une question difficile ?", true));
    questions.add(new Question("Matthieu est-il un génie ?", true));
    questions.add(new Question("Martin est-il un génie ?", true));
    questions.add(new Question("Un colibri d'Elena pèse aussi lourd qu’une pièce de 20 centimes d’euros.",false));
    questions.add(new Question("Au XVIIe siècle, les carottes n’étaient pas orange. ?",true));
    questions.add(new Question("Le coeur d'une crevette est logé dans sa tête. ?", true));
    questions.add(new Question("Brad Pitt a été interdit de territoire en Chine pendant 17 ans suite au film \"Sept ans au Tibet\" ?", true));
    questions.add(new Question("Les huitres peuvent changer de sexe au moment de l’accouplement.", true));
    questions.add(new Question("Le corps humain d’un adulte possède 119 os.",  false));
    questions.add(new Question("Il est impossible de rêver et ronfler en même temps.", true));
    questions.add(new Question("Le chocolat est toxique pour les chiens.",  true));
    questions.add(new Question("Un être humain marche en moyenne l’équivalent d’un tour de la Terre tout au long de sa vie.", false));


    return questions;
  }
}