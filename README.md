# Task for a Great Android developer

If you found this task it means we are looking for you!

> Note: To clone this repository you will need [GIT-LFS](https://git-lfs.github.com/)

## Few simple steps

1. Fork this repo
2. Do your best
3. Prepare pull request and let us know that you are done

## Few simple requirements

- Send authorization request (POST) to http://playground.tesonet.lt/v1/tokens to generate token with body: `{"username": "tesonet", "password": "partyanimal"}`. (Don't forget Content-Type)
- Get servers list from http://playground.tesonet.lt/v1/servers. Add header to request: `Authorization: Bearer <token>`
- Design should be recreated as closely as possible
- Bonus: implement smooth animated transition from login through loader to server list screen
- Bonus: implement persistent storage of the downloaded server data
- Bonus: have a good set of unit tests

*Note:* The bonus requirements are optional. While they are nice to have, it's much more important to have the basics nailed.

# My Implementation

## Libraries used and what was done

- MVVM architecture
- Hilt for dependency injection
- Ktor as http client
- Android Navigation Component and safe args for smooth navigation
- Coroutines Flow to handle network calls
- SQLDelight for persistent storage
- JUnit and Mockito for testing
- Shared Preferences to store token and login state
- Explicitly handled errors
- Logout handled
- Offline mode handled
