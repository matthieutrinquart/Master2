LOAD CSV WITH HEADERS FROM 'file:///Commune.csv' AS communes
CREATE (co:Commune {codeinsee : toInteger(communes.codeinsee), name : communes.name, longitude : toFloat(communes.longitude), latitude : toFloat(communes.latitude)});


LOAD CSV WITH HEADERS FROM 'file:///Departement.csv' AS departements
CREATE (d:Departement {id : departements.id, name : departements.name});


LOAD CSV WITH HEADERS FROM 'file:///Region.csv' AS regions
CREATE (r:Region {id : toInteger(regions.id), name : regions.name});


LOAD CSV WITH HEADERS FROM 'file:///Com_Dep.csv' AS com_dep
MERGE (co : Commune { codeinsee : toInteger(com_dep.codeinsee) })
MERGE (d : Departement { id : com_dep.id })
CREATE (co)-[:WITHIN]->(d);


LOAD CSV WITH HEADERS FROM 'file:///Reg_Dep.csv' AS reg_dep
MERGE (r : Region { id : toInteger(reg_dep.idR) })
MERGE (d : Departement { id : reg_dep.idD })
CREATE (d)-[:WITHIN]->(r);


LOAD CSV WITH HEADERS FROM 'file:///Com_Com.csv' AS com_com
MERGE (co1 : Commune { codeinsee : toInteger(com_com.codeinsee1) })
MERGE (co2 : Commune { codeinsee : toInteger(com_com.codeinsee2) })
CREATE (co1)-[:NEARBY]->(co2);


