# Auto Call App - Automated Call & Message Player

## 📱 What This App Does

This is a **fully functional Android application** that:
1. Takes a phone number as input
2. Automatically dials the number
3. Plays a pre-recorded audio message when the call connects
4. Automatically hangs up when the message finishes playing

**This is a REAL working app** that will create an installable APK for your Android phone.

## 🛠️ Building the APK

### Prerequisites
You need:
- **Android Studio** (recommended) OR
- **Android SDK Command Line Tools**
- **Java JDK 8 or higher**

### Option 1: Using Android Studio (Easiest)

1. **Install Android Studio** from https://developer.android.com/studio

2. **Open the project:**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the `auto-call-app` folder

3. **Wait for Gradle sync** to complete (first time may take a few minutes)

4. **Build the APK:**
   - Click `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
   - Wait for the build to complete
   - APK will be at: `app/build/outputs/apk/debug/app-debug.apk`

### Option 2: Using Command Line

1. **Install Java JDK** if not already installed

2. **Download Android SDK Command Line Tools** from:
   https://developer.android.com/studio#command-tools

3. **Navigate to project directory:**
   ```bash
   cd auto-call-app
   ```

4. **Build using Gradle wrapper:**
   ```bash
   # On macOS/Linux:
   ./gradlew assembleDebug
   
   # On Windows:
   gradlew.bat assembleDebug
   ```

5. **Find your APK at:**
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

### Option 3: Online Build Service

If you don't want to install anything locally:

1. Upload the entire `auto-call-app` folder to **GitHub**
2. Use **GitHub Actions** or services like **Codemagic** or **Bitrise** to build the APK
3. Download the built APK from the service

## 📲 Installing on Your Phone

### Method 1: Direct Install
1. Copy `app-debug.apk` to your Android phone
2. Tap the APK file
3. Allow installation from unknown sources if prompted
4. Tap "Install"

### Method 2: ADB Install
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 🔐 Permissions Required

When you first launch the app, it will request these permissions:
- **Make and manage phone calls** - Required to dial numbers
- **Read phone state** - Required to detect when call connects
- **Modify audio settings** - Required to play audio during call
- **Record audio** - Required for audio playback during call

**YOU MUST GRANT ALL PERMISSIONS** for the app to work properly.

## 📝 How to Use

1. **Launch the app** on your phone
2. **Grant all permissions** when prompted
3. **Enter a phone number** in the input field
4. **Tap "Make Call"** button
5. The app will:
   - Dial the number automatically
   - Wait for the call to connect
   - Play the pre-recorded message
   - Hang up when the message finishes

## 🎵 Customizing the Audio Message

The default message says: "Hello, this is an automated message. Your call has been received. Thank you."

To use your own recording:

1. Prepare an audio file (MP3, AIFF, or WAV format)
2. Name it `message.mp3` (or `.aiff`, `.wav`)
3. Replace the file at: `app/src/main/res/raw/message.aiff`
4. Rebuild the APK

## ⚠️ Important Notes

1. **This app makes real phone calls** - carrier charges may apply
2. **Not for spam/harassment** - use responsibly and legally
3. **Auto hang-up** works on Android 9 (API 28) and above
   - On older versions, you may need to hang up manually
4. **Test with your own number first** to verify it works
5. **Some Android versions** may restrict background call management

## 📁 Project Structure

```
auto-call-app/
├── app/
│   ├── src/main/
│   │   ├── java/com/autocall/
│   │   │   ├── MainActivity.java      # Main UI and permissions
│   │   │   ├── CallService.java       # Handles call initiation
│   │   │   └── CallReceiver.java      # Detects call state & plays audio
│   │   ├── res/
│   │   │   ├── layout/                # UI layouts
│   │   │   ├── values/                # Strings, styles
│   │   │   ├── drawable/              # UI backgrounds
│   │   │   ├── mipmap-*/              # App icons
│   │   │   └── raw/                   # Audio message file
│   │   └── AndroidManifest.xml        # App permissions & components
│   └── build.gradle                   # App dependencies
├── build.gradle                       # Project configuration
└── settings.gradle                    # Gradle settings
```

## 🐛 Troubleshooting

**App won't install:**
- Enable "Install from Unknown Sources" in phone settings
- Make sure you have Android 8.0 (API 26) or higher

**Permissions denied:**
- Go to Settings → Apps → Auto Call → Permissions
- Manually enable all permissions

**Audio doesn't play:**
- Some devices may block audio during calls
- Try different audio formats (MP3 instead of AIFF)
- Increase call delay in `CallReceiver.java` (line 53)

**Call doesn't hang up automatically:**
- Auto hang-up requires Android 9+
- On older versions, manually end the call

## 💡 Need Help?

If you face issues building the APK:
1. Make sure Java is installed: `java -version`
2. Make sure Android SDK is installed
3. Try cleaning the project: `./gradlew clean`
4. Check Android Studio's error messages

## 📜 License

This app is provided as-is for personal use. Use responsibly and in compliance with local laws.
