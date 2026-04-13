# SingersGallery 🎵

## Description
A dark-themed Android app displaying a gallery of famous singers with ratings, search, and share features.

## Features
- Animated splash screen with fade and slide animations
- List of 11 famous singers with circular images and ratings
- Search bar to filter singers by name in real time
- Click on a singer to update their rating via a popup
- Share the app via WhatsApp, Gmail, etc.
- Dark theme with red accents throughout


## Demo Video
[Click here to watch the demo video](https://drive.google.com/file/d/17BomdYdFsybkjQDACb6iQQiY3RY9yvqi/view?usp=sharing)

## Technologies
- Java
- Android Studio
- RecyclerView + Custom Adapter
- Glide for image loading
- CircleImageView for circular photos
- Minimum SDK: API 24 (Android 7.0)

## Architecture
- `beans/` → Singer data model
- `dao/` → Generic DAO interface
- `service/` → In-memory data management
- `adapter/` → RecyclerView adapter with search filter
- `ui/` → SplashActivity + ListActivity
