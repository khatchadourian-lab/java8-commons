package com.github.rinfield.java8.util;

import static com.github.rinfield.java8.util.Flag.FALSE;
import static com.github.rinfield.java8.util.Flag.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FlagTest {

    @Test
    public void andTest() {
        // 2
        assertThat(TRUE.and(TRUE), is(TRUE));
        assertThat(TRUE.and(FALSE), is(FALSE));
        assertThat(FALSE.and(TRUE), is(FALSE));
        assertThat(FALSE.and(FALSE), is(FALSE));

        // 3
        assertThat(TRUE.and(TRUE, TRUE), is(TRUE));
        assertThat(TRUE.and(FALSE, TRUE), is(FALSE));
        assertThat(TRUE.and(TRUE, FALSE), is(FALSE));
        assertThat(TRUE.and(FALSE, FALSE), is(FALSE));

        assertThat(FALSE.and(TRUE, TRUE), is(FALSE));
        assertThat(FALSE.and(FALSE, TRUE), is(FALSE));
        assertThat(FALSE.and(TRUE, FALSE), is(FALSE));
        assertThat(FALSE.and(FALSE, FALSE), is(FALSE));

        // 4
        assertThat(TRUE.and(TRUE, TRUE, TRUE), is(TRUE));
        assertThat(TRUE.and(FALSE, TRUE, TRUE), is(FALSE));
        assertThat(TRUE.and(TRUE, FALSE, TRUE), is(FALSE));
        assertThat(TRUE.and(TRUE, TRUE, FALSE), is(FALSE));
        assertThat(TRUE.and(FALSE, FALSE, TRUE), is(FALSE));
        assertThat(TRUE.and(FALSE, TRUE, FALSE), is(FALSE));
        assertThat(TRUE.and(TRUE, FALSE, FALSE), is(FALSE));
        assertThat(TRUE.and(FALSE, FALSE, FALSE), is(FALSE));

        assertThat(FALSE.and(TRUE, TRUE, TRUE), is(FALSE));
        assertThat(FALSE.and(FALSE, TRUE, TRUE), is(FALSE));
        assertThat(FALSE.and(TRUE, FALSE, TRUE), is(FALSE));
        assertThat(FALSE.and(TRUE, TRUE, FALSE), is(FALSE));
        assertThat(FALSE.and(FALSE, FALSE, TRUE), is(FALSE));
        assertThat(FALSE.and(FALSE, TRUE, FALSE), is(FALSE));
        assertThat(FALSE.and(TRUE, FALSE, FALSE), is(FALSE));
        assertThat(FALSE.and(FALSE, FALSE, FALSE), is(FALSE));
    }

    @Test
    public void orTest() {
        // 2
        assertThat(TRUE.or(TRUE), is(TRUE));
        assertThat(TRUE.or(FALSE), is(TRUE));
        assertThat(FALSE.or(TRUE), is(TRUE));
        assertThat(FALSE.or(FALSE), is(FALSE));

        // 3
        assertThat(TRUE.or(TRUE, TRUE), is(TRUE));
        assertThat(TRUE.or(FALSE, TRUE), is(TRUE));
        assertThat(TRUE.or(TRUE, FALSE), is(TRUE));
        assertThat(TRUE.or(FALSE, FALSE), is(TRUE));

        assertThat(FALSE.or(TRUE, TRUE), is(TRUE));
        assertThat(FALSE.or(FALSE, TRUE), is(TRUE));
        assertThat(FALSE.or(TRUE, FALSE), is(TRUE));
        assertThat(FALSE.or(FALSE, FALSE), is(FALSE));

        // 4
        assertThat(TRUE.or(TRUE, TRUE, TRUE), is(TRUE));
        assertThat(TRUE.or(FALSE, TRUE, TRUE), is(TRUE));
        assertThat(TRUE.or(TRUE, FALSE, TRUE), is(TRUE));
        assertThat(TRUE.or(TRUE, TRUE, FALSE), is(TRUE));
        assertThat(TRUE.or(FALSE, FALSE, TRUE), is(TRUE));
        assertThat(TRUE.or(FALSE, TRUE, FALSE), is(TRUE));
        assertThat(TRUE.or(TRUE, FALSE, FALSE), is(TRUE));
        assertThat(TRUE.or(FALSE, FALSE, FALSE), is(TRUE));

        assertThat(FALSE.or(TRUE, TRUE, TRUE), is(TRUE));
        assertThat(FALSE.or(FALSE, TRUE, TRUE), is(TRUE));
        assertThat(FALSE.or(TRUE, FALSE, TRUE), is(TRUE));
        assertThat(FALSE.or(TRUE, TRUE, FALSE), is(TRUE));
        assertThat(FALSE.or(FALSE, FALSE, TRUE), is(TRUE));
        assertThat(FALSE.or(FALSE, TRUE, FALSE), is(TRUE));
        assertThat(FALSE.or(TRUE, FALSE, FALSE), is(TRUE));
        assertThat(FALSE.or(FALSE, FALSE, FALSE), is(FALSE));
    }

    @Test
    public void instanceEqualityTest() throws Exception {
        assertThat(TRUE, is(sameInstance(TRUE)));
        assertThat(FALSE, is(sameInstance(FALSE)));

        assertThat(Flag.of(true), is(sameInstance(Flag.of(true))));
        assertThat(Flag.of(false), is(sameInstance(Flag.of(false))));

        assertThat(TRUE, is(sameInstance(Flag.of(true))));
        assertThat(FALSE, is(sameInstance(Flag.of(false))));

        assertThat(Flag.of(true), is(sameInstance(TRUE)));
        assertThat(Flag.of(false), is(sameInstance(FALSE)));
    }

    @Test
    public void equalsTest() throws Exception {
        assertThat(TRUE, is(TRUE));
        assertThat(FALSE, is(FALSE));

        assertThat(Flag.of(true), is(Flag.of(true)));
        assertThat(Flag.of(false), is(Flag.of(false)));

        assertThat(TRUE, is(Flag.of(true)));
        assertThat(FALSE, is(Flag.of(false)));

        assertThat(Flag.of(true), is(TRUE));
        assertThat(Flag.of(false), is(FALSE));
    }

    @Test
    public void isTrueFalseTest() throws Exception {
        assertThat(TRUE.isTrue(), is(true));
        assertThat(TRUE.isFalse(), is(false));

        assertThat(FALSE.isTrue(), is(false));
        assertThat(FALSE.isFalse(), is(true));
    }
}
