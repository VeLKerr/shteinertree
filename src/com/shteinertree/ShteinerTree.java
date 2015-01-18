package com.shteinertree;

import com.shteinertree.exceptions.InsufficientGraphException;
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
    private static final int MATRIX_SIZE = 7;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InsufficientGraphException{
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
        System.out.println(Utils.messages[0]);
        System.out.println(cond.toString(true));
        cond.floidWarshall();
        System.out.println(Utils.messages[1]);
        System.out.println(cond.toString(false));
        cond.deleteOptionalVertex();
        System.out.println(Utils.messages[2]);
        System.out.println(cond.toString(false));
        cond.primKruscall();
        System.out.println(Utils.messages[3]);
        System.out.println(cond.toString(false));
        System.out.println("Tree's weight approximation: " + cond.getApprocsymWeight());
    }
    
}
