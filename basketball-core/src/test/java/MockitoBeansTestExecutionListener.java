import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockitoBeansTestExecutionListener extends AbstractTestExecutionListener {

    private static final Map<Class<?>, MockBeanWrapper> mockBeans = new ConcurrentHashMap<>(30);
    private static final Map<Class<?>, List<Field>> injectMockBeans = new ConcurrentHashMap<>(30);
    private static boolean hasInitialized = false;

    public static Map<Class<?>, MockBeanWrapper> resolvedAllMockBeans() {
        Assert.isTrue(hasInitialized);
        return Collections.unmodifiableMap(mockBeans);
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        Field[] declaredFields = testContext.getTestClass().getDeclaredFields();
        //将需要mock的对象创建出来
        for (Field field : declaredFields) {
            Mock mockAnnon = field.getAnnotation(Mock.class);
            if (mockAnnon != null) {
                MockBeanWrapper wrapper = new MockBeanWrapper();
                Class<?> type = field.getType();
                wrapper.setMockObject(Mockito.mock(type));
                wrapper.setBeanType(type);
                wrapper.setBeanName(field.getName());
                mockBeans.putIfAbsent(wrapper.getBeanType(), wrapper);
                injectMockBeans.compute(testContext.getTestClass(), (targetClass, waitInjectFields) -> {
                    if (waitInjectFields == null) {
                        waitInjectFields = new ArrayList<>();
                    }
                    waitInjectFields.add(field);
                    return waitInjectFields;
                });
            }
        }
        hasInitialized = true;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Object testInstance = testContext.getTestInstance();
        List<Field> fields = injectMockBeans.get(testContext.getTestClass());
        if (fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(testInstance, mockBeans.get(field.getType()).getMockObject());
            }
        }
    }

    public class MockBeanWrapper {

        private String beanName;
        private Class<?> beanType;
        private Object mockObject;

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        public Class<?> getBeanType() {
            return beanType;
        }

        public void setBeanType(Class<?> beanType) {
            this.beanType = beanType;
        }

        public Object getMockObject() {
            return mockObject;
        }

        public void setMockObject(Object mockObject) {
            this.mockObject = mockObject;
        }
    }
}
