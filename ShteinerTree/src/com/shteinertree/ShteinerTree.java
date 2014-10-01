package com.shteinertree;

import com.shteinertree.exceptions.GraphInNotConnectedException;
import com.shteinertree.generator.ConditionGenerator;
import com.shteinertree.utils.Utils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class ShteinerTree {
    private static final int MATRIX_SIZE = 10;
    private static final String fname = "1";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, GraphInNotConnectedException {
        ConditionGenerator cg = ConditionGenerator.getInstance();
        cg.generate(MATRIX_SIZE);
        Condition cond = cg.getCondition();
//        Condition cond = Utils.Input.readFromTextFile(fname);
//        System.out.println(cond.toString() + "\n");
        System.out.println(cond.toString() + "\n");
        cond.floidWarshall();
        System.out.println(cond.toString() + "\n");
        List<Edge> edges = cond.toEdgesList();
        Collections.sort(edges);
        for(Edge ed: edges){
            System.out.println(ed.toString());
        }
        //System.out.println(Utils.ToString.masToString(cond.prim(), new StringBuilder()));
    }
    
}
