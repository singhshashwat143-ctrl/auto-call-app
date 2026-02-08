#!/bin/bash

echo "=== Auto Call App - Local Build Script ==="
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed"
    echo ""
    echo "To install Java:"
    echo "1. Install Homebrew (if not already): /bin/bash -c \"\$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)\""
    echo "2. Install Java: brew install openjdk@17"
    echo "3. Link Java: sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk"
    echo ""
    exit 1
fi

echo "✅ Java found: $(java -version 2>&1 | head -n 1)"
echo ""

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ]; then
    echo "⚠️  ANDROID_HOME not set"
    echo "You need to install Android SDK command-line tools"
    echo "Download from: https://developer.android.com/studio#command-tools"
    echo ""
    echo "Or install Android Studio which includes everything needed."
    exit 1
fi

echo "✅ Android SDK found at: $ANDROID_HOME"
echo ""

# Build the APK
echo "🔨 Building APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ BUILD SUCCESSFUL!"
    echo ""
    echo "📱 Your APK is ready at:"
    echo "   app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "Transfer this file to your Android phone and install it!"
else
    echo ""
    echo "❌ Build failed. Please check the errors above."
    exit 1
fi
