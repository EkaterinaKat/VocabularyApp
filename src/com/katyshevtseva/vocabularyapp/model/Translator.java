package com.katyshevtseva.vocabularyapp.model;

public class Translator {
    private static final char[] rus = {'й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х', 'ъ', 'ф', 'ы', 'в', 'а',
            'п', 'р', 'о', 'л', 'д', 'ж', 'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю'};
    private static final char[] eng = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', 'a', 's', 'd', 'f',
            'g', 'h', 'j', 'k', 'l', ';', '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.'};


    public static String translateToEng(String inString){
        char[] inChars = inString.toCharArray();
        for(int i = 0; i<inChars.length; i++){
            for(int j = 0; j<rus.length; j++){
                if(inChars[i]==rus[j]){
                    inChars[i]=eng[j];
                }
            }
        }
        return new String(inChars);
    }

    public static boolean isStingInEnglish(String inString){
        char[] inChars = inString.toCharArray();
        for(char inChar: inChars){
            for(char rusChar: rus){
                if(rusChar==inChar){
                    return false;
                }
            }
        }
        return true;
    }


    public static String translateToRus(String inString){
        char[] inChars = inString.toCharArray();
        for(int i = 0; i<inChars.length; i++){
            for(int j = 0; j<eng.length; j++){
                if(inChars[i]==eng[j]){
                    inChars[i]=rus[j];
                }
            }
        }
        return new String(inChars);
    }

    public static boolean isStingInRussian(String inString){
        char[] inChars = inString.toCharArray();
        for(char inChar: inChars){
            for(char engChar: eng){
                if(engChar==inChar){
                    return false;
                }
            }
        }
        return true;
    }

}
