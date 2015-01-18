package com.shteinertree.generator;

import com.shteinertree.Condition;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class ConditionGenerator {
    private static ConditionGenerator instance;
    private static final Random random = new Random();
    private static final double multiplier = 2.0;
    private int matrix[][];
    private final Set<Integer> reqVert;
    private int reqVertSize;
    
    private ConditionGenerator(){
        this.reqVert = new HashSet<>();
    }
    
    public static ConditionGenerator getInstance(){
        if(instance == null){
            instance = new ConditionGenerator();
        }
        return instance;
    }
    
    public void generate(int size){
        matrix = new int[size][size];
        reqVertSize = generateReqVertSize();
        generateSymmMatrix();
        generateReqVert();
    }
    
    private void generateSymmMatrix(){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                if(j > i){
                    matrix[i][j] = (int)((random.nextInt(matrix.length) + 1) * multiplier);
                }
                else if(j == i){
                    matrix[i][j] = Integer.MAX_VALUE;
                }
                else{
                    matrix[i][j] = matrix[j][i];
                }
            }
        }
    }
    
    private void generateReqVert(){
        do{
            reqVert.add(random.nextInt(matrix.length));
        }
        while(reqVert.size() != reqVertSize);
    }
    
    private int generateReqVertSize(){ //не может быть сгенерирована <3 вершин
        return random.nextInt(matrix.length - 1) + 3;
    }
    
    public Condition getCondition(){
        return new Condition(matrix, reqVert);
    }
}
