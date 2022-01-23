# IA-Interactive-Movies
Test project where a demo of login, list and detail of the movies is made, managing clean code and design metrics.

## Architecture

The application architecture is based on Clean Architecture. It is composed of one Android module that includes three main layers:

* **Presentation**: This layer contains the UI elements ( Activities, Fragments, Views, Adapters, etc... ) and the Android [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) from the Android Architecture Components. The ViewModel receives events from the Views and proceeds to get the required data which is then turned into view states in the form of [Livedata](https://developer.android.com/topic/libraries/architecture/livedata). The Views either listen to or react to directly via [Data Binding](https://developer.android.com/topic/libraries/data-binding).
* **Domain**: This holds the business logic. This layer contains the business models ( entities, Kotlin Data classes ), the contract definition for the Repositories and the business use cases.   
* **Data**: This layer handles the data: Where it comes from and how to get it. This logic will conform to the contracts defined in the domain layer. The Repositories can choose whether the data should be fetched from a local o remote data sources. 

## Technology stack


### front-end

Android Native Application developed using Android Studio 4.0.1 and Google libraries.

* Kotlin 1.5.31
* Gradle 7.3.1
* minSdkVersion 21
* targetSdkVersion 31 
