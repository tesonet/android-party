package com.demo.androidparty.ui.list.mapper;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/demo/androidparty/ui/list/mapper/ServerListMapper;", "", "map", "Lcom/demo/androidparty/dto/Server;", "server", "Lcom/demo/androidparty/dto/ServerModel;", "app_debug"})
public abstract interface ServerListMapper {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.demo.androidparty.dto.Server map(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.dto.ServerModel server);
}