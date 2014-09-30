package com.shteinertree;

import com.shteinertree.generator.ConditionGenerator;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class ShteinerTree {
    private static final int MATRIX_SIZE = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConditionGenerator cg = ConditionGenerator.getInstance();
        cg.generate(MATRIX_SIZE);
        Condition cond = cg.getCondition();
        System.out.println(cond.toString() + "\n");
        cond.loidWarshall();
        System.out.println(cond.toString() + "\n");
        List<Edge> edges = cond.toEdgesList();
        Collections.sort(edges);
        for(Edge ed: edges){
            System.out.println(ed.toString());
        }
    }
    
}
