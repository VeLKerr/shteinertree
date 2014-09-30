package com.shteinertree.utils;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public abstract class ConsoleUtils {
    public static abstract class Input{
        public static Set<Integer> inputSet(BufferedReader br, int maxSize){
            Set<Integer> res = new HashSet<>();
            int cnt = 0;
            boolean stopFlag = false;
            do{
                //TODO: realize input!
            }
            while(stopFlag || cnt == maxSize);
            return res;
        }
    }
    public static abstract class ToString{
        public static StringBuilder matrixToString(int[][] matrix){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix[0].length; j++){
                    sb.append(matrix[i][j]).append("  ");
                }
                sb.append("\n");
            }
            return sb;
        }
        
        public static StringBuilder collectionToString(Iterator it){
            StringBuilder sb = new StringBuilder();
            while(it.hasNext()){
                sb.append(it.next()).append(" ");
            }
            return sb;
        }
    }
}
