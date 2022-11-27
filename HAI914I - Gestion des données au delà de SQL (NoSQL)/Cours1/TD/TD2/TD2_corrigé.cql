MATCH (c:Commune {name:'MONTPELLIER'})
CREATE (gf:Personne {nom:"FRECHE",prenom:"GEORGES"}) <-[ap1:ADMINISTREE_PAR
{date_debut:1997, date_fin:2004}]- (c),
(hm:Personne {nom:"MANDROUX",prenom:"HELENE"})<-[ap2:ADMINISTREE_PAR {date_debut:2004,
date_fin:2014}]-(c), (ps:Personne
{nom:"SAUREL",prenom:"PHILIPPE"})<-[ap3:ADMINISTREE_PAR {date_debut:2014,
date_fin:2020}]-(c), (md:Personne
{nom:"DELAFOSSE",prenom:"MICKAEL"})<-[ap4:ADMINISTREE_PAR {date_debut:2020,
date_fin:2026}]-(c)
return *

1)
MATCH (p:Personne) <-[:ADMINISTREE_PAR]- (c:Commune) SET p:Maire

2)

CALL dbms.procedures()
CALL dbms.functions()

3)


call apoc.export.json.query("MATCH (c:Commune)-[w1:WITHIN]-(d:Departement)-[w2:WITHIN]-(r:Region) RETURN id(c),labels(c) ,c.name, d.name, r.name;","test.json",{})




2.1.1)


