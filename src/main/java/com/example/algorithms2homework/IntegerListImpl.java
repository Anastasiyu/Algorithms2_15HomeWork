package com.example.algorithms2homework;

public class IntegerListImpl implements IntegerList {
    private static final int DEFAULT_SIZE = 15;

    private Integer[] data;
    private int capacity;

    public IntegerListImpl() {
        this(DEFAULT_SIZE);
    }

    public IntegerListImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер списка не должен быть меньше 0!");
        }
        data = new Integer[DEFAULT_SIZE];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        // 4 5 6 2 null null null capacity = 4
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        // 4 5 6 2 null null null capacity= 4, index = 2
        // 4 5 6 6 2 null null
        // 4 5 item 62 null null
        checkItem(item);
        checkIndex(index, true);
        if (capacity >= data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, capacity - index); // 1 шаг копируем значение на ячейку дальше

        capacity++; // увеличить
        return data[index] = item; //добавить элемент
    }

    private void grow() {     // увеличение массива в 1,5 раза
        Integer[] newData = new Integer[(int) (data.length * 1.50)];
        System.arraycopy(data, 0, newData, 0, capacity);
        this.data = newData;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index, false);

        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        // 4 5 6 2 null null null capacity= 4, index = 2
        // 4 5 6 6 2 null null
        // 4 5 item 62 null null

        return remove(indexOf(item));
    }

    @Override
    public Integer remove(int index) {
        // 4 5 6 2  null null null capacity= 4, index = 2
        // 4 6 2 2 null null null
        // 4 6 2  null null null null

        // 4 5 6 2 7 null null index = 4, capacity = 5

        checkIndex(index, false);
        Integer item = data[index];
        if (index + 1 < capacity) { // проверить если элемент последний то удалить без здвига
            System.arraycopy(data, index + 1, data, index, capacity - index - 1); // 1 шаг копируем значение на ячейку  в перед
        }
        data[--capacity] = null;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        Integer[] arrayForSearch = toArray();

        mergeSort(arrayForSearch);

        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < capacity; i++) {
            if (item.equals(data[i])) {

                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = capacity - 1; i >= 0; i--) {
            if (item.equals(data[i])) {

                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index, false);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!get(i).equals(otherList.get(i))) {

                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] copy = new Integer[capacity];
        System.arraycopy(data, 0, copy, 0, capacity);
        return copy;
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Список не может содержать null");
        }
    }

    private void checkIndex(int index, boolean include) {
        // if (index < 0 || (include ? index >= capacity : index > capacity))
        boolean condition;
        if (include) {
            condition = index > capacity;
        } else {
            condition = index >= capacity;
        }
        if (index < 0 || condition) {
            throw new IllegalArgumentException("Index должен быть в диапозоне от 0 до размера списка");
        }
    }

    private static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        Integer mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

       System.arraycopy(arr, 0, left, 0, right.length);
       System.arraycopy(arr, mid, right, 0, right.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }
    private static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

    // private static void sortInsertion(Integer[] arr){               // вставками
     //   for (int i = 0; i < arr.length; i++) {
       //     int temp = arr[i];
        //    int j=i;
         //   while (j>0 && arr[j-1]>= temp){
          //      arr[j]= arr[j -1];
          //      j--;
         //   }
         //   arr[j] = temp;
       // }
   // }




}