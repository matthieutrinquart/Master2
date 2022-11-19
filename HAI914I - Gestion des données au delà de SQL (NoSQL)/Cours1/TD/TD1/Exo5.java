
import java.io.File;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;



public class Exo5 {
	   private static final File DB_Folder = new File("/home/matthieu/Bureau/neo4j-community-3.5.21_Ext/data/databases/graph.db");

	   private static enum Ex_Labels implements Label {
        COMMUNE,
        REGION,
        DEPARTEMENT
	    }
	   
	   
	   public static void main(String[] args) {
	      GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
	      GraphDatabaseService graphDB = dbFactory.newEmbeddedDatabase(DB_Folder);

	      try (Transaction tx =graphDB.beginTx()) {
	    	  
	    	   Node SG_node =graphDB.createNode(Ex_Labels.COMMUNE);	
	        //   Node um_node =graphDB.createNode(Ex_Labels.SCHOOL);
	           SG_node.setProperty("name", "SAINT-GAUDENS");
               SG_node.setProperty("codeinsee", 31483);
	           SG_node.setProperty("latitude", 43.106895);
	           SG_node.setProperty("longitude", 1.723763);
	           //tom_node.setProperty("name", "Tom");
	           //tom_node.createRelationshipTo(um_node, Relation.ENROLLED_IN);
	           
            /* 


	           Result result = graphDB.execute(
	 				  "MATCH (u:SCHOOL) <-[ENROLLED_IN]- (s:STUDENT) " +
	 				  "WHERE s.name = 'Tom'" +
	 				  "RETURN u.name, u.city");
	 		 while ( result.hasNext() )
	 	     {
	 	         Map<String, Object> row = result.next();
	 	         for ( String key : result.columns() )
	 	         {
	 	             System.out.printf( "%s = %s%n", key, row.get( key ) );
	 	         }
	 	     }
             */
	           
	           
	           tx.success();
	      }
	   }
}
