## Requirements

- [x] Send authorization request (POST) to http://playground.tesonet.lt/v1/tokens to generate token with body: `{"username": "tesonet", "password": "partyanimal"}`. (Don't forget Content-Type)
- [x] Get servers list from http://playground.tesonet.lt/v1/servers. Add header to request: `Authorization: Bearer <token>`
- [x] Design should be recreated as closely as possible
- [x] Bonus: implement smooth animated transition from login through loader to server list screen
- [ ] Bonus: implement persistent storage of the downloaded server data
- [x] Bonus: have a good set of unit tests

# Results

https://user-images.githubusercontent.com/12452269/190680318-78913406-b134-4e1f-ae07-4f93e4b822d6.mov

# Libraries used
- Material
- Koin for DI
- Retrofit
- OkHttp
- Compose


