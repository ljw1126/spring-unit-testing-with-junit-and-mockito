package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

import java.util.Arrays;
import java.util.OptionalInt;

public class SomeBusinessImpl {

    private SomeDataService someDataService;
    // 생성자 주입방식
    public SomeBusinessImpl(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }
    // 요렇게 한 세트
    public SomeBusinessImpl(){}

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    public int calculateSum(int[] data) {
        /*
        OptionalInt number = Arrays.stream(data).reduce(Integer::sum);
        return number.orElse(0);
        */
        return Arrays.stream(data).reduce(Integer::sum).orElse(0);

    }

    public int calculateSumUsingDataService() {
        int sum = 0;
        int[] data = someDataService.retrieveAllData();
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

}
