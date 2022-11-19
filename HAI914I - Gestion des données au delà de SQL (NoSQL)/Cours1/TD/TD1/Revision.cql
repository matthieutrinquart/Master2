2.1.1)
CREATE (m:Commune {nom:'MONTPELLIER', latitude:43.610769, longitude:3.876716,
codeinsee:'34172'}) -[:WITHIN]-> (h:Departement {nom:'HERAULT', numero:'34'}),
(h) <-[:WITHIN]- (l:Commune {nom:'LUNEL', latitude:43.67445, longitude:4.135366,
codeinsee:'34145'}), (m) -[:NEARBY]-> (l), (c:Commune {nom:'CLAPIERS',
latitude:43.6575, longitude:3.88833, codeinsee:'34830', pop_17:5135}) -[:WITHIN]->
(h), (m) -[:NEARBY {type:'border'}]-> (c)
RETURN l, m, h, c





MATCH (l:Commune {nom:'LUNEL'})
CREATE (m:Commune {nom:'NIMES', latitude:43.836699, longitude:4.360054,
codeinsee:'30189'}) -[:WITHIN]-> (h:Departement {nom:'GARD', numero:'30'}), (m)
-[:NEARBY]-> (l)



MATCH (h:Departement {nom:'GARD', numero:'30'})
CREATE (m:Commune {nom:'GARONS', latitude:43.770059, longitude:4.424545,
codeinsee:'30125'}) -[:WITHIN]-> (h), (m) -[:NEARBY]-> (s:Commune {nom:'SOMMIERES',
codeinsee:'30321'}), (s) -[:WITHIN]-> (h)

MATCH (g:Commune {nom:'GARONS'})
MATCH (n:Commune {nom:'NIMES'})
CREATE (g) -[:NEARBY]-> (n)



MATCH (h:Departement {numero:'34'})
MATCH (m:Commune {nom:'MONTPELLIER'})
CREATE (c:Commune {nom:'MONTFERRIER-SUR-LEZ', latitude:43.671824, longitude:3.859265,
codeinsee:'34169',pop_1975:1682}) -[:WITHIN]-> (h), (c) -[:NEARBY]-> (m),(cr:Commune
{nom:'CRES', latitude:43.644825, longitude:3.936612,
codeinsee:'34090',pop_1975:4507}) -[:WITHIN]-> (h), (cr) -[:NEARBY]-> (m),
(ca:Commune {nom:'CASTELNAU-LE-LEZ', latitude:43.634144, longitude:3.897398,
codeinsee:'34057',pop_1975:9446}) -[:WITHIN]-> (h), (ca) -[:NEARBY
{type:'border'}]-> (m),(cas:Commune {nom:'CASTRIES', latitude:43.677589,
longitude:3.985579, codeinsee:'34058',pop_1975:2494}) -[:WITHIN]-> (h), (cas)
-[:NEARBY]-> (m), (stc:Commune {nom:'SAINT-CLEMENT-DE-RIVIERE', latitude:43.6844,
longitude:3.8472, codeinsee:'34247',pop_1975:845,pop_2010:4985}) -[:WITHIN]-> (h),
(stc) -[:NEARBY]-> (m)


2.1.2)

MATCH (s:Commune {nom:'SOMMIERES'})
SET s.pop_1975=3072, s.latitude=43.783450, s.longitude=4.089738
RETURN s
MATCH (s:Commune {nom:'SOMMIERES'}) <-[r:NEARBY]- (g:Commune {nom:'GARONS'})
SET r.distance = 27.4

2.1.3)
MATCH (c1:Commune)-[:NEARBY]->()<-[:NEARBY]-(c2:Commune)
MERGE (c1)-[:NEARBY]-(c2)
2.2)

communes de l’HERAULT
MATCH (d:Departement {nom:"HERAULT"}) <-[p:WITHIN]- (n:Commune)
RETURN d, n, p


communes qui sont proches de MONTPELLIER
MATCH (m:Commune {nom:"MONTPELLIER"}) -[:NEARBY]- (n:Commune)
RETURN m, n


MATCH (m:Commune {nom:"MONTPELLIER"}) -[:NEARBY*]- (n:Commune)
RETURN m, n


communes qui sont proches d’une commune qui est proche de Montpellier
MATCH (m:Commune {nom:"MONTPELLIER"}) -[:NEARBY*2]- (n:Commune)
RETURN m, n



tailles des chemins menant de Montpellier a Nimes (ou son inverse)
MATCH p=((c1:Commune {nom:"MONTPELLIER"})-[:NEARBY*]-(c2:Commune {nom:"NIMES"}))
RETURN length(p)



2.2.1)

-- Tout supprimer
MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r
--ou
MATCH (n)
DETACH DELETE n
-- un noeud
MATCH (n:Commune {nom:"SOMMIERES"})
DELETE n
-- une relation
MATCH (n:Commune { nom:"GARONS" })-[r:NEARBY]->()
DELETE r
-- un attribut de noeud
MATCH (n:Commune { nom:"NIMES" })
REMOVE n.codeinsee
return n

