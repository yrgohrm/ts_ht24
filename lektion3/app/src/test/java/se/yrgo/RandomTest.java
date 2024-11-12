package se.yrgo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

public class RandomTest {
    private static class BurlapSack implements ItemContainer {
        private int size = 0;
        public Item get(int index) {
            size--;
            return null;
        }

        public int put(Item i) {
            size++;
            return 0;
        }

        public int size() {
            return size;
        }
    }

    private static class Apple implements Item {

    }

    @Test
    void testLootGetsItem() {
        ItemContainer container = new BurlapSack();
        RandomProvider randp = new RandomProvider() {
            public Item getItem() {
                return new Apple();
            }
        };

        Player2 p = new Player2(container, randp);
        p.lootBox();
        assertThat(container.size()).isEqualTo(1);
    }

    @Test
    void testLootGetsItem2() {
        ItemContainer container = new BurlapSack();

        // create concrete objects from the given classes
        RandomProvider randp = mock(RandomProvider.class);
        Item item = mock(Item.class);

        // programatically describe behaviour
        when(randp.getItem()).thenReturn(item);

        Player2 p = new Player2(container, randp);
        p.lootBox();
        assertThat(container.size()).isEqualTo(1);

        // make sure it got called exactly once
        verify(randp).getItem();
    }
}
