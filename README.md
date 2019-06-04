# PedestrianCongestion	
Pedestrian Congestion is an application that tracks the user's location and shows on a heat map the	
places where he has been most. The general scope of the project is to mark the most crowded places 	
in a city in order to avoid congestions.	

	dependencies {
	    implementation fileTree(dir: 'libs', include: ['*.jar'])
   	 	implementation 'com.android.support:appcompat-v7:26.1.0'
 		implementation 'com.android.support.constraint:constraint-layout:1.0.2'
  	  	implementation 'com.google.android.gms:play-services-maps:11.8.0'
    	compile 'com.google.code.gson:gson:2.8.1'
    	compile 'com.squareup.okhttp3:okhttp:3.7.0'
    	compile 'com.android.support:recyclerview-v7:26.1.0'
    	compile 'com.google.android.gms:play-services-location:11.8.0'
    	compile project(':mylibrary')


}
