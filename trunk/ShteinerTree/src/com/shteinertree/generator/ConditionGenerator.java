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
                if(j >= i){
                    matrix[i][j] = random.nextInt((int)(matrix.length * 1.5));
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
    
    private int generateReqVertSize(){
        return random.nextInt(matrix.length) + 1;
    }
    
    public Condition getCondition(){
        return new Condition(matrix, reqVert);
    }
}
