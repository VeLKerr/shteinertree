package com.shteinertree.utils;

import com.shteinertree.Condition;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public abstract class Utils {
    public static abstract class Input{
        private static final String fileExtension = ".txt";
        public static Condition readFromTextFile(String filename) throws FileNotFoundException, IOException{
            String delimiter = " ";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename + fileExtension)));
            StringTokenizer st = null;
            String line = br.readLine();
            int size = Integer.parseInt(line);
            int[][] matrix = new int[size][size];
            int i=0;
            while((line = br.readLine()) != null && i<size){
                st = new StringTokenizer(line, delimiter);
                int j=0;
                while(st.hasMoreTokens()){
                    int el = Integer.parseInt(st.nextToken());
                    if(el != 0){
                        matrix[i][j] = el;
                    }
                    else{
                        matrix[i][j] = Integer.MAX_VALUE;
                    }
                    j++;
                }
                i++;
            }
            Set<Integer> reqVerts = new HashSet<>();
            st = new StringTokenizer(br.readLine(), delimiter);
            while(st.hasMoreTokens()){
                reqVerts.add(Integer.parseInt(st.nextToken()));
            }
            return new Condition(matrix, reqVerts);
        }
    }
    public static abstract class ToString{
        public static StringBuilder matrixToString(int[][] matrix, StringBuilder sb){
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix[0].length; j++){
                    if(matrix[i][j] != Integer.MAX_VALUE){
                        sb.append(matrix[i][j]);
                    }
                    else{
                        sb.append("-");
                    }
                    sb.append("  ");
                }
                sb.append("\n");
            }
            return sb;
        }
        
        public static StringBuilder collectionToString(Iterator it, StringBuilder sb){
            while(it.hasNext()){
                sb.append(it.next()).append(" ");
            }
            return sb;
        }
        
        public static StringBuilder masToString(int[] mas, StringBuilder sb){
            for(int el: mas){
                sb.append(el).append(" ");
            }
            return sb;
        }
    }
    
    public static abstract class MathUtils{
        public static int[] toIntMas(Object[] mas){
            int[] res = new int[mas.length];
            for(int i=0; i<mas.length; i++){
                res[i] = (int)mas[i];
            }
            return res;
        }
    }
}
