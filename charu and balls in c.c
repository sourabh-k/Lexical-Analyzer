#include<stdio.h>
int main()
{
    long long int t,n,i,j,k;
    scanf("%lli",&t);
    while(t)
    {
        scanf("%lli",&n);
        long long int a[n];
        long long int odd[n],even[n],sum;
        j=k=0;
        sum=0;
        for(i=0;i<n;i++)
        {
           scanf("%lli",&a[i]);
            if(i!=0)
            {
                if(a[i]<a[i-1])
                {
                    sum+=a[i-1]-a[i];
                    a[i]=a[i-1];
                }
            }
            if(a[i]%2==0)
            {
                even[j]=i;
                j++;
            }
            else
            {
                odd[k]=i;
                k++;
            }
        }

        if(j==n)
        {
           printf("%lli\n",sum);
        }
        else
        {
            if(a[0]==a[n-1] && a[0]==1)
            {
                sum+=n;
            }
            else if(a[0]==a[n-1])
            {
                ;
            }
            else
            {
                long long int d,c,small,coun,e;
                small=0;
                if(a[0]==1)
                   {
                       sum++;
                       a[0]=2;
                   }

                e=a[0]/2;
                for(c=2;c<=e || c==a[0];)
                {
                  coun=0;
                  for(i=0;i<n;i++)
                  {
                     d=a[i]%c;
                     if(d!=0)
                     {
                         coun+=(c-d);
                     }
                  }
                  if(coun==0)
                  {
                      small=0;
                      break;
                  }
                  else if(coun<small || small==0)
                  {
                      small=coun;
                  }
                  if(c==e)
                    c=a[0];
                  else
                    c++;
                }
                sum+=small;
            }
              printf("%lli\n",sum);
        }

       t--;

    }
    return 0;
}
