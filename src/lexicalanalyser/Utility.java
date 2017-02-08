package lexicalanalyser;

/**
 * Created by Sourabh on 29-Jan-17.
 */
public  class Utility {
    public static  boolean isIdentifier(String s)
    {
        int i,j,k,l,n,x,y,state;
        char c;
        boolean res=true;
        i=0;
        state =1;
        while(i<s.length())
        {
            //System.out.print(state+" ");
            switch (state)
            {
                case 1:
                    c= s.charAt(i);
                    k=c;
                    if((k>=65 &&k<=90) || (k>=97 && k<=122) || k==95)
                    {
                        state=2;
                    }
                    else
                    {
                        res=false;
                        break;
                    }
                case 2:
                    c= s.charAt(i);
                    k=c;
                    if((k>=65 &&k<=90) || (k>=97 && k<=122) || k==95)
                    {
                        state=2;
                    }
                    else if(k>=48 && k<=57)
                        state=3;
                    else
                    {
                        res=false;
                        break;
                    }
                    break;
                case 3:
                    c= s.charAt(i);
                    k=c;
                    if((k>=65 &&k<=90) || (k>=97 && k<=122) || k==95)
                    {
                        state=2;
                    }
                    else if(k>=48 && k<=57)
                        state=3;
                    else
                    {
                        res =false;
                        break;
                    }
            }
            i++;
        }
        return res;
    }
    public  static int isNumber(String s)
    {
        int i,j,k,x,y,res,num,state;
        res=1;
        state =1;
        i=0;
        int c;

        while (i<s.length())
        {
            switch (state)
            {
                case 1:
                    c= s.charAt(i);
                    k= c;
                    i++;
                    if(k==48 && i<s.length())
                    {
                        int tempK= s.charAt(i);
                        if(tempK==120)
                        {
                            state=2;
                            res=3;
                        }
                            // hexadecimal
                        else if(tempK>=48 && tempK<=57)
                        {
                            res=2;
                            state=2; //octal
                        }
                        else if(tempK==46)
                        {
                            res=4;
                        }
                    }
                    else if(k>=49 && k<=57)
                        state=2;
                    else
                    {
                        res=-1;
                        break;
                    }
                    break;
                case 2:
                    c= s.charAt(i);
                    k=c;
                    if(k>=48 && k<=57)
                        state=2;
                    else if(k==46)
                    {
                        state=3;
                        if(res==1)
                            res=4;
                    }
                    else
                    {
                        res=-1;
                        break;
                    }
                    break;
                case 3:
                    c= s.charAt(i);
                    k=c;
                    if(k>=48 && k<=57)
                        state=3;
                    else if(k == 'e')
                    {
                        if(res==4)
                            state=4;
                        else
                            return  -1;
                    }
                    else
                    {
                        res=-1;
                        break;
                    }
                    break;
                case 4:
                    c=s.charAt(i);
                    k=c;
                    if(k>=48 && k<=57)
                    {
                        res=-1;
                        break;
                    }
            }
            i++;
        }
        return  res;
    }
}
