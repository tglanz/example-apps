#include <stdio.h>

int main()
{
  int a[] = {0, 7, 3, 23, 8, 7, 5};
  int i, j, did_found = 0;
  
  for (i = 0; i < 7 && !did_found; i++)
  {
    for (j = i + 1; j < 7 && !did_found; j++)
    {
      if (a[i] == a[j])
      {
        did_found = 1;
      }
    }
  }

  printf("Found at least two equal numbers: %d", did_found);
}