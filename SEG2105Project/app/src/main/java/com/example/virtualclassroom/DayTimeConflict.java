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
            returnBool = timeConflict(course1.getCourseHours(),course2.getCourseHours());
        }
        return returnBool;
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
