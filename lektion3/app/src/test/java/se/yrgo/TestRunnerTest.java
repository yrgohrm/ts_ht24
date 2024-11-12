package se.yrgo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class TestRunnerTest {
    @Mock
    Random rand;

    @Test
    void testSomethingRandom() {
        when(rand.nextInt()).thenReturn(1,2,3);

        assertThat(rand.nextInt()).isEqualTo(1);
        assertThat(rand.nextInt()).isEqualTo(2);
        assertThat(rand.nextInt()).isEqualTo(3);
    }

    @Test
    void testOtherRandom() {
        when(rand.nextInt()).thenReturn(3);

        assertThat(rand.nextInt()).isEqualTo(3);
        assertThat(rand.nextInt()).isEqualTo(3);
    }
}
