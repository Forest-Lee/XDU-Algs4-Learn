package com.franklee.algs4.ch3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.TreeMap;

/**
 * The {@code ST} class represents an ordered symbol table of generic key-value pairs.
 * @param <Key> the generic type of keys in this symbol table
 * @param <Value> the generic type of values in this symbol table
 */
public class ST<Key extends Comparable<Key>, Value> {
    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<Key, Value>();
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        st.remove(key);
    }

    public void remove(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to remove() is null");
        st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return st.containsKey(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public Key min() {
        if (isEmpty()) return null;
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) return null;
        return st.lastKey();
    }

    // returns the smallest key in st greater than or equal to key
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        Key k = st.ceilingKey(key);
        if (k == null) throw new IllegalArgumentException("all keys are less than " + key);
        return k;
    }

    // returns the largest key in st less than or equal to key
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        Key k = st.floorKey(key);
        if (k == null) throw new IllegalArgumentException("all keys are greater than " + key);
        return k;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
