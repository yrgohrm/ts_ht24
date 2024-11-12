package se.yrgo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Random;
import java.util.random.*;

import org.junit.jupiter.api.Test;

public class AnotherRandomTest {
    @Test
    void someRandomTest() {
        RandomGenerator rand = mock(RandomGenerator.class);

        when(rand.nextInt(anyInt())).thenReturn(0, 0, 1, 2, 3, 4, 5);
        when(rand.nextInt(10)).thenReturn(5, 6, 7, 8);
        when(rand.nextInt(intThat(n -> n > 1000))).thenReturn(500, 501);

        for (int i = 0; i < 10; ++i) {
            System.out.println(rand.nextInt(20));
            System.out.println(rand.nextInt(10));
            System.out.println(rand.nextInt(10000));
        }

        // Random rand = mock(Random.class);

        // when(rand.nextInt(anyInt())).thenReturn(0);







        // when(rand.nextInt(10)).thenReturn(5);
        // when(rand.nextInt(intThat(n -> n > 1000))).thenReturn(500);

        // assertThat(rand.nextInt(10)).isEqualTo(5);
        // assertThat(rand.nextInt(2000)).isEqualTo(500);
        // assertThat(rand.nextInt(10000)).isEqualTo(500);
        // assertThat(rand.nextInt(1)).isZero();
    }

    @Test
    void someRandomTest2() {
        Random rand = mock(Random.class);

        when(rand.nextInt(anyInt())).thenReturn(0, 1, 2, 3);

        assertThat(rand.nextInt(10)).isEqualTo(0);
        assertThat(rand.nextInt(10)).isEqualTo(1);
        assertThat(rand.nextInt(1)).isEqualTo(2);
        assertThat(rand.nextInt(5)).isEqualTo(3);

    }
}
