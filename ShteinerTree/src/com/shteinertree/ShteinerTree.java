package com.shteinertree;

import com.shteinertree.exceptions.GraphInNotConnectedException;
import com.shteinertree.generator.ConditionGenerator;
import com.shteinertree.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class ShteinerTree {
    private static final int MATRIX_SIZE = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, GraphInNotConnectedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int menu = 0;
        while(menu == 0){
            String incorrectStr = "\tIncorrect number!";
            System.out.println("If you want to read graph from file, press 1.");
            System.out.print("If you want to generate its, press 2: ");
            try{
                menu = Integer.parseInt(br.readLine());
                if(menu != 1 && menu != 2){
                    menu = 0;
                    System.out.println(incorrectStr);
                }
            }
            catch(NumberFormatException nfe){
                System.out.println(incorrectStr);
            }
        }
        Condition cond = null;
        if(menu == 1){
            System.out.print("\tInput filename (without file extension): ");
            String fname = br.readLine();
            cond = Utils.Input.readFromTextFile(fname);
        }
        else{
            ConditionGenerator cg = ConditionGenerator.getInstance();
            cg.generate(MATRIX_SIZE);
            cond = cg.getCondition();
        }
        System.out.println(cond.toString() + "\n");
        cond.floidWarshall();
        System.out.println(cond.toString() + "\n");
        cond.deleteOptionalVertex();
        System.out.println(cond.toString() + "\n");
        cond.primKruscall();
        System.out.println(cond.toString() + "\n");
        System.out.println("Tree's weight: " + cond.getApprocsymWeight());
    }
    
}
