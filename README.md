# GithubProfile Apps

Github Profile Apps

GithubProfile is an App to Search for profiles through github data, users can search and view profiles, repositories, & stats of all other users.

I made this project during the assessment process as an Android Engineer at *Ajaib*. by following the requirements and specifications given.



## Features

---

#### Instant Search

by utilizing kotlin flow technology, I created a simple function in the form of instant search, the search bar will detect typing done by the user 500ms after that and will immediately make a call to the rest api

#### Search and Detail Screen

on this page there is a list that accommodates the results of the github search api response which actually has some data that doesn't match the design guidelines given, but as engineers of course we have to be able to handle it well #peace:D

#### Local Cache with Room

every user who presses an item in the list will be directed to the detail page, the application will save it locally so that users can see it again even without being connected to the internet

## Data

---

GithubProfile uses data directly from public api provided by github like `/search/user` & `/user/repos`

## Architecture

---

The app is built with MVVM architecture and JetPack Compose for the ui also in a Redux-style, where each UI 'screen' has its own [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), which exposes a single [StateFlow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) containing the entire view state. Each [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.

## Stack

---

| Architecture Components | Android Architecture Components (AAC) is a new collection of libraries that contains the lifecycle-aware components.a                                                                                                 |
| ----------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Dagger Hilt             | Hilt provides a standard way to incorporate Dagger dependency injection into an Android application                                                                                                                   |
| Jetpack Compose         | Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs |
| Coroutine               | Asynchronous or non-blocking programming is an important part of the development landscape                                                                                                                            |
| Retrofit                | A type-safe HTTP client for Android and Java. Introduction. Retrofit turns your HTTP API into a Java interface                                                                                                        |
| Glide                   | ide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching,                                                                  |
| Room                    | Room is an ORM, Object Relational Mapping library. In other words, Room will map our database objects to Java objects                                                                                                 |
| Lottie                  | Lottie is an open source animation file format that's tiny, high quality, interactive, and can be manipulated at runtime.                                                                                             |
| Espresso                | Use Espresso to write concise, beautiful, and reliable Android UI tests                                                                                                                                               |
| Mockito                 | Mockito is a mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API                                                                                                   |
| JUnit                   | JUnit is a unit testing framework for the Java programming language                                                                                                                                                   |



Thank You
