# AndroidMVVMCompose

This is a small project, training the implementation of the MVVM (Model-View-ViewModel) pattern.

The app makes a call, via GraphQL, to https://countries.trevorblades.com/, and populates a list with the retrieved data, using Jetpack Compose

##### Keywords
* Android
* Kotlin
* MVVM
* Jetpack Compose
* GraphQL
* Coroutines / Flow
* Hilt
* JUnit
* Mockk

### Setup
If there are any updates in the graphql structure, run the following command to update the schema.sdl file:

```
./gradlew downloadContinentsAndCountriesApolloSchemaFromIntrospection
```

Have fun!