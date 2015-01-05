package com.github.rinfield.java8.function;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.github.rinfield.java8.function.ThrowingFunction;

@RunWith(Enclosed.class)
public class ThrowingFunctionTest {
    interface Stub {
        String get() throws Exception; // <- checked Exception
    }

    public static class RethrowTests extends TestCommon {
        @Test
        public void okStreamMap() {
            assertThat(ok1.map(stubGet.rethrow()).toArray(),
                is(arrayContaining(OK)));
            assertThat(ok2.map(stubGet.rethrow()).toArray(),
                is(arrayContaining(OK, OK)));
        }

        @Test(expected = RuntimeException.class)
        public void ngStreamMap1() throws Exception {
            ng1.map(stubGet.rethrow()).toArray();
        }

        @Test(expected = RuntimeException.class)
        public void ngStreamMap2() throws Exception {
            ng2.map(stubGet.rethrow()).toArray();
        }

        @Test(expected = RuntimeException.class)
        public void ngStreamMap3() throws Exception {
            okNg.map(stubGet.rethrow()).toArray();
        }

        @Test(expected = RuntimeException.class)
        public void ngStreamMap4() throws Exception {
            ngOk.map(stubGet.rethrow()).toArray();
        }

        @Test
        public void okCompose() throws Exception {
            assertThat(stubGet.compose(beforeOk).rethrow().apply(COMPOSED),
                is(COMPOSED));
        }

        @Test
        public void okAndThen() throws Exception {
            assertThat(stubGet.andThen(afterOk).rethrow().apply(okStub), is(2));
        }

        @Test(expected = RuntimeException.class)
        public void ngCompose() throws Exception {
            stubGet.compose(beforeNg).rethrow().apply(COMPOSED);
        }

        @Test(expected = RuntimeException.class)
        public void ngAndThen() throws Exception {
            stubGet.andThen(afterNg).rethrow().apply(okStub);
        }
    }

    public static class OptionTests extends TestCommon {
        static final Optional<String> optOk = Optional.of(OK);
        static final Optional<String> optNg = Optional.empty();

        @SuppressWarnings("unchecked")
        @Test
        public void okStreamMap() {
            assertThat(ok1.map(stubGet.optional()).toArray(),
                is(arrayContaining(optOk)));
            assertThat(ok2.map(stubGet.optional()).toArray(),
                is(arrayContaining(optOk, optOk)));
        }

        @SuppressWarnings("unchecked")
        @Test
        public void okStreamMapWithFail() {
            assertThat(ng1.map(stubGet.optional()).toArray(),
                is(arrayContaining(optNg)));
            assertThat(ng2.map(stubGet.optional()).toArray(),
                is(arrayContaining(optNg, optNg)));
            assertThat(okNg.map(stubGet.optional()).toArray(),
                is(arrayContaining(optOk, optNg)));
            assertThat(ngOk.map(stubGet.optional()).toArray(),
                is(arrayContaining(optNg, optOk)));
        }

        @Test
        public void okCompose() throws Exception {
            assertThat(stubGet.compose(beforeOk).optional().apply(COMPOSED),
                is(Optional.of(COMPOSED)));
        }

        @Test
        public void okComposeWithFail() throws Exception {
            assertThat(stubGet.compose(beforeNg).optional().apply(COMPOSED),
                is(Optional.empty()));
        }

        @Test
        public void okAndThen() throws Exception {
            assertThat(stubGet.andThen(afterOk).optional().apply(okStub),
                is(Optional.of(2)));
        }

        @Test
        public void okAndThenWithFail() throws Exception {
            assertThat(stubGet.andThen(afterNg).optional().apply(okStub),
                is(Optional.empty()));
        }
    }

    public static class OrElseTests extends TestCommon {
        static final String DEF = "default";
        static final Supplier<String> defStr = () -> DEF;
        static final Supplier<Integer> defInt = () -> -1;

        @Test
        public void okStreamMap() {
            assertThat(ok1.map(stubGet.orElseGet(defStr)).toArray(),
                is(arrayContaining(OK)));
            assertThat(ok2.map(stubGet.orElseGet(defStr)).toArray(),
                is(arrayContaining(OK, OK)));
        }

