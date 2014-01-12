AdapterViewAnimator Backport
============

A port of AdapterViewAnimator and AdapterViewFlipper to &lt; 11 Android. Some random notes: 
 - The code has been taken from IceCream Sandwich (I would have preferred HoneyComb) at the following [link](https://android.googlesource.com/platform/frameworks/base/+/android-4.0.1_r1).
 - To keep things simple and avoid digging too much in Android's source, the ```RemoteViewsAdapter.RemoteAdapterConnectionCallback``` subclassing has been removed.
 - StackView is way more complex to port because it makes use of much more API 11 goodies like Transformation Matrix. It will be ported with the help of the Android community at a later stage (hopefully).
 - AdapterViewAnimator makes use of package private and private fields/methods of ArrayAdapter. For this reason reflection is necessary.
 - The sample module follows [this](http://developer.android.com/guide/topics/appwidgets/index.html) Android tutorial.
 - It is left to the developer to either branch ```if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) ...```, build a different layout or inject the correct view.
 
The library is provided in the apklib format but given the limited number of resources added by this project, and to avoid [this](https://groups.google.com/forum/#!msg/actionbarsherlock/i6fGbZn-m_M/YZTkzn5XIhQJ), a jar package is provided.
If you use the jar file, don't forget to manually merge the included ```attrs.xml```.

The provided AdapterViewAnimator and AdapterViewFlipper both use their own custom attributes.


