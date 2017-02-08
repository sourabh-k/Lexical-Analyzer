/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyser;

import com.sun.xml.internal.messaging.saaj.util.CharReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static sun.misc.Version.println;

/**
 * @author Sourabh
 */
public class LexicalAnalyser {

    /**
     * @param args the command line arguments
     */
    public static FileReader f1;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        int i = 0, c;
        Scanner in = new Scanner(System.in);
        f1 = new FileReader("chef_n_wildcard.cpp");
        FileWriter f2 = new FileWriter("output.cpp");
        StringBuilder sb = new StringBuilder();
        Keyword keyword = new Keyword();
        Punctuator punctuator = new Punctuator();
        int rflag = 1;
        char o=' ';
        int isComment=0;
        while (true) {

            if (rflag == 1)
            {
                c = readChar();
                if (c == -1)
                    break;
                else if (c == 1000) {
                    System.out.println("I/O exception caught while reading file provide proper typr of file");
                } else {
                    o = (char) c;
                }

            }
            int pb = punctuator.isPunctuator(o);
            if (pb == 100)
            {
                sb.append(o);
                rflag=1;

            } else {
                if (sb.toString().isEmpty() || sb.toString().equals("\n"))
                {

                    if((pb>=0 && pb<=4) || o=='|')
                    {
                        int temp = readChar();
                        char tempO= (char) temp;
                        int tempPb= punctuator.isPunctuator(tempO);
                        int f=0;
                        switch(o)
                        {
                            case '+':
                                if(tempO == o) // ++
                                    f=1;
                                if(tempO =='=')
                                {
                                    f=1;
                                }
                                break;
                            case '-':
                                if(tempO == o) // --
                                    f=1;
                                if(tempO == '-')
                                    f=1;
                                break;
                            case '<':         // <=
                                if(tempO == '=')
                                    f=1;
                                break;
                            case '>':         // >=
                                if(tempO == '=')
                                    f=1;
                                break;       // ==
                            case '=':
                                if(tempO == o)
                                    f=1;
                                break;
                            case '|':
                                if(tempO==o)
                                {
                                    f=1;
                                    break;
                                }
                        }
                        if(f ==1)
                        {
                            sb = new StringBuilder();
                            sb.append(o);
                            sb.append(tempO);
                            System.out.println("\"" + sb.toString() + "\""+"<Operator>");
                            rflag=1;
                        }
                        else
                        {
                            sb= new StringBuilder();
                            sb.append(o);
                            System.out.println("\"" + sb.toString() + "\""+"<Operator>");
                            o=tempO;
                            rflag=0;
                        }
                    }
                    else if(pb==6)
                    {
                        int temp = readChar();
                        char tempO= (char) temp;
                        int tempPb= punctuator.isPunctuator(tempO);
                        if(tempPb ==pb)
                        {
                            omitComment();
                        }
                        else
                        {
                            sb= new StringBuilder();
                            sb.append(o);
                            System.out.println("\"" + sb.toString() + "\""+"<Operator>");
                            rflag=0;
                            o=tempO;
                        }
                    }
                    else if ((pb >= 5 && pb <= 7) || o=='&')
                    {

                        sb= new StringBuilder();
                        sb.append(o);
                        System.out.println("\"" + sb.toString() + "\""+"<Operator>");
                        rflag =1;

                    }
                    else if(pb==17)
                    {
                        int temp = readChar();
                        char tempO= (char) temp;
                        StringBuilder tempSb= new StringBuilder();
                        tempSb.append(o);
                        tempSb.append(tempO);
                       while (tempO != '"')
                       {
                           temp = readChar();
                           tempO= (char) temp;
                           tempSb.append(tempO);
                       }
                        System.out.println(tempSb.toString()+"<String Literal>");
                       rflag=1;
                    }
                    else if(o!=' ' && o!='\n' && o!='\r')
                    {
                        sb= new StringBuilder();
                        sb.append(o);
                       System.out.println("\"" + sb.toString() + "\""+"<Separator>");
                        rflag =1;
                    }
                    else
                    {
                        rflag=1;
                    }
                    sb = new StringBuilder();
                    continue;
                }
                String s = sb.toString();
                if (keyword.isKeyword(s) == false)
                {
                    // TODO code for automata for identifier and number
                    boolean isIdentifier = Utility.isIdentifier(s);
                    if (isIdentifier)
                        System.out.println("\"" + s + "\""+"<Identifier>");
                    else if(s.charAt(0)=='\'' && s.charAt(2)=='\'' && s.length()==3)
                    {
                        System.out.println("\"" + s.charAt(1) + "\""+"<Character>");
                    }
                    else
                    {
                        int isNumber = Utility.isNumber(s);
                        StringBuilder tempSb = new StringBuilder();
                        if(isNumber!=-1)
                        {
                            if(s.charAt(s.length()-1) == 'e' )
                            {

                                if(o!='+' && o!='-')
                                {
                                    s="Error at "+s;
                                    isNumber=-1;
                                }
                                else {
                                    tempSb.append(o);
                                }
                                    int tempo ;
                                    tempo = readChar();
                                    char tempC=(char)tempo;
                                    while(punctuator.isPunctuator(tempC) ==100)
                                    {

                                        if(tempo >=48 && tempo<=57 && isNumber!=-1)
                                        {
                                            tempSb.append(tempC);
                                        }
                                        else
                                        {
                                            //s="Error at "+s
                                            System.out.print("Error at ");
                                            isNumber=-1;
                                        }
                                        tempo=readChar();
                                        tempC = (char) tempo;
                                    }
                                    rflag=0;
                                    o=tempC;
                                }
                                System.out.print("\"" + s + tempSb.toString()+"\"");
                            if(isNumber==1)
                                System.out.println("<Decimal Number>");
                            else if(isNumber == 2)
                            {
                                System.out.println("<Octal Number>");
                            }
                            else if(isNumber ==3)
                            {
                                System.out.println("<Hexa Number>");
                            }
                            else if(isNumber == 4)
                            {
                                System.out.println("<Floating Number>");
                            }
                        }
                        else
                        System.out.println("Error at o"+s);
                    }
                } else
                {
                    System.out.println("\"" + s + "\""+"<Keyword>");
                }
                // Process Buffer
                // Till Here
                sb = new StringBuilder();
                rflag=0;
            }

        }
    }

    public static int readChar() {
        int c = 1000;
        try {
            c = f1.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return c;
    }
    public static void omitComment()
    {
        int c=0;
        while (c!=10)
        {
            try {
                c = f1.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
