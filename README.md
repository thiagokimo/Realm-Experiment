# Realm-Test
## Goal
- Consume 2 JSON files when the app starts for the first time
- Insert the data from those files into the Realm
- Displays the data from the database once they're ready

## Decisions
### Regarding the JSON files
I've added 2 JSON files locally, in the `raw` resource folder.
The first file is a 1 big JSON object which contains information about a movie (Mad Max from 1979 if you're curious).
The second file is an array with 100 objects, each of contains random generated data representing users.

### UI
The app has only 1 main screen, with a view-pager. Each of them has a loading and a normal state. While the data is processed
in the background, the user can swipe the view-pager until each page's data is fully loaded.

**OBS:** On top of the data processing there's an delay of **5 seconds** for the first page (movie) and **10 seconds** for the
second page (list).

### Dependency Injection
Since this demo is quite simple I've decided not to use [Dagger](https://www.google.se/url?sa=t&rct=j&q=&esrc=s&source=web&cd=5&cad=rja&uact=8&ved=0ahUKEwibqv6bnJ7WAhUGCpoKHWSwChIQFghBMAQ&url=https%3A%2F%2Fgoogle.github.io%2Fdagger%2F&usg=AFQjCNEDTmybLxAjCPSyqLwPLBC4I6XMyQ)
to organize the [injections of the dependencies](https://www.google.se/url?sa=t&rct=j&q=&esrc=s&source=web&cd=13&cad=rja&uact=8&ved=0ahUKEwjz1KexnZ7WAhUEQJoKHTUfB5wQFgheMAw&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FDependency_injection&usg=AFQjCNFL_POPe3c3kxDFTghgyr3NJIIWyw).

### Libraries
- [AppCompat](https://developer.android.com/topic/libraries/support-library/features.html) and [Design Support](https://developer.android.com/training/material/design-library.html) 
to keep the consistency of the [Material Design guidelines](https://material.io/guidelines/) in the app.
- [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) to display the list of users
- [Picasso](http://square.github.io/picasso/) to download and display the movie cover image.

### Architecture
At the frontend I've used [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) 
advocating in favor of using [fragments](https://developer.android.com/guide/components/fragments.html) in the `View` layer.

#### Model
A simple (very simple) [repository pattern](https://martinfowler.com/eaaCatalog/repository.html) abstraction provides the interface to 
query the database. For the sake of this demo I didn't implement the whole [CRUD](https://www.google.se/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&cad=rja&uact=8&ved=0ahUKEwj94qD1mZ7WAhWDDZoKHWy2DrUQFgg7MAE&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FCreate%2C_read%2C_update_and_delete&usg=AFQjCNFE9JzuGHQ7kSdftyZ3MMe7nMBIqQ) 
since I only needed to insert and read.

#### View
The views are "stupid" in the sense of not having business logic at all.
In addition, there's an extra layer (`ViewModel`) with I've also decided to use just to play with [data binding](https://developer.android.com/topic/libraries/data-binding/index.html). 
The main goal of this layer is basically format the data to be further displayed in the view. 

#### Presenter/View-Model
In this experiment the presenters are somehow "stupid". They basically being the interface between the repositories and the 
view-models. They have the necessary dependencies to load the data, calculating the states of the views and passing
the model to the view-models.

### Helpers and Utils
- **ParserHelper** maps the JSON data into the models.
- **SharedPreferencesHelper** stores locally if the JSON files were completelly parsed.
- **Binders** contains the custom data binding functions used by the view-models.
- **ViewBuilder** creates the views with their own dependencies.
- **DataManager** performs the transactions of the database.

## Flow
### Application-Lifecycle
- App start
- Perform initial sync if JSON files weren't parsed
- The DataManager executes the parsing and the insertions in the database, **asynchronously**.
- Once both processes flag the initial sync as done.

### View-Lifecycle
- Presenters create listeners for their repositories.
- Once the data is ready, presenters pull the models from the repositories and set them into the view-models.

## Author
- Thiago Rocha
- <kimo@kimo.io>



