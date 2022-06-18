package com.in28minutes.unittesting.unittesting.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mock = mock(List.class);

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

    // https://beststar-1.tistory.com/30
    @DisplayName("테스트 하고자 하는 메서드가 의도대로 동작했는지 검증")
    @Test
    public void verificationBasics() {
        String value = mock.get(0);

        //Verify. 결국 mock 객체가 get(0) 호출했는지 검증하는거네.
        verify(mock).get(0);
        verify(mock).get(anyInt());
        verify(mock, times(1)).get(anyInt()); // 한번 호출했는가
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, never()).get(2); // 2번 호출 하지 않았다.
    }

    @DisplayName("argument capture")
    @Test
    public void argumentCapturing() {
        // SUT
        mock.add("SomeString");

        // Verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture()); // verify : 증명하다, mock 객체가 add() 실행시 argument 를 확인하는 용도 같음

        assertEquals("SomeString", captor.getValue()); // true
    }
}
