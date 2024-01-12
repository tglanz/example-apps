#include <stdio.h>
#include <math.h>

int main()
{
    int num, digit, sum, lassdigit;
    int R = -1;
    int counter = 0;
    printf("enter a num ");
    scanf("%d", &num);
    printf("enter a sam digit:");
    scanf("%d", &digit);
    while (num > 0)
    {
        lassdigit = num % 10;
        num = num / 10;
        if (digit == lassdigit)
        {
            printf("%d", R);
            break;
        }
        if (!(digit == lassdigit))
        {
            sum = sum + lassdigit * (pow(10, counter));
            counter++;
        }
        
        printf(" %d", sum);
    }
}