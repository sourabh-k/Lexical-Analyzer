package lexicalanalyser;

/**
 * Created by Sourabh on 28-Jan-17.
 */
public class Punctuator {
    char []pun={'+',
            '-',
            '=',
            '>',
            '<',
            '*',
            '/',
            '%',
            ';',
            ',',
            '{',
            '}',
            '(',
            ')',
            ' ',
            '\n',
            '\r',
            '"',
            '[',
            ']',
            '&',
            '|'
    };
    public int isPunctuator(char c)
    {
        // Return 1 if c is Punctuator else return 0;
        int i,re;
        re=100;
        for(i=0;i<pun.length;i++)
        {
            if( pun[i] == c)
            {
                re=10;
                if(i<8)
                    re=i;
                else if(i==17)
                    re=17;
                break;
            }
        }
        return  re;
    }

}
