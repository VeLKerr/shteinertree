
package com.shteinertree;

import com.shteinertree.exceptions.GraphInNotConnectedException;
import com.shteinertree.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.omg.CORBA.INTERNAL;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class Condition {
    private int[][] matrix;
//    private final Set<Integer> reqVert;
    private final int[] reqVert;

    public Condition(int[][] matrix, Set<Integer> reqVert) {
        this.matrix = matrix;
        this.reqVert = Utils.MathUtils.toIntMas(reqVert.toArray());
    }
    
    public void floidWarshall(){
        for(int k=0; k<matrix.length; k++){
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix.length; j++){
                    if(matrix[i][k] != 0 && matrix[k][j] != 0)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
    }
    
    public int[] prim() throws GraphInNotConnectedException{
        boolean[] used = new boolean[matrix.length];
        int[] minEdge = new int[matrix.length];
        int[] endOfTheEdge = new int[matrix.length];
        Arrays.fill(minEdge, Integer.MIN_VALUE);
        Arrays.fill(endOfTheEdge, -1);
        minEdge[reqVert[0]] = 0;
        for(int i=0; i<reqVert.length; ++i){
            int newVertice = -1;
            for(int verticeNum: reqVert){
                if(!used[verticeNum] && (newVertice == -1 || minEdge[verticeNum] < minEdge[newVertice])){
                    newVertice = verticeNum;
                }
            }
            if(minEdge[newVertice] == Integer.MIN_VALUE){
                throw new GraphInNotConnectedException();
            }
            used[newVertice] = true;
            for(int verticeNum: reqVert){
                if(verticeNum != newVertice && matrix[newVertice][verticeNum] < minEdge[verticeNum]){
                    minEdge[verticeNum] = matrix[newVertice][verticeNum];
                    endOfTheEdge[verticeNum] = newVertice;
                }
            }
        }
        return minEdge;
    }
    
    public boolean check(){
        return reqVert.length <= matrix.length && matrix[0].length == matrix.length;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Utils.ToString.matrixToString(matrix, sb);
        sb.append("REQ vertices: ");
        Utils.ToString.masToString(reqVert, sb);
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
