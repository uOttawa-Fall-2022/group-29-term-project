package com.example.virtualclassroom;

public class DayTimeConflict {

    public static boolean dayHourConflict(Courses course1, Courses course2){
        if(course1==null || course2==null){
            return false;
        }
        return dayConflict(course1,course2) && hoursConflict(course1,course2);
    }

    public static boolean dayConflict(Courses course1, Courses course2){
        boolean returnBool;
        if(course1.getCourseDays() == null){
            returnBool = course2.getCourseDays() == null;
        }else if(course2.getCourseDays() == null){
            returnBool = false;
        }else{
            returnBool = timeConflict(course1.getCourseDays(),course2.getCourseDays());
        }
        return returnBool;
    }

    public static boolean hoursConflict(Courses course1, Courses course2){
        boolean returnBool;
        if(course1.getCourseHours() == null){
            returnBool = course2.getCourseHours() == null;
        }else if(course2.getCourseHours() == null){
            returnBool = false;
        }else{
            returnBool = hoursTimeConflict(course1.getCourseHours(),course2.getCourseHours());
        }
        return returnBool;
    }

    private static boolean hoursTimeConflict(String string1,String string2){
        boolean returnBool = false;
        if(timeConflict(string1,string2)){
            returnBool = true;
        }

        else {
            String[][] course1Time = instantiateHoursArray(string1);
            String[][] course2Time = instantiateHoursArray(string2);

            for (String[] strings : course1Time) {
                for (String[] value : course2Time) {
                    if (strings[0].trim().equalsIgnoreCase(value[0].trim()) || strings[1].trim().equalsIgnoreCase(value[1].trim())) {
                        returnBool = true;
                        break;
                    }else if(isBetween(strings[0].trim(),strings[1].trim(),value[0].trim()) || isBetween(strings[0].trim(),strings[1].trim(),value[1].trim())){
                        return true;
                    }
                }
            }
        }
        return returnBool;
    }

    private static boolean isBetween(String string1, String string2, String between){
        string1 = string1.toLowerCase();
        string2 = string2.toLowerCase();
        between = between.toLowerCase();

        if(string1.equals(string2)||between.equals(string2)){
            return false;
        }
        if(between.equals(string1)){
            return true;
        }
        boolean betweenIsBigger = Integer.parseInt(between.substring(0,2))>Integer.parseInt(string1.substring(0,2));
        boolean betweenIsSmaller = Integer.parseInt(string2.substring(0,2))>Integer.parseInt(between.substring(0,2));

        if(between.contains("am")&&string1.contains("am")){
            if(string2.contains("pm")){
                if(betweenIsBigger) return true;
            }
            else if(betweenIsBigger && betweenIsSmaller)
                return true;
        }
        if(between.contains("pm")&&string1.contains("pm")){
            return betweenIsBigger && betweenIsSmaller;
        }
        return false;
    }

    private static String[][] instantiateHoursArray(String string){
        String[][] sections = new String[sectionCounterComma(string)][2];
        StringBuilder tmp= new StringBuilder();
        int counter = 0;
        int innerCounter = 0;
        for (int i = 0;i < string.length(); i++){
            if(string.charAt(i)=='-'){
                sections[counter][innerCounter] = tmp.toString();
                innerCounter=1;
                tmp = new StringBuilder();
            }
            else if(string.charAt(i)==','){
                sections[counter][innerCounter] = tmp.toString();
                innerCounter = 0;
                tmp = new StringBuilder();
                counter++;
            }else tmp.append(string.charAt(i));
        }
        sections[counter][innerCounter] = tmp.toString();
        return sections;
    }

    private static boolean timeConflict(String string1,String string2){
        boolean returnBool = false;

        String[] course1Time = instantiateSectionArray(string1);
        String[] course2Time = instantiateSectionArray(string2);

        for (String time : course1Time) {
            for (String time2 : course2Time) {
                if (time.trim().equalsIgnoreCase(time2.trim())) {
                    returnBool = true;
                    break;
                }
            }
        }
        return returnBool;
    }

    private static String[] instantiateSectionArray(String string){
        String[] sections = new String[sectionCounterComma(string)];
        StringBuilder tmp= new StringBuilder();
        int counter = 0;
        for (int i = 0;i < string.length(); i++){
            if(string.charAt(i)==','){
                sections[counter] = tmp.toString();
                counter++;
                tmp = new StringBuilder();
            }else tmp.append(string.charAt(i));
        }
        sections[counter] = tmp.toString();
        return sections;
    }

    private static int sectionCounterComma(String string){
        int counter=1;
        for (int i = 0;i < string.length(); i++){
            if(string.charAt(i)==','){
                counter++;
            }
        }
        return counter;
    }
}
