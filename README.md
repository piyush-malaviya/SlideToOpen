# SlideToOpen
Slide to open custom android view library

<b>Images</b>

![alt tag](https://github.com/piyush-malaviya/SlideToOpen/blob/master/screenshot/slide_to_open_1.png)
![alt tag](https://github.com/piyush-malaviya/SlideToOpen/blob/master/screenshot/slide_to_open_2.png)

##Setup

###1. Add Library
####Gradle
```
repositories {
    maven {
        ...
        url 'https://dl.bintray.com/piyush/maven'
    }
}

dependencies {
    compile 'com.pcm:slidetoopen:1.0.0'
}


```


###2. Add view in xml layout

```
<com.pcm.slidetoopen.SlideToOpen
    android:id="@+id/slideToOpen"
    android:layout_width="match_parent"
    android:layout_height="80dp"
    android:layout_alignParentBottom="true"
    android:layout_margin="12dp"
    app:margin="10"
    app:sliderColor="@color/colorPrimary"
    app:sliderText="Slide to open >"
    app:sliderTextSize="20"
    app:textOff="Close"
    app:textOn="Open"
    app:textSize="12"
    app:thumbColor="@color/colorAccent" />
```

###3. Setup SlideToOpen in Class file
```
SlideToOpen slideToOpen = (SlideToOpen) findViewById(R.id.slideToOpen);
slideToOpen.setOnSliderStatusChange(new OnSliderStatusChange() {
    @Override
    public void onSliderStatusChange(boolean isOpen) {
        if (isOpen) {
            Toast.makeText(MainActivity.this, "Open", Toast.LENGTH_SHORT).show();
        }
    }
});
```

<H2>License</H2>

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
