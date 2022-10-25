import { CallExpression, Function, Metamodel } from "./metamodel";

import { Node, Edge } from "vis-network";

export function reachabilityAnalysis(metamodel: Metamodel): {
  nodes: Array<Node>;
  edges: Array<Edge>;
} {
  const nodes: Array<Node> = [];
  const edges: Array<Edge> = [];

  function findTargets(current: Function, call: CallExpression): Function[] {
    // Compute function with same name
    const ret : Array<Function> = [];
    if(Function.name === CallExpression.name){
      ret.push(current);
    }


    return ret
  }

  for (const [id, current] of Object.entries(metamodel.functions)) {
    nodes.push({
      id: Number(id),
      label: current.label(),
    });
  }

  // Reach a fixpoint
  while (true) {
    let oneChange = false;

    for (const [id, current] of Object.entries(metamodel.functions)) {
      for (const instruction of current.instructions) {
        instruction.visit((node) => {
          if (node.kind === "CallExpression") {
            const call = node as CallExpression;

            for (const target of findTargets(current, call)) {
              const from = Number(id);
              const to = metamodel.functions.indexOf(target);

              const alreadyExists =
                edges.findIndex((e) => e.from === from && e.to === to) !== -1;

              if (!alreadyExists) {
                edges.push({
                  from,
                  to,
                });

                oneChange = true;
              }
            }
          }
        });
      }
    }

    if (!oneChange) break;
  }

  return { nodes, edges };
}

export function classHierarchyAnalysis(metamodel: Metamodel): {
  nodes: Array<Node>;
  edges: Array<Edge>;

} {
  // Compute function with same name
  const nodes: Array<Node> = [];
  const edges: Array<Edge> = [];



  return { nodes, edges };
}

export function rapidTypeAnalysis(metamodel: Metamodel): {
  nodes: Array<Node>;
  edges: Array<Edge>;
} {
  // Compute function with same name
  const nodes: Array<Node> = [];
  const edges: Array<Edge> = [];



  return { nodes, edges };
}
