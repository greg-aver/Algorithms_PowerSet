package org.gregory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PowerSet {
    private LinkedList<String> set;

    public PowerSet() {
        set = new LinkedList<>();
    }

    public int size() {
        return getSet().size();
    }


    public void put(String value) {
        if (!get(value)) {
            getSet().add(value);
            Collections.sort(getSet());
        }
    }

    public boolean get(String value) {
        return getSet().contains(value);
    }

    public boolean remove(String value) {
        return getSet().remove(value);
    }

    public PowerSet intersection(PowerSet set2) {
        PowerSet resultSet = new PowerSet();
        resultSet.setSet(this.getSet()
                .stream()
                .filter(set2.getSet()::contains)
                .collect(Collectors.toCollection(LinkedList::new)));
        Collections.sort(resultSet.getSet());
        return resultSet;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet resultSet = new PowerSet();
        resultSet.setSet(Stream.concat(this.getSet().stream(), set2.getSet().stream())
                .distinct()
                .collect(Collectors.toCollection(LinkedList::new)));
        Collections.sort(resultSet.getSet());
        return resultSet;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet resultSet = new PowerSet();
        resultSet.setSet(this.getSet()
                .stream()
                .filter(s -> !set2.getSet().contains(s))
                .collect(Collectors.toCollection(LinkedList::new)));
        Collections.sort(resultSet.getSet());
        return resultSet;
    }

    public boolean isSubset(PowerSet set2) {
        return set2.getSet().stream()
                .filter(this.getSet()::contains)
                .collect(Collectors.toCollection(LinkedList::new))
                .equals(set2.getSet());
    }


    public LinkedList<String> getSet() {
        return set;
    }

    private void setSet(LinkedList<String> set) {
        this.set = set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PowerSet powerSet = (PowerSet) o;

        return Objects.equals(set, powerSet.set);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Set = ");
        ListIterator iterator = this.getSet().listIterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }
}