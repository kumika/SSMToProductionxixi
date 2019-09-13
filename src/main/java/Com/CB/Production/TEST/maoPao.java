package Com.CB.Production.TEST;

import com.sun.tools.hat.internal.util.ArraySorter;

import java.util.ArrayList;
import java.util.Arrays;

public class maoPao {

    public static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
       //减少的元素数量,是对比较范围的确定
        for (int i = 0; i < array.length;i++) {
            //第一次比较完毕后，下一次比较的时候就会减少一个元素的比较
            //比较大小的位置根据k 决定的，k最大范围是减少元素数量后的倒数2位，因为当k在最后一位的时候就无法比较
            for (int k =0; k < array.length - i - 1; k++) {
                if (array[k + 1] < array[k]) {
                    int temp = array[k + 1];
                    array[k + 1] = array[k];
                    array[k] = temp;
                }
            }
        }
        return array;
    }

    public static int[] selecttionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i= 0; i < array.length;i++) {
            int minIndex = i;
            for (int k = i ; k < array.length;k++) {
                if (array[k] < array[minIndex]) {//找到最小的值
                    minIndex = k;//将最小值的索引保存
                }
            }
            //将找到的最小值和i位置所在的值进行交换
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertTionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current;
        //为什么需要-1呢？
        //因为变量 i 是 从数组第1个元素到倒数第2个元素的范围，是交换对象
        for (int i=0;i< array.length - 1;i++) {
            //移动或者交换的基准点（被交换对象），是从数组的1位置开始的，不是0位置
            current = array[i + 1];
            //交换的对象
            int preIndex = i;
            //前面的判断是作用在第一次比较交换，后面的判断是进行判断是否需要移动或者停止这轮交换
            //当基准点大于交换对象的时候，就是停止本轮交换的时刻。
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            //这就是冒泡排序最后的array[i] = temp，放在第三方的数据拿回来
            //就是将冒泡排序的交换3部曲使用判断分开了
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     *  希尔排序
     * @param array
     * @return
     */
    public static int[] ShellSort(int[] array) {

        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i=gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    /**
     * 归并排序
     * @param array
     * @return
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        return merge(MergeSort(left), MergeSort(right));
    }

    /**
     *  归并排序----将2段排序好的数组结合成一个排序数组
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] endNum = new int[left.length + right.length];
        //递归是2叉树的思想体现，这里体现了左右子树的分的作用
        //2个参数都是排列好的数组，只需要考虑边界问题
        for (int index = 0, i = 0,k = 0;index < endNum.length;index++) {
            if (i >= left.length)
                //当序号i 超越左数组序号范围最大值，右数组的第一个元素开始赋值到支付宝（endNum数组）
                endNum[index] = right[k++];
            else if (k >= right.length)
                //当序号k 超越右数组序号范围最大值，左数组的第一个元素开始赋值到支付宝（endNum数组）
                endNum[index] = left[i++];
            else if (left[i] > right[k])//个人认为的唯一的排序判断
                // 当左数组 》右数组，右数组赋值到支付宝（endNum数组）
                endNum[index] = right[k++];
            else
                //左数组从序号0开始，赋值到支付宝（endNum数组）上
                endNum[index] = left[i++];
        }
        return endNum;
    }

    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] array, int start, int end) {

        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        //进行分区操作，返回基准值
        int smallIndex = partition(array,start,end);

        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);

        if (smallIndex < end)
            QuickSort(array,smallIndex + 1,end);

        return array;
    }

    /**
     * 快速排序算法---partition----就是分区操作
     * @param array
     * @param start
     * @param end
     * @return
     */
    public  static int partition(int[] array, int start,int end) {
        //从数列中挑出一个元素，称为 “基准”（pivot）
        int pivot = (int) (start + Math.random() * (end - start + 1));
        //
        int smallIndex = start - 1;
        //根据基准数和 最后序号进行交换
        swap(array, pivot, end);
        //
        for (int i = start; i <= end; i++) {
            //从0开始与基准值进行比较，比基准值小，就不动，因为这个算法是从左到右的，不是2边同时判断的
            if (array[i] <= array[end]) {
                //发生交换的位置，同时也是记录基准值最后的位置。因为最后发生交换的位置是大概在中间位置的。
                smallIndex++;
                //判断 2个 交换对象 的位置，因为这算法要求输出是小的在左，大的在右
                if (i > smallIndex)
                    //进行交换元素
                    swap(array, i, smallIndex);
            }
        }
        return smallIndex;
    }

    /**
     * 交换数组内2个元素
     * @param array
     * @param i
     * @param k
     */
    public static void swap(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }




    //声明全局变量，用于记录数组array的长度
    static int len;

    /**
     *  堆排序算法
     * @param array
     * @return
     */
    public static int[] HeapSort(int[] array) {

        len = array.length;
        if (len < 1) return array;

        //1. 构建一个最大堆
        buildMaxHeap(array);

        //2. 循环将堆首位（最大值）与末位交换，然后再重新调整最大堆
        while (len > 0) {
            swap(array,0,len - 1);
            //再完成交换后，最大值就退出交换操作
            len--;
            //因为交换后，此时堆首位不是最大值，所以堆结构需要调整到符合规则的顺序
            adjustHeap(array, 0);
        }
        return array;
    }

    /**
     *  建立最大堆
     * @param array
     */
    public static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    /**
     * 调整堆结构，成为最大堆结构
     * @param array
     * @param i
     */
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，而且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex])
            maxIndex = i * 2;
        //如果有右子树，而且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2  + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }


    public static int[] CountingSort(int[] array) {

        if (array.length == 0) return array;

        int bias, min = array[0], max = array[0];

        //找出输入数组A中的最大最小值
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];

            if (array[i] < min) min = array[i];
        }
        //目的是为了辅助数组的下标从0开始，不然就是min的值开始
        bias = 0 - min;

        //辅助计数数组B ，长度是根据输入数组A的最大值 - 最小值 + 1
        int[] bucket = new int[max - min + 1];

        //辅助数组B全部填充数值0
        Arrays.fill(bucket, 0);

        //记录每个数字出现的次数。辅助数组的下标  等于 输入数组的值
        for (int i = 0; i < array.length; i++) {
            // 因为辅助数组B的下标是从输入数组A的最小数值开始，到最大数值结束
            //比如输入数组最大值120，最小值3， 辅助数组的下标起始值就是3，终结值120，
            //但是，这里为了方便，辅助数组的下标起始值要从0开始，所以就要减去输入数组的最小值，
            //所以辅助数组的下标的最大值，则是输入数组最大值减去最小值
            bucket[array[i] + bias]++;
        }
        //index是作为输入数组的下标，i是辅助数组的下标
        int index = 0, i = 0;
        //辅助数组输出元素，就是从桶拿出元素
        while (index < array.length) {
            //判断辅助数组是否记录了元素
            if (bucket[i] != 0) {
                //根据辅助数组下标还原输入数组的值
                array[index] = i - bias;
                //输出了一个元素，辅助数组的该元素计数减一
                bucket[i]--;
                //i并没有更新，可以多次输出辅助数组的一个元素
                //index是输出数组的下标，需要更新
                index++;
            } else
                i++;
        }
        return array;
    }

    public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
        //bucketSize是代表需要按 bucketSize个分桶，进行桶排序

        //人为设置一个BucketSize，作为每个桶所能放置多少个不同数值
        // （例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）

        ArrayList<Integer> resultArr = new ArrayList<>();

        if (array == null || array.size() < 2) return array;

        int max = array.get(0), min = array.get(0);

        //找到最大值和最小值
        for(int i =0; i < array.size(); i++) {
            if (array.get(i) > max) max = array.get(i);

            if (array.get(i) < min) min = array.get(i);
        }

        //桶数
        int buckCount = (max - min) /  bucketSize + 1;
        //存放桶的列表
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(buckCount);

        //对桶内元素进行填充 0
        for (int i = 0; i < buckCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将每个元素放入桶
        for (int i = 0; i < array.size(); i++) {
            //桶的数量
            int num = (array.get(i) - min) / bucketSize;
            //将输入元素放入到数组上
            bucketArr.get(num).add(array.get(i));
        }

        //对每个桶进行排序
        for (int i = 0; i < buckCount; i++) {
            //当桶数 = 1 ，则减少桶内的元素数量
            if (buckCount == 1) bucketSize--;
            //使用递归，把每一个桶的元素数量分治到1，然后递归返回，只有1个元素数量的桶的值，赋值到temp数组上，完成排序
            ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
            //根据temp数组上的元素，完成各个桶之间的排序合并，最后返回到输出数组上
            for (int k = 0; k < temp.size(); k++) {
                resultArr.add(temp.get(k));
            }
        }
        return resultArr;
    }



