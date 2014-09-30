
package com.shteinertree;

import com.shteinertree.utils.ConsoleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class Condition {
    private int[][] matrix;
    private final Set<Integer> reqVert;

    public Condition(int[][] matrix, Set<Integer> reqVert) {
        this.matrix = matrix;
        this.reqVert = reqVert;
    }
    
    public void loidWarshall(){
        for(int k=0; k<matrix.length; k++){
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix.length; j++){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
    }
    
    public boolean check(){
        return reqVert.size() <= matrix.length && matrix[0].length == matrix.length;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleUtils.ToString.matrixToString(matrix));
        sb.append("REQ vertices: ");
        Iterator<Integer> it = reqVert.iterator();
        sb.append(ConsoleUtils.ToString.collectionToString(it));
        return sb.toString();
    }
    
    public List<Edge> toEdgesList(){
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                if(j >= i && matrix[i][j] != 0 && matrix[i][j] != Integer.MAX_VALUE){
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }
        return edges;
    }
}
