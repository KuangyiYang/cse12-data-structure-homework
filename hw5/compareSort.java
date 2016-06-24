import java.util.Random;

public class HelloWorld {

  // Insertion Sort
  public static void insertionSort(int[] array) {
    insertionSortHelper(array, 1);
  }

  private static void insertionSortHelper(int[] array, int index) {
    if (index >= array.length) return;
    int i, temp = array[index];
    for (i = index; i > 0; i--) {
      if (array[i - 1] > temp) array[i] = array[i - 1];
      else break;
    }
    array[i] = temp;
    insertionSortHelper(array, index + 1);
  }

  // Merge Sort  
  public static void mergeSort(int[] array) {
    if (array.length <= 0) return;
    mergeSortHelper1(array, 0, array.length - 1);
  }

  public static void mergeSortHelper1(int[] array, int start, int end) {
    int mid = (start + end + 1) / 2;
    if (mid - start - 1 > 0) {
      mergeSortHelper1(array, start, mid - 1);
    }
    if (end - mid > 0) {
      mergeSortHelper1(array, mid, end);
    }
    mergeSortHelper2(array, start, mid, end);
  }

  public static void mergeSortHelper2(int[] array, int start, int mid, int end) {
    int[] array1 = new int[mid - start];
    int[] array2 = new int[end - mid + 1];
    for (int i = 0; i < array1.length; i++) {
      array1[i] = array[start + i];
    }
    for (int i = 0; i < array2.length; i++) {
      array2[i] = array[mid + i];
    }
    int j = 0, k = 0;
    for (int i = start; i <= end; i++) {
      if (j == array1.length) {
        array[i] = array2[k];
        k++;
      } else if (k == array2.length) {
        array[i] = array1[j];
        j++;
      } else if (array1[j] <= array2[k]) {
        array[i] = array1[j];
        j++;
      } else {
        array[i] = array2[k];
        k++;
      }
    }
  }

  // Quick Sort
  public static void quickSort(int[] array) {
    if (array.length <= 0) return;
    quickSortHelper1(array, 0, array.length - 1);
  }

  public static void quickSortHelper1(int[] array, int start, int end) {
    if (start == end) return;
    Random random = new Random();
    int index = start + random.nextInt(end - start + 1);
    int temp = array[end];
    array[end] = array[index];
    array[index] = temp;
    int i = start - 1;
    for (int j = start; j < end; j++) {
      if (array[j] <= array[end]) {
        i++;
        int tmp1 = array[i];
        array[i] = array[j];
        array[j] = tmp1;        
      }
    }
    int tmp2 = array[i + 1];
    array[i + 1] = array[end];
    array[end]= tmp2;
    if (i > start) {
      quickSortHelper1(array, start, i);
    }
    if (i + 2 < end)
      quickSortHelper1(array, i + 2, end);
  }

  // Merge Sort incorporating Insertion
  public static void mergeSort_insertion(int[] array) {
    mergeSortHelper3(array, 0, array.length - 1);
  }

  public static void mergeSortHelper3(int[] array, int start, int end) {
    int mid = (start + end + 1) / 2;
    if (mid - start - 1 > 10) {
      mergeSortHelper1(array, start, mid - 1);
    } else if (mid - start - 1 > 0) {
      int[] temp = new int[mid - start];
      for (int i = 0; i < temp.length; i++) {
        temp[i] = array[start + i];
      }
      insertionSort(temp);
      for (int i = 0; i < temp.length; i++) {
        array[start + i] = temp[i];
      }
    }
    if (end - mid > 10) {
      mergeSortHelper1(array, mid, end);
    } else if (end - mid > 0) {
      int[] temp = new int[end - mid + 1];
      for (int i = 0; i < temp.length; i++) {
        temp[i] = array[mid + i];
      }
      insertionSort(temp);
      for (int i = 0; i < temp.length; i++) {
        array[mid + i] = temp[i];
      }
    }
    mergeSortHelper2(array, start, mid, end);
  }

  // Quick Sort incorporating Insertion
  public static void quickSort_insertion(int[] array) {
    quickSortHelper2(array, 0, array.length - 1);
  }

