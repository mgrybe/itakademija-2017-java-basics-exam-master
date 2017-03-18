package lt.itakademija.exam.test;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mariusg on 2017.03.18.
 */
public class MethodsTest {

    @Test
    public void permutesArgsCorrectly() {
        final Methods.ArgsProducer argsProducer = Methods.permuteWithNull("1", "2", "3");

        assertThat(argsProducer.produce(), is(new Object[]{
                new Object[]{null, "2", "3"},
                new Object[]{"1", null, "3"},
                new Object[]{"1", "2", null}}));
    }


}