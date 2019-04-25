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

## Implementation

- Login added.
- For data fetching Retrofit with Moshi and RX adapter is used.
- Few logos svg are invalid for android studio import.
- Simple animation added.
- Could add global cache if needed.
- Unit mocked tests added for list.
- architecture by http://kaunas-jug.lt/material/meetup46/JUG46_Faster_testing_with_Kotlin.pptx
- Repository for my personal demo. https://gitlab.com/dzastinas/mini-list
- Custom lib used is in https://gitlab.com/dzastinas/mini-list/tree/master/minilist
- Custom lib featured app published https://play.google.com/store/apps/details?id=net.justinas.minitemplate&hl=en
