package com.franklee.algs4.ch3;

import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> {
    private TreeMap<Key, Value> st;

    public ST() { }

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
}
