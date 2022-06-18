package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Mockito simple to use
 * Note.
 * 1. junit5 using @ExtendWith(MockitoExtension.class) instead of @RunWith(MockitoJUnitRunner.class), junit4
 */
@ExtendWith(MockitoExtension.class)
class SomeBusinessMockTest {

    // NOTE. @BeforeEach 통해 직접 주입을 안 해도 됨
    @InjectMocks
    SomeBusinessImpl business;

    @Mock
    SomeDataService dataServiceMock = mock(SomeDataService.class);

    @DisplayName("Mock 사용하여 simple 인터페이스 구현")
    @Test
    public void calculateSumUsingDataService_basic() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        assertEquals(6, business.calculateSumUsingDataService());
    }

    @DisplayName("")
    @Test
    public void calculateSumUsingDataService_empty() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, business.calculateSumUsingDataService());
    }

    @DisplayName("")
    @Test
    public void calculateSumUsingDataService_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(5, business.calculateSumUsingDataService());
    }

}