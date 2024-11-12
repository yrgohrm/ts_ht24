package se.yrgo;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void canLevelUpMax() {
        ItemContainer dummy = new ItemContainer() {
            public Item get(int index) {
                throw new UnsupportedOperationException();
            }

            public int put(Item i) {
                throw new UnsupportedOperationException();
            }

            public int size() {
                throw new UnsupportedOperationException();
            }
        };

        Player p = new Player(dummy, 40);
        assertThat(p.levelUp()).isEqualTo(40);
    }
}
