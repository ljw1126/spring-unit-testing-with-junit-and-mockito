package com.in28minutes.unittesting.unittesting.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListMockTest {

    List mock = mock(List.class);

    @Test
    public void size_basic() {
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @DisplayName("두 번 호출 할 경우")
    @Test
    public void returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    /**
     * Defaults
     * - numeric (0)
     * - boolean (false)
     * - Objects (null)
     * - Collectioons (empty collection)
     */
    @Test
    public void returnWithParameter() {
        when(mock.get(0)).thenReturn("in28Minutes");
        assertEquals("in28Minutes", mock.get(0));
        assertNull(mock.get(1)); // 설정하지 않은 걸 호출 할 경우 null 확인
    }

    @DisplayName("ArgumentMatchers 클래스 함수 활용 테스트(generic형)")
    @Test
    public void returnWithGenericParameters() {
        // 어차피 mock.get(int index) 라서 int 형만 됨
        when(mock.get(anyInt())).thenReturn("in28Minutes"); // ArgumentMatchers.class 참고
        assertEquals("in28Minutes", mock.get(0));
        assertEquals("in28Minutes", mock.get(1));
    }
}
