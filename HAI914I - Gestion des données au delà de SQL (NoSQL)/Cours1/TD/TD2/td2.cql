2)

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

2.1)
1)
Match(p:Personne) <-[:ADMINISTREE_PAR]-(c:Commune)
SET p:maire

2)
CALL dbms.procedures() YIELD name
RETURN head(split(name,".")) as package, count(*), collect(name) as procedures;

3)
call apoc.export.json.query("MATCH (c:Commune) - [:WITHIN] - (d:Departement)-[:WHITHIN]-(r:Region)
Return id(c), labels(c), c.name, d.name" , "test.json", {} )