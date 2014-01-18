AdapterViewAnimator Backport
============

A port of AdapterViewAnimator and AdapterViewFlipper to &lt; 11 Android. Some random notes: 
 - The source code has been taken from IceCream Sandwich at the following [link](https://android.googlesource.com/platform/frameworks/base/+/android-4.0.1_r1).
 - To keep things simple and avoid digging too much in Android's source, the ```RemoteViewsAdapter.RemoteAdapterConnectionCallback``` subclassing has been removed.
   Even if AdapterViewFlipper is annotated with ```RemoteView```, it seems not to work inside App widgets. For this reason, the internal register/unregister of
   the ```BroadcastReceiver``` has been commented out. The class is fully functional in normal layouts, see [ava-sample](https://github.com/arichiardi/ava-project/tree/master/ava-sample).
 - StackView is way more complex to port because it makes use of much more API 11 goodies like the internal Transformation Matrix. It will be ported with the help of the Android community at a later stage (hopefully).
 - AdapterViewAnimator (superclass) makes use of package private and private fields/methods of ArrayAdapter (subclass). For this reason reflection is necessary.
 - The sample module follows [this](http://developer.android.com/guide/topics/appwidgets/index.html) Android tutorial and includes some minimal testing (using Robolectric 2.2).
 - It is left to the developer to either branch with the classic ```if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) ...```.
 - Animation package provided by [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids).
 
The library is in the apklib format but given the limited number of resources added by this project, and to avoid [this](https://groups.google.com/forum/#!msg/actionbarsherlock/i6fGbZn-m_M/YZTkzn5XIhQJ), a jar package will be provided.
If you use the jar file, don't forget to manually merge the included ```attrs.xml```.

The provided AdapterViewAnimator and AdapterViewFlipper both use their own custom attributes.
The correct way to use them in a layout if the package is imported as apklib is
(see [ava-sample](https://github.com/arichiardi/ava-project/tree/master/ava-sample)):

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:abp="http://schemas.android.com/apk/apk/res-auto"
    ...>
    
    <com.andrearichiardi.android.avabackport.widget.AdapterViewFlipper
        android:id="@+id/flipper"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:gravity="center"
        abp:flipInterval="500"
        abp:autoStart="true"
        abp:loopViews="true"
        abp:animateFirstView="true"
        abp:inAnimation="@drawable/slide_in_right"
        abp:outAnimation="@drawable/slide_out_left"/>

</RelativeLayout>
```

To include AdapterViewAnimator backport to your project add to your Maven pom.xml:

```
<dependency>
    <groupId>com.andrearichiardi.android</groupId>
    <artifactId>ava-backport</artifactId>
    <version>${ava-backport.version}</version>
    <type>apklib</type>
</dependency>
```

License
-------

    Copyright 2014 Andrea Richiardi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

###### Developed and maintained By Andrea Richiardi
