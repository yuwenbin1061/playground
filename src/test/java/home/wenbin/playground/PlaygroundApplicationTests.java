package home.wenbin.playground;

import home.wenbin.playground.mock_example.FirstClass;
import home.wenbin.playground.mock_example.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaygroundApplicationTests {

    @Mock
    FirstClass firstClass;

    @Test
    public void contextLoads() {

        doNothing().when(firstClass).execute(any(TestBean.class));

        TestBean testBean = new TestBean();
        testBean.setParam1("123");
        testBean.setParam2("456");

        firstClass.execute(testBean);

        testBean.setParam1("aaa");
        testBean.setParam2("bbb");
        firstClass.execute(testBean);

        ArgumentCaptor<TestBean> argumentCaptor = ArgumentCaptor.forClass(TestBean.class);
        verify(firstClass, times(2)).execute(argumentCaptor.capture());

        List<TestBean> arguments = argumentCaptor.getAllValues();
    }

}

