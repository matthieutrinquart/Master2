




I)

Je sais pas je fais plus tard.



II)
CREATE (louis:Personne:Homme {nom:'Claudel', prenom:'Louis', naissance:1826})
-[:MARIE_A {annee_mariage:1866}]->
(louise:Personne:Femme {nom:'Cerveaux', prenom:'Louise', naissance:1840}),
(louis) <-[:A_PARENT]- ( amille:Personne:Femme {nom:'Claudel', prenom:'Camille', naissance:1864}),
( amille) -[:A_PARENT]-> (louise), (louis) <-[:A_PARENT]-
(paul:Personne:Homme {nom:'Claudel', prenom:'Paul', naissance:1868})
RETURN louise, louis, amille, paul


MATCH (paul:Homme {nom:'Claudel', prenom:'Paul', naissance:1868})
CREATE (marie:Personne:Femme {nom:'Perrin', prenom:'Marie'}) -[:MARIE_A {annee_mariage:1906}]->
(paul),
(louise:Personne:Femme {nom:'Vet h', prenom:'Louise', naissance:1905}) -[:A_PARENT]->
(paul), (louise) -[:A_PARENT]->
(rosalie:Personne:Femme {nom:'Vet h', prenom:'Rosalie', naissance:1871}),
(henri:Personne:Homme {nom:'Vet h', prenom:'Henri', naissance:1898}) -[:A_PARENT]->
(rosalie), (pierre:Personne:Homme {nom:'Claudel', prenom:'Pierre', naissance:1908}) -[:A_PARENT]->
(paul), (pierre) -[:A_PARENT]-> (marie),
(reine:Personne:Femme {nom:'Paris', prenom:'Reine', naissance:1910})
-[:A_PARENT]-> (marie), (reine) -[:A_PARENT]-> (paul),
(reinemarie:Personne:Femme {nom:'Paris', prenom:'Reine-Marie'})
-[:A_PARENT]-> (reine), (renee:Personne:Femme {nom:'Nantet', prenom:'Renee', naissance:1917})
-[:A_PARENT]-> (marie), (renee) -[:A_PARENT]-> (paul),
(victoire:Personne:Femme {nom:'Nantet', prenom:'Marie-victoire'})-[:A_PARENT]-> (renee);
RETURN 


2)
MATCH (f2:Personne) -[:A_PARENT]-> (f1:Personne)-[:A_PARENT]-> (p:Personne{nom:"Claudel",prenom: "Paul"} )
RETURN f2.nom,f2.prenom

3)
MATCH (cc:Femme {nom:'Claudel', prenom:'Camille'}) -[:A_PARENT]-> (pe:Personne) <-[:A_PARENT]-
(fs:Personne) <-[:A_PARENT]- (nn:Personne)
WHERE cc.prenom <> fs.prenom
RETURN *

Cette request retourne les frères et soeur de Camille Claudel, les neuveux et niéce de Camille Claudel, le père de Camille Claudel ainsi que camille Claudel elle même.

louis Claudel
Paul Claudel
Camille Claudel
Reine Paris
Renee Paris
Pierre Claudel
Louis vernhet


4)
Je sais pas je fais plus tard


III)
3.1)
Je sais pas encore


3.2)
function(doc){
    if(doc.type =='batiment' && doc.destination =='enseignement')
        emit(doc.code, doc.annee_constrution);
}

Parcrourt tout les batiments ne selectionne que les ceux à destination de "enseignement".
Retourne son nom de code ainsi que son année de construction.
Key     |   Value
t_b36       2019
t_b16       1966
t_b05       1964


2)

function (doc) { 
    if (doc.type =='batiment' && doc.destination =='enseignement'){
        if (Array.isArray(doc.salles)){
            for (var s in doc.salles)
                emit([doc.code, doc.salles[s].type], doc.salles[s.capacite]);
            }
    }
}  

parcrourt tout les batiments a destination "enseignement" et parcourt chaque salle de chaque batiment.
Retourne le nom de code du batiment, le type de salle ainsi que la capacité de la salle.
Key             |   Value
[t_b36,amphi]       120
[t_b36,amphi]      120
[t_b36,amphi]      120
[t_b36,TDInfo]      40
[t_b36,amphi]      80
[t_b16,amphi]      120
[t_b16,TD]      18
etc..



3)
function (doc) { 
    if (doc.type =='batiment' && doc.destination =='enseignement')
        {
            if (Array.isArray(doc.salles)){
                for (var s in doc.salles)
                    emit([doc.code, doc.salles[s].type], do.salles[s].capaite);
            }
        }
}

function(keys, values, reredue) {
    return sum(values);
}

Fait la même chose que la requet précédente mais en addittionnant a chaque fois les capacités des salles de classe.
Key             |   Value
[t_b36,amphi]       120
[t_b36,amphi]      240
[t_b36,amphi]      360
[t_b36,TDInfo]      400
[t_b36,amphi]      480
[t_b16,amphi]      600
[t_b16,TD]      618
etc..


