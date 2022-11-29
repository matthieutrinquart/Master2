
1)
curl -X PUT $COUCH3/matthieu_revision_occitanie

curl -X GET $COUCH3/_all_dbs


2)
curl -X POST $COUCH3/matthieu_revision_occitanie/_bulk_docs -d @herault.json -H "Content-Type:application/json"
curl -X POST $COUCH3/matthieu_revision_occitanie/_bulk_docs -d @gard.json -H "Content-Type:application/json"
curl -X POST $COUCH3/matthieu_revision_occitanie/_bulk_docs -d @aveyron.json -H "Content-Type:application/json"
curl -X POST $COUCH3/matthieu_revision_occitanie/_bulk_docs -d @hauteGaronne.json -H "Content-Type:application/json"
curl -X POST $COUCH3/matthieu_revision_occitanie/_bulk_docs -d @regions_partiel.json -H "Content-Type:application/json"


3)
1)
curl -X GET $COUCH3/

2)
curl -X GET $COUCH3/matthieu_revision_occitanie/

3)
curl -X GET $COUCH3/matthieu_revision_occitanie/_all_docs


4)

curl -X GET $COUCH3/matthieu_revision_occitanie/73



4.1)
curl -X PUT $COUCH3/matthieu_revision_occitanie/_design/Q1 -d '{"views":{"v1":{"map":"function(doc) { if (doc.type==`old_region`) {emit(doc._id, doc) ;} }"}}}' -H "Content-Type: application/json"
curl -X GET $COUCH3/matthieu_revision_occitanie/_design/Q1/_view/v1?reduce=false


function(doc){
    if (doc.type=="old_region") {
        emit(doc._id, doc); }
        
}

1)