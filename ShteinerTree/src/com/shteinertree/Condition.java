
package com.shteinertree;

import com.shteinertree.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class Condition {
    private int[][] initialMatrix;
    private int[][] treeMatrix;
    private final TreeSet<Integer> reqVert;
    private int approcsymWeight;

    public Condition(int[][] matrix, Set<Integer> reqVert) {
        this.initialMatrix = matrix;
        this.reqVert = new TreeSet<>(reqVert);
    }
    
    public void floidWarshall(){
        for(int k=0; k<initialMatrix.length; k++){
            for(int i=0; i<initialMatrix.length; i++){
                for(int j=0; j<initialMatrix.length; j++){
                    if(initialMatrix[i][k] != Integer.MAX_VALUE && 
                       initialMatrix[k][j] != Integer.MAX_VALUE &&
                       i != j && i != k && j != k &&
                       initialMatrix[i][j] != Integer.MAX_VALUE)
                    initialMatrix[i][j] = Math.min(initialMatrix[i][j], initialMatrix[i][k] + initialMatrix[k][j]);
                }
            }
        }
    }
    
    public void primKruscall(){
        treeMatrix = new int[initialMatrix.length][initialMatrix.length];
        fillMatrix(treeMatrix);
        Set<Integer> usedIndexes = new HashSet<>();
        boolean isFirstStep = true;
        while(usedIndexes.size() != initialMatrix.length && !isEmpty(initialMatrix)){
            int[] elem = findMin(usedIndexes);
            treeMatrix[elem[0]][elem[1]] = elem[2];
            usedIndexes.add(elem[0]);
            deleteRow(elem[0]);
            if(isFirstStep){
                usedIndexes.add(elem[1]);
                deleteRow(elem[1]);
                isFirstStep = false;
            }
        }
        countApprocsym();
        symmMatr();
    }
    
    private void deleteRow(int colIndex){
        Arrays.fill(initialMatrix[colIndex], Integer.MAX_VALUE);
    }
    //i, j, value
    private int[] findMin(Set<Integer> userIndexes){
        int[] res = new int[3];
        Arrays.fill(res, Integer.MAX_VALUE);
        for(int i=0; i<initialMatrix.length; i++){
            for(int j=0; j<initialMatrix.length; j++){
                if((userIndexes.contains(j) || userIndexes.isEmpty()) && initialMatrix[i][j] < res[2]){
                    res = new int[]{i, j, initialMatrix[i][j]};
                }
            }
        }
        return res;
    }
    
    private static void fillMatrix(int[][] matrix){
        for(int[] row: matrix){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
    }
    
    private void countApprocsym(){
        for (int[] treeMatrixRow : treeMatrix) {
            for (int j = 0; j<treeMatrix.length; j++) {
                int el = treeMatrixRow[j];
                if(el != Integer.MAX_VALUE){
                    approcsymWeight += el;
                }
            }
        }
    }
    
    public void deleteOptionalVertex(){
        int shift = 0;
        int initLen = initialMatrix.length;
        for(int i=0; i<initLen; i++){
            if(!reqVert.contains(i)){
                strikeOut(i - shift);
                shift++;
            }
        }
    }
    
    private void strikeOut(int index){
        int[][] res = new int[initialMatrix.length - 1][initialMatrix.length - 1];
        for(int i=0; i<initialMatrix.length; i++){
            for(int j=0; j<initialMatrix.length; j++){
                if(i != index && j != index){
                    int newI = i;
                    int newJ = j;
                    if(i > index){
                        newI = i - 1;
                    }
                    if(j > index){
                        newJ = j - 1;
                    }
                    res[newI][newJ] = initialMatrix[i][j];
                }
            }
        }
        initialMatrix = res;
    }
    
    private void symmMatr(){
        for(int i=0; i<treeMatrix.length; i++){
            for(int j=0; j<treeMatrix.length; j++){
                if(treeMatrix[i][j] != Integer.MAX_VALUE){
                    treeMatrix[j][i] = treeMatrix[i][j];
                }
            }
        }
    }
    
    protected boolean check(){
        return reqVert.size() <= initialMatrix.length && initialMatrix[0].length == initialMatrix.length;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(!isEmpty(initialMatrix)){
            Utils.ToString.matrixToString(initialMatrix, sb);
        }
        else{
            Utils.ToString.matrixToString(treeMatrix, sb);
        }
        sb.append("REQ vertices: ");
        Utils.ToString.collectionToString(reqVert.iterator(), sb);
        return sb.toString();
    }
    
    private static boolean isEmpty(int[][] matrix){
        for (int[] matrixRow : matrix) {
            for (int el: matrixRow) {
                if(el != Integer.MAX_VALUE){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Deprecated
    public List<Edge> toEdgesList(){
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<treeMatrix.length; i++){
            for(int j=0; j<treeMatrix.length; j++){
                if(treeMatrix[i][j] != Integer.MAX_VALUE){
                    edges.add(new Edge(i, j, treeMatrix[i][j]));
                }
            }
        }
        return edges;
    }

    public int getApprocsymWeight() {
        return approcsymWeight;
    }
}
