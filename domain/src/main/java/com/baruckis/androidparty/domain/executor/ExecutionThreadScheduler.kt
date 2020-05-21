package com.baruckis.androidparty.domain.executor

import io.reactivex.Scheduler

interface ExecutionThreadScheduler {
    val scheduler: Scheduler
}