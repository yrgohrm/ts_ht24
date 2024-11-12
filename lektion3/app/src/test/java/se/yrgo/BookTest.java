package se.yrgo;

import static org.mockito.Mockito.mock;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class BookTest {
@Test
void someBookTest() {
    BookEdition edition = mock(BookEdition.class);
    Date pd = new Date(1654034400); // 2022-06-01 00:00:00
    Book book = new Book(1, pd, edition);

    // do some testing
}
}
