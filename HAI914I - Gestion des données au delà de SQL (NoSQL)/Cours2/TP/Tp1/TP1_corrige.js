
II)
    1)
    curl -X PUT  http:/admin:admin@localhost:5984/matthieu_revision_occitanie/


    curl -X GET  http:/admin:admin@localhost:5984/_all_dbs

    2)
    curl -X POST http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_bulk_docs -d @herault.json -H "Content-Type:application/json"
    curl -X POST http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_bulk_docs -d @gard.json -H "Content-Type:application/json"
    curl -X POST http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_bulk_docs -d @aveyron.json -H "Content-Type:application/json"
    curl -X POST http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_bulk_docs -d @hauteGaronne.json -H "Content-Type:application/json"
    curl -X POST http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_bulk_docs -d @regions_partiel.json -H "Content-Type:application/json"


III)
    1)
    curl -X GET http:/admin:admin@localhost:5984/

    2)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_occitanie/

    3)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_all_docs


    4)

    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_occitanie/73



IV)
    I)
        1)
        function(doc){
            if (doc.type=="old_region") {
                emit(doc._id, doc); }
                
        }


        2)
        function(doc){
            if (doc.type=="commune") {
                emit(doc.nom, {"latitude":doc.latitude, "longitude":doc.longitude});
            }
        }



        3)
        function(doc){
            if (doc.nom=="MONTPELLIER") {
                emit(doc.codeInsee, {"latitude":doc.latitude, "longitude":doc.longitude});
            }
        }

        4)
        function(doc){
            if (doc.type=="commune") {
                if(doc.nom=="MONTPELLIER")
                emit(doc.codeInsee, {"latitude":doc.latitude, "longitude":doc.longitude});
            }
        }

        5)
        function (doc) {
            if(doc.nom_reg == "Occitanie"){
                var val = doc.president.prenom + "-" + doc.president.nom
                emit(doc.nom_reg, val);
                }
            }

        
        
            


    II)
        1)
        function (doc) {
            if(doc.type == "commune"){
                emit([doc.old_reg,doc.dep, doc._id], 1);
            }
        }



        2)
        function (doc) {
            if(doc.type == "commune"){
                emit(doc.nom, doc.populations[1].pop_1985);
            }
        }

        3)
        function (doc) {
            if(doc.type == "commune"){
                emit(doc.dep, doc.populations[1].pop_1985);
            }
        }
        4)
        function (doc) {
            if(doc.type == "commune"){
                emit(doc.old_reg, doc.populations[1].pop_1985);
            }
        }


    III)
        1)
        function (doc) {
            if(doc.type == "commune"){
                if(doc.populations[1].pop_1985 < doc.populations[2].pop_1995)
                emit(doc.nom, (doc.populations[1].pop_1985-doc.populations[2].pop_1995));
            }
        }


        2)
        function (doc) {
            if(doc.type == "region"){
                emit([doc._id,0], doc.nom_reg);
            }
            if(doc.type == "old_region"){
                emit([doc._id,1], doc.nom_reg);
            }
        }



    V)
    1)
    curl -X GET http:/admin:admin@localhost:5984/_membership

    2)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_shards
    
    
    3)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_occitanie/_shards/34172
  