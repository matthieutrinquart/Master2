I)
    1)
    curl -X PUT  http:/admin:admin@localhost:5984/matthieu_revision_vaccination?q=8
    2)
    curl -X POST http:/admin:admin@localhost:5984/matthieu_revision_vaccination/_bulk_docs -d @vaccin1.json -H "Content-Type:application/json"


II)
    1)

    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_vaccination/
    5806
    2)

    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_vaccination/_all_docs

    3)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_vaccination/12_05-JAN-21_AstraZeneka


III)
    I)
        1)
        function (doc) {
            if(doc.type == "couverture_vaccinale" && doc.dep == "34"){
                emit(doc._id, doc.jour);
            }
        }
        2)
        function (doc) {
            if(doc.type == "couverture_vaccinale" && doc.dep == "34"){
                emit(doc.dep, 1);
            }
        }

        3)

        function (doc) {
            if(doc.type == "couverture_vaccinale" && doc.dep == "34"){
                emit(new Date(doc.jour).getMonth(), 1);
            }
        }

        4)

        function (doc) {
            if(doc.type == "couverture_vaccinale"){
                emit([doc.dep,new Date(doc.jour).getFullYear()], 1);
            }
        }

        5)

        function (doc) {
            if(doc.type == "couverture_vaccinale"){
                emit([doc.dep,new Date(doc.jour).getFullYear()], 1);
            }
        }

        6)
        function (doc) {
            if(doc.type == "couverture_vaccinale" && doc.doses[0].vaccin == "Pfizer"){
                emit(doc.dep, 1);
            }
        }

        7)

        function (doc) {
            if(doc.type == "couverture_vaccinale" && doc.doses[0].vaccin == "Pfizer"){
                emit([doc.dep,new Date(doc.jour).getMonth(),new Date(doc.jour).getFullYear()],  doc.doses[0].dose1);
            }
        }
IV)

    1)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_vaccination/_shards

    2)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_vaccination/_shards/12_27-DEC-20_AstraZeneka


    3)
    curl -X GET http:/admin:admin@localhost:5984/matthieu_revision_vaccination/_shards





