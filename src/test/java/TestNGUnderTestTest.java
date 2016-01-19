import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@PrepareForTest({UnderTest.class, Newable.class})
public class TestNGUnderTestTest extends PowerMockTestCase {
    @Mock
    private Newable newable;

    private UnderTest underTest;

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @BeforeMethod
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