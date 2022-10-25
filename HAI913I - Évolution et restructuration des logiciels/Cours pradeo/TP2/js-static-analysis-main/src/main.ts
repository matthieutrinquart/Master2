import {classHierarchyAnalysis, rapidTypeAnalysis, reachabilityAnalysis} from "./flow-analysis";
import { load } from "./load";

async function main() {
  const metamodel = await load("class.js");

  console.log(metamodel.main().instructions);

   console.log(reachabilityAnalysis(metamodel));
  console.log(classHierarchyAnalysis(metamodel));


  console.log(rapidTypeAnalysis(metamodel));
}

main();
