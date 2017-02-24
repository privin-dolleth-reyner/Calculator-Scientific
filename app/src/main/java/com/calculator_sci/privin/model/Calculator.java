package com.calculator_sci.privin.model;

import android.util.Log;


/**
 * Created by Privin_p on 22-02-2017.
 */

public class Calculator {

    private final String  TAG = "Calculator";
    private String op1, op2, operand,expression,fullExpression;
    private double memory = 0,memoryOp=0;
    private Boolean isUnCalculatedResult = false;



    public interface CalculatorViewDelegate{
        void updateView();
    }
    private CalculatorViewDelegate delegate = null;
    public Calculator(){

    }

    private void formatExpression(){
        expression = op1!=null?op1: " ";
        if (operand!=null && (operand.equals("√") || operand.equals("³√") || operand.equals("1/x"))){
            if (operand.equals("1/x")){
                expression = "1/"+expression;
            }else {
                expression = (operand != null ? operand : "") + expression;
            }
        }else if (operand!=null && (operand.equals("sin") || operand.equals("cos") || operand.equals("tan") || operand.equals("ln") || operand.equals("log") || operand.equals("±"))){
            expression = (operand!=null?operand+"("+(expression.trim().isEmpty()?"":expression+")"):expression);
        }else if (operand!=null && operand.equals("x²")){
            expression = expression + "²";
        }else {
            expression = expression + (operand!=null?operand:"");
        }
        expression = expression + (op2!=null?op2:"");
        if (expression.contains("NaN")){
            expression = "Infinity";
        }
    }

    public void updateView() {
        formatExpression();

        if (delegate!=null){
            delegate.updateView();
        }else {
            Log.d(TAG,"delegate is null");
        }
    }

    private String check_int(double result)
    {
        String temp="0";
        Log.d("Check int",": "+temp);
        if((int)result*10==result*10)
        {
            temp=String.valueOf((int)result);
        }else{
            temp=String.valueOf((float)result);
        }
        return temp;
    }
    private int setDefaultOp2() throws NullPointerException{
        if (operand.equals("+")||operand.equals("-")){
            return 0;
        }
        return 1;
    }
    public void checkUnCalculatedResult(){
        if (operand !=null){
            isUnCalculatedResult = true;
            result(false);
        }
    }
    public void setDelegate(CalculatorViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public String getExpression() {
        return expression;
    }

    public String getFullExpression() {
        return fullExpression;
    }

    public void memory(String s) {
        try {
        switch (s){
            case "MS":

                    memoryOp = Double.parseDouble(op1);
                    memory = memoryOp;


                break;
            case "M+":
                memory = memory+memoryOp;
                break;
            case "M-":
                memory = memory-memoryOp;
                break;
            case "MR":
                op1 = check_int(memory);
                expression = op1;
                break;
        }
        Log.d(TAG,s);
        fullExpression = "M "+ memory;
        if (delegate!=null){
            delegate.updateView();
        }
        }catch (Exception e){
            Log.d(TAG,e.getMessage());
        }
    }

    public double getResult(double x,double y,String op){
        switch (op){
            case "+" : return x+y;
            case "-": return x-y;
            case "/": return x/y;
            case "x": return x*y;
            case "√": return Math.sqrt(x);
            case "%": return x*y/100;
            case "x²":return x*x;
            case "³√":return Math.cbrt(x);
            case "^": return Math.pow(x,y);
            case "log": return Math.log10(x);
            case "ln": return Math.log(x);
            case "sin": return Math.sin(x);
            case "cos": return Math.cos(x);
            case "tan": return Math.tan(x);
            case "π": return x*Math.PI;
            case "1/x":return 1/x;
            case "±": return x<0? Math.abs(x):(-x);
            default: return 0;
        }
    }

    public void setOp(String num){
        Log.d(TAG,"in setOp "+num);
        if (op1 !=null && operand !=null){
            op2 =(op2 !=null? op2 + num:num);
        }else{
            op1 =(op1 !=null? op1 + num:num);
        }

        updateView();
    }



    public void result(boolean isEqualPressed){
        try {
            if (op2 != null) {
                fullExpression = expression;
            }
            double aa = Double.parseDouble(op1);
            double bb = op2 != null ? Double.parseDouble(op2) : setDefaultOp2();
            aa = getResult(aa, bb, operand);
            op1 = check_int(aa);
            op2 = null;
            if (isEqualPressed) {
                operand = null;
            }
            if (!isUnCalculatedResult) {
                updateView();
            } else {
                isUnCalculatedResult = false;
            }
        }catch (Exception e){
            expression = "Invalid!";
            updateView();
            expression = "";
            Log.d(TAG,e.getMessage());
        }
    }




    public void clearMemory(){
        op1=null;
        op2=null;
        operand=null;
        memory = 0;
        memoryOp = 0;
        expression=" ";
        fullExpression = " ";
        updateView();
    }
    public void clear(){
        op1=null;
        op2=null;
        operand=null;
        expression=" ";
        fullExpression = " ";
        updateView();
    }
    public void delete(){
        if (operand==null){
            op1 = op1.substring(0,op1.length()-1);
            if (op1.length()==0){
                op1=null;
            }
        }else{
            op2 = op2.substring(0,op2.length()-1);
        }
        updateView();
    }
}
