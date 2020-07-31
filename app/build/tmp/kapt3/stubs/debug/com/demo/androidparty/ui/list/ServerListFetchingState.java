package com.demo.androidparty.ui.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/demo/androidparty/ui/list/ServerListFetchingState;", "", "()V", "Failed", "Started", "Success", "Lcom/demo/androidparty/ui/list/ServerListFetchingState$Started;", "Lcom/demo/androidparty/ui/list/ServerListFetchingState$Failed;", "Lcom/demo/androidparty/ui/list/ServerListFetchingState$Success;", "app_debug"})
public abstract class ServerListFetchingState {
    
    private ServerListFetchingState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/demo/androidparty/ui/list/ServerListFetchingState$Started;", "Lcom/demo/androidparty/ui/list/ServerListFetchingState;", "()V", "app_debug"})
    public static final class Started extends com.demo.androidparty.ui.list.ServerListFetchingState {
        public static final com.demo.androidparty.ui.list.ServerListFetchingState.Started INSTANCE = null;
        
        private Started() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/demo/androidparty/ui/list/ServerListFetchingState$Failed;", "Lcom/demo/androidparty/ui/list/ServerListFetchingState;", "reason", "", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "app_debug"})
    public static final class Failed extends com.demo.androidparty.ui.list.ServerListFetchingState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String reason = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getReason() {
            return null;
        }
        
        public Failed(@org.jetbrains.annotations.NotNull()
        java.lang.String reason) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/demo/androidparty/ui/list/ServerListFetchingState$Success;", "Lcom/demo/androidparty/ui/list/ServerListFetchingState;", "data", "", "Lcom/demo/androidparty/dto/Server;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "app_debug"})
    public static final class Success extends com.demo.androidparty.ui.list.ServerListFetchingState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.demo.androidparty.dto.Server> data = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.demo.androidparty.dto.Server> getData() {
            return null;
        }
        
        public Success(@org.jetbrains.annotations.NotNull()
        java.util.List<com.demo.androidparty.dto.Server> data) {
            super();
        }
    }
}