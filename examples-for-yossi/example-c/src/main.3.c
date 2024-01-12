#include <stdio.h>

int main()
{
    int N = 3;
    int arr[3][3] = {{1, 2, 5},
                     {4, 5, 6},
                     {12, 5, 3}};
    int i, j, sum;
    for (i = 0; i < N; ++i)
    {
        sum = 0;
        for (j = 0; j < N; ++j)
        {
            sum += arr[i][j];
        }

        printf("the sum of column %d is :%d\n", i, sum);
    }

    for (i = 0; i < N; ++i)
    {
        sum = 0;
        for (j = 0; j < N; ++j)
        {
            sum += arr[j][i];
        }

        printf("the sum of row %d is: %d\n", j, sum);
    }
}