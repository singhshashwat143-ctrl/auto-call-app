# How to Build APK Using GitHub Actions

1. **Create a GitHub account** if you don't have one (free): https://github.com

2. **Upload this project to GitHub:**
   ```bash
   cd /Users/shashwatsingh/.gemini/antigravity/scratch/auto-call-app
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   # Create a new repo on GitHub first, then:
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
   git push -u origin main
   ```

3. **GitHub will automatically build your APK!**
   - Go to your repository on GitHub
   - Click "Actions" tab
   - The build will start automatically
   - Wait 2-3 minutes for it to complete

4. **Download your APK:**
   - Click on the completed workflow
   - Scroll down to "Artifacts"
   - Download "app-debug"
   - Extract the ZIP to get `app-debug.apk`

5. **Transfer to phone and install!**
