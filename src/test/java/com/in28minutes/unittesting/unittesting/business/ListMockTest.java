package com.in28minutes.unittesting.unittesting.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
}
