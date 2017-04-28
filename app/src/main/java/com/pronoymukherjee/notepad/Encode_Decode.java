package com.pronoymukherjee.notepad;

import java.util.Scanner;

/**
 * Created by pronoymukherjee on 26/04/17.
 */

public class Encode_Decode {
    /**
     * NOTE: This is the method to encode the text.
     * @param text: The text that has to be encoded before saving.
     * @return: The text after encoding it.
     */
    protected static String encodeText(String text){
        String encodeText="";
        int i,length=text.length();
        for(i=0;i<length;i++){
            encodeText+=(int)text.charAt(i)+"_";
        }
        return encodeText;
    }

    /**
     * NOTE: This is the method to decode the Text.
     * @param text: The text that is to be decoded.
     * @return: The text after decoding.
     */
    protected static String decodeText(String text){
        String decodedText="";
        Scanner scanner=new Scanner(text);
        scanner.useDelimiter("_");
        while (scanner.hasNext()){
            decodedText+=(char)Integer.parseInt(scanner.next());
        }
        return decodedText;
    }
}
