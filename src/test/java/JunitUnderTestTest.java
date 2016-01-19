import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UnderTest.class, Newable.class})
public class JunitUnderTestTest {
    @Mock
    private Newable newable;

    private UnderTest underTest;

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @Before
    public void setUp() throws Exception {
        underTest = new UnderTest();
        PowerMockito.whenNew(Newable.class).withAnyArguments().thenReturn(newable);
    }

    @Test
    public void testDoSomething() throws Exception {
        underTest.doSomething();

        PowerMockito.verifyNew(Newable.class).withArguments(captor.capture(), Mockito.eq(1));

        List<String> strings = captor.getValue();

        Assert.assertTrue(strings.isEmpty());
    }
}