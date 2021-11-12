//[RacciCore](../../../index.md)/[me.racci.raccicore.scheduler](../index.md)/[ICoroutineScheduler](index.md)/[cancelTask](cancel-task.md)

# cancelTask

[jvm]\
abstract suspend fun [cancelTask](cancel-task.md)(taskID: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Attempts to remove a task from the scheduler.

#### Return

If the task was successfully cancelled and removed.

## Parameters

jvm

| | |
|---|---|
| taskID | The task to remove and cancel. |