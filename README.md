# AndroidMVVMCompose

This is a small project, training the implementation of the MVVM (Model-View-ViewModel) pattern.

The app makes a call, via Retrofit, to https://jsonplaceholder.typicode.com/todos, and populates a RecyclerView with the retrieved data.

##### Keywords
* Android
* Kotlin
* MVVM
* Jetpack Compose
* Retrofit (maybe?) / GraphQL
* Coroutines
* Hilt

Have fun!

### Setup

If any updates in the graphql structure, run the following command to update the schema.sdl file:
./gradlew :app:downloadApolloSchema --endpoint=https://countries.trevorblades.com --schema=app/src/main/graphql/schema.sdl 