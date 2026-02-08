package com.autocall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.telecom.TelecomManager;

public class CallReceiver extends BroadcastReceiver {
    
    private static final String TAG = "CallReceiver";
    private static MediaPlayer mediaPlayer;
    private static boolean isPlayingAudio = false;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        
        if (action != null && action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            
            if (state != null) {
                switch (state) {
                    case TelephonyManager.EXTRA_STATE_RINGING:
                        Log.d(TAG, "Phone is ringing");
                        break;
                        
                    case TelephonyManager.EXTRA_STATE_OFFHOOK:
                        // Call is connected
                        Log.d(TAG, "Call connected");
                        if (!isPlayingAudio) {
                            playRecording(context);
                        }
                        break;
                        
                    case TelephonyManager.EXTRA_STATE_IDLE:
                        // Call ended
                        Log.d(TAG, "Call ended");
                        stopRecording();
                        isPlayingAudio = false;
                        break;
                }
            }
        }
    }
    
    private void playRecording(final Context context) {
        isPlayingAudio = true;
        
        // Wait a moment for call to be fully connected
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    
                    // Set audio mode for in-call
                    audioManager.setMode(AudioManager.MODE_IN_CALL);
                    audioManager.setSpeakerphoneOn(false);
                    
                    // Create and prepare media player
                    mediaPlayer = MediaPlayer.create(context, R.raw.message);
                    
                    if (mediaPlayer != null) {
                        // Set audio stream type
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
                        
                        // Set listener for when playback completes
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                Log.d(TAG, "Recording finished playing");
                                stopRecording();
                                
                                // Hang up the call after recording is done
                                hangUpCall(context);
                            }
                        });
                        
                        // Start playing
                        mediaPlayer.start();
                        Log.d(TAG, "Started playing recording");
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error playing recording: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, 2000); // 2 second delay to ensure call is connected
    }
    
    private void stopRecording() {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                Log.e(TAG, "Error stopping recording: " + e.getMessage());
            }
        }
    }
    
    private void hangUpCall(Context context) {
        try {
            // Try to hang up using TelecomManager (Android 9+)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                TelecomManager telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
                if (telecomManager != null) {
                    boolean success = telecomManager.endCall();
                    Log.d(TAG, "Attempted to hang up call: " + success);
                }
            } else {
                // For older versions, we need to use reflection or other methods
                // This is device-dependent and may not work on all devices
                Log.d(TAG, "Auto hang-up not fully supported on this Android version");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error hanging up call: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
