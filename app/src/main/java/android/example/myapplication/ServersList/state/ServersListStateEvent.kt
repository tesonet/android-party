package android.example.myapplication.ServersList.state

sealed class ServersListStateEvent {

    class GetServersEvent: ServersListStateEvent()
    class None: ServersListStateEvent()

}