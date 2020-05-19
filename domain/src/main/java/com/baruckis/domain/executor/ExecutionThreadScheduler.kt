package com.baruckis.domain.executor

import io.reactivex.Scheduler

interface ExecutionThreadScheduler {
    val scheduler: Scheduler
}