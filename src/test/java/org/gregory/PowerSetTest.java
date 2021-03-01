package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PowerSetTest {
    PowerSet set;

    @BeforeEach
    void setUp() {
        set = new PowerSet();
    }

    @AfterEach
    void tearDown() {
        set = null;
    }

    @Test
    void sizeRepeat() {
        set.put("1");
        set.put("2");
        set.put("2");
        set.put("3");
        set.put("2");
        assertThat(set.size(), is(3));
    }

    @Test
    void size() {
        set.put("1");
        set.put("2");
        set.put("3");
        set.put("4");
        set.put("5");
        assertThat(set.size(), is(5));
    }

    @Test
    void put() {
        set.put("1");
        set.put("2");
        set.put("2");
        set.put("3");
        set.put("2");
    }

    @Test
    void get() {
        set.put("1");
        set.put("2");
        set.put("2");
        set.put("3");
        set.put("2");
        assertThat(set.get("1"), is(true));
        assertThat(set.get("2"), is(true));
        assertThat(set.get("3"), is(true));
    }

    @Test
    void getNullSet() {
        assertThat(set.get("1"), is(false));
        assertThat(set.get("2"), is(false));
        assertThat(set.get("3"), is(false));
    }

    @Test
    void remove() {
        set.put("1");
        set.put("2");
        set.put("2");
        set.put("3");
        set.put("2");
        assertThat(set.remove("1"), is(true));
        assertThat(set.remove("2"), is(true));
        assertThat(set.remove("3"), is(true));

        assertThat(set.size(), is(0));
    }

    @Test
    void removeNullSet() {
        assertThat(set.remove("1"), is(false));
        assertThat(set.size(), is(0));
    }

    @Test
    void intersection() {
        set.put("3");
        set.put("1");
        set.put("5");
        set.put("2");
        set.put("4");

        PowerSet set2 = new PowerSet();
        set2.put("7");
        set2.put("2");
        set2.put("3");
        set2.put("4");

        PowerSet setExpected = new PowerSet();
        setExpected.put("2");
        setExpected.put("4");
        setExpected.put("3");

        assertThat(set.intersection(set2), is(setExpected));
    }

    @Test
    void intersectionNullSet() {
        PowerSet set2 = new PowerSet();
        set2.put("7");
        set2.put("2");
        set2.put("3");
        set2.put("4");

        PowerSet setExpected = new PowerSet();

        assertThat(set.intersection(set2), is(setExpected));
    }

    @Test
    void intersectionResultNullSet() {
        set.put("3");
        set.put("1");
        set.put("5");
        set.put("2");
        set.put("4");

        PowerSet set2 = new PowerSet();
        set2.put("7");
        set2.put("8");
        set2.put("9");
        set2.put("199");

        PowerSet setExpected = new PowerSet();

        assertThat(set.intersection(set2), is(setExpected));
    }

    @Test
    void union() {
        set.put("3");
        set.put("2");
        set.put("1");

        PowerSet set2 = new PowerSet();
        set2.put("3");
        set2.put("4");
        set2.put("3");
        set2.put("5");

        PowerSet setExpected = new PowerSet();
        setExpected.put("3");
        setExpected.put("1");
        setExpected.put("5");
        setExpected.put("2");
        setExpected.put("4");

        assertThat(set.union(set2), is(setExpected));
    }

    @Test
    void difference() {
        set.put("3");
        set.put("2");
        set.put("5");
        set.put("4");
        set.put("1");

        PowerSet set2 = new PowerSet();
        set2.put("4");
        set2.put("3");
        set2.put("2");

        PowerSet setExpected = new PowerSet();
        setExpected.put("1");
        setExpected.put("5");

        assertThat(set.difference(set2), is(setExpected));
    }

    @Test
    void isSubsetTrue() {
        set.put("1");
        set.put("2");
        set.put("3");
        set.put("4");
        set.put("5");

        PowerSet set2 = new PowerSet();
        set2.put("2");
        set2.put("3");
        set2.put("4");

        assertThat(set.isSubset(set2), is(true));
    }

    @Test
    void isSubsetFalse() {
        set.put("1");
        set.put("2");
        set.put("3");
        set.put("4");

        PowerSet powerSet = new PowerSet();
        powerSet.put("2");
        powerSet.put("7");

        set.isSubset(powerSet);
        assertThat(set.isSubset(powerSet), is(false));
    }

    //Проверить на нулевые множества
}