package com.example.cleanandroidarchitecture

import android.content.Context
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

private const val FAKE_STRING = "HELLO_WORLD"

@RunWith(MockitoJUnitRunner::class)
class MockitoTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        `when`(mockContext.getString(R.string.test_string))
            .thenReturn(FAKE_STRING)
        val myObjectUnderTest = ClassUnderTest(mockContext)

        // ...when the string is returned from the object under test...
        val result: String = myObjectUnderTest.getName()

        // ...then the result should be the expected one.
        assertEquals(result, FAKE_STRING)
    }

}

class ClassUnderTest(private val context: Context) {

    fun getName() = context.getString(R.string.test_string)
}