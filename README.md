# NYTimes

This Android application displays NY Times Most Popular Articles.

This project is built on MVVM and Clean Architecture.

Different layers are:
1.  Data - The data for the application is managed in two different data sources.
 <br />LocalDataSource handles the persistant storage containing RoomDatabase and DAO objects.
 <br />RemoteDataSource handles the ApiInterface from where the call to get the Most Popular Articles from http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=sample-key 
 <br />NewsRepositoryImpl - This class implements the NewsRepository interface (defined in domain layer). 

2. Domain - The domain layer handles the data received in JSON format to convert into respective models, defined the NewsRepository to be used by viewModels for making API requests and retriving data.
 <br /> Models 
 <br />NewsRepository - This is where API request is initiaited, response gathered, saved in database and retrives data from database to pass data to viewModel following single source of truth comcept.

3. Presentation - The presentationn layer mainly comprises of UI related classes (Activity/Fragments/Adapters), and viewModel. 
No business logic is written in UI related classes. Any events coming from the user interaction are passed to viewModel to process the event, generate the states and then send it back to UI layer

Live data is used to observe the change in value of data objects and the changes will be reflected on the UI automatically thru data binding.
 
For dependency injection, we are using HILT. We are injecting Database, Network, Repository and ViewModels using the same.

We are implementing single activity principle, and fragments are navigated thru Navigation Host and navGraphs.

To fetch data from retrofit calls or save into database, we are using Kotlin coroutines.

To run the code 
  1. using Studio, immport the project using Android Studio
<br />       File -> New -> Project from Version Control
<br />    Let the gradle sync build, and once sync is successful, you can run the code on any device/emulator.    
  2. using apk shared - just install the apk on any device/emulator
  
To test the code 
  1.  without code coverage - Go to com.newyork.times(test) folder, open the folder, right click on NewsViewModelTest and select "Run NewsViewModelTest"
  2.  with code coverage -  Go to com.newyork.times(test) folder, open the folder, right click on NewsViewModelTest and select "Run NewsViewModelTest with Coverage". After tests are succesfully ran, a pop up with cde coverage reports is generated to review.

