# covid-data-app

This App Shows the Covid-19 cases all over the World.

1st Screen is a Splash Screen.

On the next Screen, User can view the world wide covid-19 Cases. User can Also, Search by Country.

On the 3rd Screen, User gets the list of all the countries sorted in Alphabetical order.
On Selecting any country, User is taken to the 4th Screen where user can see the Covid-19 details of the country.

This App also Supports caching i.e. if some data was previously loaded, we view that data in absence of Internet Connectivity.
 (We are assuming that we had proper internet connection on the first launch of app otherwise app might crash)
 
 
Technical Specification:
 We have Used Dagger for Dependency Injection. 
 Room Persistence Library for Offline Support.
 We have also written Unit test cases for all Network Related calls.
 
 
Build Information:

Release build can be found on the root of the project with name "app-release.apk"