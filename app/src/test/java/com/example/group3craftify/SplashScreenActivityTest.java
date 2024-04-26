package com.example.group3craftify;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SplashScreenActivityTest extends TestCase {

    @Mock
    private ImageView mockLogoImageView;

    @Mock
    private ProgressBar mockProgressBar;

    @Mock
    private Handler mockHandler;

    public void testSplashScreenBehavior() throws NoSuchFieldException, IllegalAccessException {
        SplashScreenActivity splashScreenActivity = Mockito.spy(new SplashScreenActivity());

        // Access progressBar field using reflection
        Field progressBarField = SplashScreenActivity.class.getDeclaredField("progressBar");
        progressBarField.setAccessible(true);
        progressBarField.set(splashScreenActivity, mockProgressBar);

        // Mock findViewById
        when(splashScreenActivity.findViewById(R.id.imageViewLogo)).thenReturn(mockLogoImageView);
        when(splashScreenActivity.findViewById(R.id.progressBar)).thenReturn(mockProgressBar);

        // Mock SPLASH_DISPLAY_LENGTH
        int mockSplashDisplayLength = 3000;

        // Mock Handler.postDelayed()
        Runnable mockRunnable = Mockito.mock(Runnable.class);
        when(mockHandler.postDelayed(Mockito.any(Runnable.class), Mockito.eq(mockSplashDisplayLength))).thenAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
            return null;
        });

        // Call onCreate
        splashScreenActivity.onCreate(null);

        // Verify animations and method calls
        verify(mockLogoImageView).startAnimation(Mockito.any());
        verify(mockProgressBar).startAnimation(Mockito.any());
        verify(mockHandler).postDelayed(Mockito.any(), Mockito.eq(mockSplashDisplayLength));
        verify(splashScreenActivity).startActivity(Mockito.any(Intent.class));
        verify(splashScreenActivity).finish();
    }
}