3)
LOAD CSV WITH HEADERS FROM "file:///Region.csv" AS regions
CREATE (r:Region {id : toInteger(regions.id), name : regions.name});
LOAD CSV WITH HEADERS FROM "file:///Com_Dep.csv" AS com_dep
MERGE (co : Commune { codeinsee : toInteger(com_dep.codeinsee) })
MERGE (d : Departement { id : com_dep.id })
CREATE (co)-[:WITHIN]->(d);

3.1)
LOAD CSV WITH HEADERS FROM "file:///Commune.csv" AS communes
CREATE (c:Commune {codeinsee : toInteger(communes.codeinsee), name : communes.name,longitude: toFloat(communes.longitude),latitude: toFloat(communes.latitude) });


LOAD CSV WITH HEADERS FROM 'file:///Departement.csv' AS departements
CREATE (d:Departement {id : departements.id, name : departements.name});


LOAD CSV WITH HEADERS FROM "file:///Region.csv" AS regions
CREATE (r:Region {id : regions.id, name : regions.name});


LOAD CSV WITH HEADERS FROM "file:///Com_Com.csv" AS com_com
MERGE (c1 : Commune { codeinsee : toInteger(com_com.codeinsee1) })
MERGE (c2 : Commune { codeinsee : toInteger(com_com.codeinsee2) })
CREATE (c1)-[:NEARBY]->(c2);



LOAD CSV WITH HEADERS FROM "file:///Com_Dep.csv" AS com_dep
MERGE (co : Commune { codeinsee : toInteger(com_dep.codeinsee) })
MERGE (d : Departement { id : com_dep.id })
CREATE (co)-[:WITHIN]->(d);


LOAD CSV WITH HEADERS FROM 'file:///Reg_Dep.csv' AS reg_dep
MERGE (r : Region { id : reg_dep.idR })
MERGE (d : Departement { id : reg_dep.idD })
CREATE (d)-[:WITHIN]->(r);



MATCH (c:Commune {name:"MONTPELLIER"})
MATCH (d:Departement {name:"HERAULT"})
CREATE (c)-[:CHEFLIEU]->(d);



MATCH (c:Commune {name:"NIMES"})
MATCH (d:Departement {name:"GARD"})
CREATE (c)-[:CHEFLIEU]->(d);


MATCH (l:Departement {name:"HAUTE-GARONNE"})
MATCH (r:Region {name:"OCCITANIE"})
CREATE (m:Commune {name:'TOULOUSE', latitude:43.604652, longitude:1.444209,
codeinsee:'31555'})-[:WITHIN]-> (l), (l)-[:WITHIN]-> (r), (m)-[:CHEFLIEU]->(l),(m)-[:CHEFLIEU]->(r) ;

Exercice4)
1)
MATCH (d:Departement)-[:WITHIN]->(r:Region {name:"OCCITANIE"})
RETURN count(d);

2)
MATCH (d:Departement)-[:WITHIN]->(r:Region)
RETURN r.name, count(d);

3)
MATCH (d:Departement)-[:WITHIN]->(r:Region)
WITH r,count(d) AS departements
WHERE departements >6
RETURN r.name, departements;

4)

MATCH (c1:Commune)-[:NEARBY]->(m:Commune {name:"MONTPELLIER"})
MATCH (c1)-[:WITHIN]->(d:Departement)
RETURN c1.name, d.name;


5)
MATCH (c1:Commune)-[:NEARBY]-(m:Commune {name:"MONTPELLIER"})
MATCH (c1)-[:WITHIN]->(d:Departement)
WHERE d.name <> "HERAULT"
RETURN c1.name, d.name;


MATCH (m:Commune) -[:NEARBY]- (v1:Commune)
-[:NEARBY]- (v2:Commune) -[:WITHIN]-> (d:Departement)
WHERE m.name ="MONTPELLIER" AND d.name <> "HERAULT"
RETURN v2.name, d.name


6)
MATCH (c:Commune)-[:WITHIN]-(d:Departement)
RETURN c.name, d.name;


7)
MATCH p=((m:Commune {name:"MONTPELLIER"}) -[:NEARBY*]-(n:Commune{name:"NIMES"}))
RETURN p;

8)

MATCH (c1:Commune {name:"MONTPELLIER"}), (c2:Commune {name:"NIMES"}),
path = shortestpath((c1)-[:NEARBY*]-(c2))
RETURN path


exercice 6)


1)
MATCH(n) RETURN distinct labels(n)

2)
MATCH ()-[r]-() RETURN distinct type(r)

3)
MATCH (n)-[r]-() RETURN distinct labels(n),type(r)


4)
MATCH ()-[r]->() RETURN type(r), count(*) as cardinality_relation