        @Test
        public void okStreamMapWithFail() {
            assertThat(ng1.map(stubGet.orElseGet(defStr)).toArray(),
                is(arrayContaining(DEF)));
            assertThat(ng2.map(stubGet.orElseGet(defStr)).toArray(),
                is(arrayContaining(DEF, DEF)));
            assertThat(okNg.map(stubGet.orElseGet(defStr)).toArray(),
                is(arrayContaining(OK, DEF)));
            assertThat(ngOk.map(stubGet.orElseGet(defStr)).toArray(),
                is(arrayContaining(DEF, OK)));
        }

        @Test
        public void okCompose() throws Exception {
            assertThat(
                stubGet.compose(beforeOk).orElseGet(defStr).apply(COMPOSED),
                is(COMPOSED));
        }

        @Test
        public void okComposeWithFail() throws Exception {
            assertThat(
                stubGet.compose(beforeNg).orElseGet(defStr).apply(COMPOSED),
                is(DEF));
        }

        @Test
        public void okAndThen() throws Exception {
            assertThat(
                stubGet.andThen(afterOk).orElseGet(defInt).apply(okStub), is(2));
        }

        @Test
        public void okAndThenWithFail() throws Exception {
            assertThat(
                stubGet.andThen(afterNg).orElseGet(defInt).apply(okStub),
                is(-1));
        }
    }

    public static class OnFailTests extends TestCommon {
        static final String ON_FAIL = "on fail";
        static final Function<Throwable, String> onFail = t -> ON_FAIL;
        static final Function<Throwable, Integer> onFailInt = t -> -1;

        @Test
        public void okStreamMap() {
            assertThat(ok1.map(stubGet.catches(onFail)).toArray(),
                is(arrayContaining(OK)));
            assertThat(ok2.map(stubGet.catches(onFail)).toArray(),
                is(arrayContaining(OK, OK)));
        }

        @Test
        public void okStreamMapWithFail() {
            assertThat(ng1.map(stubGet.catches(onFail)).toArray(),
                is(arrayContaining(ON_FAIL)));
            assertThat(ng2.map(stubGet.catches(onFail)).toArray(),
                is(arrayContaining(ON_FAIL, ON_FAIL)));
            assertThat(okNg.map(stubGet.catches(onFail)).toArray(),
                is(arrayContaining(OK, ON_FAIL)));
            assertThat(ngOk.map(stubGet.catches(onFail)).toArray(),
                is(arrayContaining(ON_FAIL, OK)));
        }

        @Test
        public void okCompose() throws Exception {
            assertThat(stubGet.compose(beforeOk).catches(onFail)
                .apply(COMPOSED), is(COMPOSED));
        }

        @Test
        public void okComposeWithFail() throws Exception {
            assertThat(stubGet.compose(beforeNg).catches(onFail)
                .apply(COMPOSED), is(ON_FAIL));
        }

        @Test
        public void okAndThen() throws Exception {
            assertThat(stubGet.andThen(afterOk).catches(onFailInt)
                .apply(okStub), is(2));
        }

        @Test
        public void okAndThenWithFail() throws Exception {
            assertThat(stubGet.andThen(afterNg).catches(onFailInt)
                .apply(okStub), is(-1));
        }
    }

    public static abstract class TestCommon {
        Stream<Stub> ok1;
        Stream<Stub> ok2;
        Stream<Stub> ng1;
        Stream<Stub> ng2;
        Stream<Stub> okNg;
        Stream<Stub> ngOk;

        final ThrowingFunction<Stub, String> stubGet = s -> s.get();

        final ThrowingFunction<String, Stub> beforeOk = s -> () -> s;
        final ThrowingFunction<String, Stub> beforeNg = s -> () -> {
            throw new Exception();
        };
        final ThrowingFunction<String, Integer> afterOk = s -> s.length();
        final ThrowingFunction<String, Integer> afterNg = s -> {
            throw new Exception();
        };

        static final String OK = "OK";
        static final String COMPOSED = "composed";

        static final Stub okStub = () -> OK;
        static final Stub ngStub = () -> {
            throw new RuntimeException();
        };

        @Before
        public void before() {
            ok1 = Stream.of(okStub);
            ok2 = Stream.of(okStub, okStub);
            ng1 = Stream.of(ngStub);
            ng2 = Stream.of(ngStub, ngStub);
            okNg = Stream.of(okStub, ngStub);
            ngOk = Stream.of(ngStub, okStub);
        }
    }
}