  public static void quickSortHelper2(int[] array, int start, int end) {
    if (end - start > 10) {
      int i = start - 1;
      for (int j = start; j < end; j++) {
        if (array[j] <= array[end]) {
          i++;
          int tmp1 = array[i];
          array[i] = array[j];
          array[j] = tmp1;        
        }
      }
      int tmp2 = array[i + 1];
      array[i + 1] = array[end];
      array[end]= tmp2;
      if (i > start) {
        quickSortHelper2(array, start, i);
      }
      if (i + 2 < end)
        quickSortHelper2(array, i + 2, end);
    } else if (end - start > 0) {
      int[] temp = new int[end - start + 1];
      for (int i = 0; i < temp.length; i++) {
        temp[i] = array[start + i];
      }
      insertionSort(temp);
      for (int i = 0; i < temp.length; i++) {
        array[start + i] = temp[i];
      }
    }
  }

  // 4-way Merge Sort
  public static void mergeSort_4way(int[] array) {
    mergeSortHelper4(array, 0, array.length - 1);
  }

  public static void mergeSortHelper4(int[] array, int start, int end) {
    int quartile2 = (start + end + 1) / 2;
    int quartile1 = (start + quartile2) / 2;
    int quartile3 = (quartile2 + end + 1) / 2;
    if (quartile1 - start - 1 > 0) {
      mergeSortHelper4(array, start, quartile1 - 1);
    }
    if (quartile2 - quartile1 - 1 > 0) {
      mergeSortHelper4(array, quartile1, quartile2 - 1);
    }
    if (quartile3 - quartile2 - 1 > 0) {
      mergeSortHelper4(array, quartile2, quartile3 - 1);
    }
    if (end - quartile3 > 0) {
      mergeSortHelper4(array, quartile3, end);
    }
    mergeSortHelper2(array, start, quartile1, quartile2 - 1);
    mergeSortHelper2(array, quartile2, quartile3, end);
    mergeSortHelper2(array, start, quartile2, end);
  }

  public static void main(String[] args) {
	long[] r = new long[30];
	int j = 0;
    for (int i = 500; i <= 5000; i += 500) {
      long[] result = TestReverse(i);
      r[j] = result[0];
      r[10 + j] = result[1];
      r[20 + j] = result[2];
      j++;
//      System.out.println();
//      TestAlmostSorted(i);
//      System.out.println();
//      TestDuplicates(i);
//      System.out.println();
//      TestReverse(i);
    }
    for (int i = 0; i < 30; i++) {
    	System.out.print(r[i] + ",");
    	if (i == 9 || i == 19) System.out.println();
    }
  }

  private static long[] TestRandom(int N) {
    int[] array1;
    int[] array2;
    int[] array3;
    long time1 = 0;
    long time2 = 0;
    long time3 = 0;
    Random random = new Random();

    for (int i = 0; i < 11; i++) {
      array1 = new int[N];
      array2 = new int[N];
      array3 = new int[N];

      for (int j = 0; j < N; j++) {
        array1[j] = random.nextInt();
        array2[j] = array1[j];
        array3[j] = array1[j];
      }

      long start1 = System.nanoTime();
      insertionSort(array1);
      long end1 = System.nanoTime();
      if (i != 0) {
        time1 += (end1 - start1);
        // System.out.println("Insertion Sort " + i + ": "+ (end1 - start1));
      }

      long start2 = System.nanoTime();
      mergeSort(array2);
      long end2 = System.nanoTime();
      if (i != 0) {
        time2 += (end2 - start2);
        // System.out.println("Merge Sort " + i + ": " + (end2 - start2));
      }

      long start3 = System.nanoTime();
      quickSort(array3);
      long end3 = System.nanoTime();  
      if (i != 0) {
        time3 += (end3 - start3);
        // System.out.println("Quick Sort " + i + ": " + (end3 - start3));
      }
    }
    long[] result = new long[3];
    result[0] = time1 / 10;
    result[1] = time2 / 10;
    result[2] = time3 / 10;
    return result;
  }

