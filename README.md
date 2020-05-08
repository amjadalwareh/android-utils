# Android Utils

A collection of most common android utils.

## How to implement

#### First
Add those lines in ```build.gradle Project level```.

```groovy
repositories {
	maven {url "https://jitpack.io"}
 }
```

#### Then
Back to your ```build.gradle App level```
```groovy
dependencies {
    def utilsVersion = "1.0-beta14"
    implementation "com.github.amjad-alwareh:android-utils:utilsVersion"
}
```

## API Dictionary

* Connectivity Utils
* Phone Utils

## Usage

* Connectivity Utils

``` java
import com.amjadalwareh.androidutils.ConnectivityUtils;

ConnectivityUtils.isNetworkConnected(Context);
ConnectivityUtils.isWifiConnected(Context);
ConnectivityUtils.isMobileDataConnected(Context);
ConnectivityUtils.turnOnWifi(Context);
ConnectivityUtils.turnOffWifi(Context);
ConnectivityUtils.openWiFiSettings(Context);
ConnectivityUtils.openDataSettings(Context);
ConnectivityUtils.openNfcSettings(Context);
```

* Phone Utils
``` java
import com.amjadalwareh.androidutils.PhoneUtils;

PhoneUtils.isLollipop();
PhoneUtils.isMarshmallow();
PhoneUtils.isNougat();
PhoneUtils.isOreo();
PhoneUtils.isPie();
PhoneUtils.isAndroidQ(); // AKA Android 10
PhoneUtils.isPackageInstalled(Context, PackageName);
PhoneUtils.checkPermissionGranted(Context, Permission);

```

## License
[Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)
