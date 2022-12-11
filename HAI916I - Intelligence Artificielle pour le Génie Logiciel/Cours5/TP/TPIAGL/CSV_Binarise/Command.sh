#!/bin/bash
echo "">BDDRCFT.rcft

java -jar fca4j-cli-0.4.3.jar family BDDRCFT.rcft -a IMPORT BDD.csv -n BDD -x CSV 


java -jar fca4j-cli-0.4.3.jar family BDDRCFT.rcft -a IMPORT Licenses.csv -n Licenses -x CSV 


java -jar fca4j-cli-0.4.3.jar family BDDRCFT.rcft -a IMPORT Languages.csv -n Languages -x CSV 


java -jar fca4j-cli-0.4.3.jar family BDDRCFT.rcft -a IMPORT BDD2Licenses.csv -n BDD2Licenses -x CSV -v  -op exist -source BDD -target Licenses


java -jar fca4j-cli-0.4.3.jar family BDDRCFT.rcft -a IMPORT BDD2Languages.csv -n BDD2Languages -x CSV -v -op exist -source BDD -target Languages


java -jar fca4j-cli-0.4.3.jar RCA ./BDDRCFT.rcft ./results -v -a CERES
#ARES
#        CERES
#        PLUTON
#        HERMES
#        ADD_EXTENT
#        ICEBERG
dot -Tpdf results/step2.dot -o results/resultat.pdf