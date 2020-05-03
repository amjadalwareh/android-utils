# Android-Utils

### How to implement

#### First
Add those lines in ```build.gradle Project level```
```
    repositories {
	    maven {
	        url "https://jitpack.io"
	    }
	}
```

#### Then
Back to your ```build.gradle App level```
```
dependencies {
    def utilsVersion = "1.0-beta12"
    implementation "com.github.AmjadAlwareh25:Android-Utils:$utilsVersion"
}
```