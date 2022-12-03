package com.example.virtualclassroom;

public class StartEndTimeValidator {

    public static boolean hasStartAndEndTime(String string){
        boolean endFlag=false;
        for(int i=0;i<string.length();i++){
            if(endFlag&&string.charAt(i)==','){
                endFlag=false;
            }
            else if(!endFlag&&string.charAt(i)==','){
                break;
            }
            else if(endFlag&&string.charAt(i)=='-'){
                endFlag=false;
                break;
            }
            else if(string.charAt(i)=='-'){
                endFlag=true;
            }
        }
        return endFlag;
    }
}
