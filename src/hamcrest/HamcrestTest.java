package hamcrest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.matchers.JUnitMatchers.hasItem;


public class HamcrestTest {
    private List<String> values;

    @Before
    public void setupList() {
        values = new ArrayList<>();
        values.add("x");
        values.add("y");
        values.add("z");
    }

    @Test
    public void testWithoutHamcrest() {
        assertTrue(values.contains("one")
                || values.contains("two")
                || values.contains("three")
        );
    }

    @Test
    public void testWithHamcrest(){
        assertThat(values, hasItem(anyOf(equalTo("one"), equalTo("two"), equalTo("three"))));
    }
}