  private static long[] TestAlmostSorted(int N) {
    int[] array1;
    int[] array2;
    int[] array3;
    long time1 = 0;
    long time2 = 0;
    long time3 = 0;
    Random random = new Random();

    for (int i = 0; i < 11; i++) {
      array1 = new int[N];
      array2 = new int[N];
      array3 = new int[N];

      for (int j = 0; j < N; j++) {
        array1[j] = j;
      }

      for (int j = 0; j < N * 0.1; j++) {
        int index = random.nextInt(N);
        int temp = array1[0];
        array1[0] = array1[index];
        array1[index] = temp;
      }

      for (int j = 0; j < N; j++) {
        array2[j] = array1[j];
        array3[j] = array1[j];
      }

      long start1 = System.nanoTime();
      insertionSort(array1);
      long end1 = System.nanoTime();
      if (i != 0) {
        time1 += (end1 - start1);
        // System.out.println("Insertion Sort " + i + ": "+ (end1 - start1));
      }

      long start2 = System.nanoTime();
      mergeSort(array2);
      long end2 = System.nanoTime();
      if (i != 0) {
        time2 += (end2 - start2);
        // System.out.println("Merge Sort " + i + ": " + (end2 - start2));
      }

      long start3 = System.nanoTime();
      quickSort(array3);
      long end3 = System.nanoTime();  
      if (i != 0) {
        time3 += (end3 - start3);
        // System.out.println("Quick Sort " + i + ": " + (end3 - start3));
      }
    }
    long[] result = new long[3];
    result[0] = time1 / 10;
    result[1] = time2 / 10;
    result[2] = time3 / 10;
    return result;
  }

  private static long[] TestDuplicates(int N) {
    int[] array1;
    int[] array2;
    int[] array3;
    long time1 = 0;
    long time2 = 0;
    long time3 = 0;
    Random random = new Random();

    for (int i = 0; i < 11; i++) {
      array1 = new int[N];
      array2 = new int[N];
      array3 = new int[N];

      for (int j = 0; j < N; j++) {
        array1[j] = random.nextInt();
      }

      int count = (int) (N * 0.8);
      while (count != 0) {
        int index = random.nextInt(N);
        if (array1[index] != array1[0]) {
          array1[index] = array1[0];
          count--;
        }
      }

      for (int j = 0; j < N; j++) {
        array2[j] = array1[j];
        array3[j] = array1[j];
      }

      long start1 = System.nanoTime();
      insertionSort(array1);
      long end1 = System.nanoTime();
      if (i != 0) {
        time1 += (end1 - start1);
        // System.out.println("Insertion Sort " + i + ": "+ (end1 - start1));
      }

      long start2 = System.nanoTime();
      mergeSort(array2);
      long end2 = System.nanoTime();
      if (i != 0) {
        time2 += (end2 - start2);
        // System.out.println("Merge Sort " + i + ": " + (end2 - start2));
      }

      long start3 = System.nanoTime();
      quickSort(array3);
      long end3 = System.nanoTime();
      if (i != 0) {
        time3 += (end3 - start3);
        // System.out.println("Quick Sort " + i + ": " + (end3 - start3));
      }
    }
    long[] result = new long[3];
    result[0] = time1 / 10;
    result[1] = time2 / 10;
    result[2] = time3 / 10;
    return result;
  }

  private static long[] TestReverse(int N) {
    int[] array1;
    int[] array2;
    int[] array3;
    long time1 = 0;
    long time2 = 0;
    long time3 = 0;

    for (int i = 0; i < 11; i++) {
      array1 = new int[N];
      array2 = new int[N];
      array3 = new int[N];

      for (int j = 0; j < N; j++) {
        array1[j] = N - j;
      }

      for (int j = 0; j < N; j++) {
        array2[j] = array1[j];
        array3[j] = array1[j];
      }

      long start1 = System.nanoTime();
      insertionSort(array1);
      long end1 = System.nanoTime();
      if (i != 0) {
        time1 += (end1 - start1);
        // System.out.println("Insertion Sort " + i + ": "+ (end1 - start1));
      }

      long start2 = System.nanoTime();
      mergeSort(array2);
      long end2 = System.nanoTime();
      if (i != 0) {
        time2 += (end2 - start2);
        // System.out.println("Merge Sort " + i + ": " + (end2 - start2));
      }

      long start3 = System.nanoTime();
      quickSort(array3);
      long end3 = System.nanoTime();
      if (i != 0) {
        time3 += (end3 - start3);
        // System.out.println("Quick Sort " + i + ": " + (end3 - start3));
      }
    }
    long[] result = new long[3];
    result[0] = time1 / 10;
    result[1] = time2 / 10;
    result[2] = time3 / 10;
    return result;
  }
}




