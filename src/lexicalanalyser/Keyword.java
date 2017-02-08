package lexicalanalyser;

/**
 * Created by Sourabh on 28-Jan-17.
 */
public class Keyword {
    //private String key[]={"int","float","char","long","array","main","printf","scanf","#include"};
    private String key[] = {"auto", "double", "int", "struct", "break","else", "long","switch", "case",	"enum",	"register", 	"typedef",
            "char",	"extern", "return",	"union", "continue", "for",	"signed",	"void", "do", "if", "static", "while",
            "default", "goto", "sizeof", "volatile", "const", "float", "short","unsigned"};
    public boolean isKeyword(String s)
    {
        //Return 1 if s is keyword,else return 0;
        int i=0;
        boolean b=false;
        for (i=0;i<key.length;i++)
        {
            if(key[i].equals(s))
            {
                b=true;
                break;
            }
        }
        return b;
    }

}
