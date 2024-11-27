# Video Player
A simple video player application built with JavaFX that supports basic playback features, including play/pause, volume adjustment, and seek functionality. Users can control playback using both mouse and keyboard inputs.

## Features
- Play and pause videos with a button or the spacebar.<br>
- Adjust volume using a slider or the arrow keys (up/down).<br>
- Seek through the video with a slider or the arrow keys (left/right).<br>
- Displays current and total video duration.<br>

## Usage
- Set the video file to play by updating the SOURCE variable in VideoPlayer.java.<br>
  ```java
  public static String SOURCE = "C:/path/to/your/video.mp4";
  ```
- Build and run the application.

## Controls
- **Mouse:** Click on the play or pause button, adjust sliders for volume and seek.
- **Keyboard:**
  - Spacebar: Toggle play/pause.
  - Arrow keys:
    - Up/Down: Increase/Decrease volume.
    - Left/Right: Seek backward/forward by 5 seconds.
