package se.yrgo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.NotEmpty;

class UtilsTest {
    @Test
    void testReverseEmpty() {
        final List<Integer> list1 = List.of();
        final List<Integer> list2 = new ArrayList<>(list1);

        Utils.inplaceReverse(list2);
        assertThat(list2).isEqualTo(list1);
    }

    @Test
    void testReverseSizeOne() {
        final List<Integer> list1 = List.of(1);
        final List<Integer> list2 = new ArrayList<>(list1);

        Utils.inplaceReverse(list2);
        assertThat(list2).isEqualTo(list1);
    }

    @Test
    void testReverseBigOne() {
        final int size = 3333;
        final List<Integer> list1 = new ArrayList<>(size);
        final List<Integer> list2 = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list1.add(i);
            list2.add(0, i);
        }

        Utils.inplaceReverse(list2);
        assertThat(list2).isEqualTo(list1);
    }

    @Test
    void testSomewhatBigOne() {
        final int size = 555;
        final List<String> list1 = new ArrayList<>(size);
        final List<String> list2 = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            String val = Integer.toString(i);
            list1.add(val);
            list2.add(0, val);
        }

        Utils.inplaceReverse(list2);
        assertThat(list2).isEqualTo(list1);
    }

    @Test
    void testNullList() {
        Utils.inplaceReverse(null);
    }

    @Property
    <T> boolean twiceReverseIsSame(@ForAll List<T> list) {
        List<T> copy = new ArrayList<T>(list);
        Utils.inplaceReverse(copy);
        Utils.inplaceReverse(copy);
        return copy.equals(list);
    }

    @Property
    <T> boolean headBecomesTail(@ForAll @NotEmpty List<T> list) {
        T head = list.get(0);
        Utils.inplaceReverse(list);
        return head == list.get(list.size() - 1);
    }

    @Property
    boolean trimJustWorks(@ForAll @NotEmpty String data) {
        return data.trim().equals(data.trim().trim());
    }
}