/*
    //使用插入排序的桶排序
    function bucketSort(arr, bucketSize) {
        if (arr.length == = 0) {
            return arr;
        }

        var i;
        var minValue = arr[0];
        var maxValue = arr[0];
        for (i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];                // 输入数据的最小值
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];                // 输入数据的最大值
            }
        }

        // 桶的初始化
        var DEFAULT_BUCKET_SIZE = 5;            // 设置桶的默认数量为5
        bucketSize = bucketSize || DEFAULT_BUCKET_SIZE;
        var bucketCount = Math.floor((maxValue - minValue) / bucketSize) + 1;
        var buckets = new Array(bucketCount);
        for (i = 0; i < buckets.length; i++) {
            buckets[i] = [];
        }

        // 利用映射函数将数据分配到各个桶中
        for (i = 0; i < arr.length; i++) {
            buckets[Math.floor((arr[i] - minValue) / bucketSize)].push(arr[i]);
        }

        arr.length = 0;
        for (i = 0; i < buckets.length; i++) {
            insertionSort(buckets[i]);                      // 对每个桶进行排序，这里使用了插入排序
            for (var j = 0; j < buckets[i].length; j++) {
                arr.push(buckets[i][j]);
            }
        }

        return arr;
    }*/


    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        // 1.先算出最大数的位数；
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int div = 1;
        //存放桶的列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        //对桶内元素进行填充 0
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());
        //2.进行maxDigit趟分配
        //将每个元素放入桶
        for (int i = 0; i < maxDigit; i++,div *= 10) {
            for (int j = 0; j < array.length; j++) {
                //桶的数量
                int num = (array[j] / div) % 10;
                //将输入元素放入到数组上
                bucketList.get(num).add(array[j]);
            }
            //3.收集
            //从临时数组上拿出元素，存储到array（输出数组）上
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }
        }
        return array;
    }


    public static void main(String[] args) {

        ArrayList<Integer> AFF = new ArrayList<>();
        AFF.add(120);
        AFF.add(9);
        AFF.add(38);
        AFF.add(72);
        AFF.add(63);
        AFF.add(35);
        AFF.add(4);
        AFF.add(3);
        AFF.add(23);

        ArrayList<Integer> BFF = new ArrayList<>();

        int[] A = {120,9,38,72,63,35,4,3,23};
        int[] B = null;

        long startTime = System.currentTimeMillis();
        //希尔排序
        //B = ShellSort(A);

        //冒泡排序
        //B = bubbleSort(A);

        //插入排序
        //B = insertTionSort(A);

        //归并排序
        //B = MergeSort(A);

        //快速排序
        //B = QuickSort(A,0,8);

        //堆排序
        //B = HeapSort(A);

        //计数排序
        //B = CountingSort(A);

        //桶排序
        //BFF = BucketSort(AFF,3);


        //基数排序
        B = RadixSort(A);

        long endTime = System.currentTimeMillis();

        for (int i =0;i < B.length;i++) {
            System.out.print(B[i] + ",");
        }
        System.out.println("用时: "+ (endTime-startTime) +"毫秒");
        System.out.println(" ");
    }
}
