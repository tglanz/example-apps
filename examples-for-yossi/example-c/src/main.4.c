#include <stdio.h>

int main()
{
    int arr[5][5] = {{1, 1, 2, 6, 0},
                     {2, 3, 3, 7, 9},
                     {3, 4, 4, 8, 8},
                     {7, 5, 3, 9, 7},
                     {1, 6, 7, 6, 5}};

    int i, j, num1;
    int counter = 0;
    printf("choose the num ");
    scanf("%d", &num1);

    int n1 = num1 / 10;
    int n2 = num1 % 10;

    for (i = 0; i < 5; i++)
    {
        for (j = 0; j < 5; j++)
        {
            if (arr[i][j] == n1 && arr[i + 1][j] == n2)
            {
                counter++;
            }
        }

        if (arr[i][j] == n1 && arr[i][j + 1] == n2)
        {
            counter++;
        }
    }

    printf("the num its appears is %d", counter);
}
