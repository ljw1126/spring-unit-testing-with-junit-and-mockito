package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeDataServiceStub implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {1, 2, 3};
    }
}

class SomeDataServiceEmptyStub implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {};
    }
}

class SomeDataServiceOneElementStub implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {5};
    }
}

class SomeBusinessImplTest {

    @Test
    public void calculateSum_basic() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculateSum(new int[]{1, 2, 3});
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSum_empty() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculateSum(new int[]{});
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSum_oneValue() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculateSum(new int[]{5});
        int expectedResult = 5;
        assertEquals(expectedResult, actualResult);
    }

    /**
     * DB와 같은 outside 에 연결하지 않고 테스트해보고 싶은 경우
     */
    @DisplayName("SomeDataServiceStub 클래스 생성 호출 테스트")
    @Test
    public void calculateSumUsingDataService_basic() {
        SomeBusinessImpl business = new SomeBusinessImpl(new SomeDataServiceStub());

        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("exercise1")
    @Test
    public void calculateSumUsingDataService_empty() {
        SomeBusinessImpl business = new SomeBusinessImpl(new SomeDataServiceEmptyStub());
        int actualResult = business.calculateSumUsingDataService(); // new int[]{}
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("exercise2")
    @Test
    public void calculateSumUsingDataService_oneValue() {
        SomeBusinessImpl business = new SomeBusinessImpl(new SomeDataServiceOneElementStub());
        int actualResult = business.calculateSumUsingDataService(); // new int[]{5}
        int expectedResult = 5;
        assertEquals(expectedResult, actualResult);
    }

    /**
     * 계속해서 *stub 클래스를 만들고, interface 에 메서드 추가후 수정하는건 비효율적
     * 그래서 mocking 을 사용한다 함
     */
}