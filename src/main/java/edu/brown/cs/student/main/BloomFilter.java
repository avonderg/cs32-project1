package edu.brown.cs.student.main;

public interface BloomFilter<T> {
//    long[] array = new long[];
//    int size;
//    List<ToIntFunction<T>> hashFunctions;

    public String createBf(int r, int n);

    public String insertBf(T value);

    public String queryBf(T value);
}
