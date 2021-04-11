package x.chestnut.code.snippet.kotlin.flow

import android.util.Log
import android.view.View
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import x.chestnut.code.snippet.base.CoroutineBaseFragment
import x.chestnut.code.snippet.base.ScrollBaseFragment
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/19 21:44
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class KotlinFlowFragment : CoroutineBaseFragment() {

    companion object {
        private const val TAG = "Flow"
    }

    private var start: Long = 0

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        addView("flow") {
            mainScope.launch {
                flow<Int> {
                    for( i in 1..10) {
                        delay(500)
                        viewLog(TAG, "emit $i, ${Thread.currentThread().name}")
                        emit(i)
                    }}.flowOn(Dispatchers.IO)
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("flowOf") {
            mainScope.launch {
                flowOf("Today is Sunday")
                        .flowOn(Dispatchers.Main)
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("asFlow") {
            mainScope.launch {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { delay(100) }
                        .flowOn(Dispatchers.Main)
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("线程切换") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { delay(100) }
                        .map { it + 2 }
                        .flowOn(Dispatchers.Main)
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("异常捕获-try-catch") {
            mainScope.launch(Dispatchers.IO) {
                try {
                    listOf(1, 2, 3, 4, 5).asFlow()
                            .onEach { delay(100) }
                            .map { it + 2 }
                            .flowOn(Dispatchers.Main)
                            .collect {
                                if (it == 5) {
                                    throw Exception("haha")
                                }
                                viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                            }
                } catch (e: Exception) {
                    viewLog(TAG, "err, ${e.message}, ${Thread.currentThread().name}")
                } finally {
                    viewLog(TAG, "finally, ${Thread.currentThread().name}")
                }
            }
        }
        addView("异常捕获-onCompletion") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { delay(100) }
                        .map {
                            it + 2
                            if (it == 5) {
                                throw Exception("haha")
                            }
                        }
                        .flowOn(Dispatchers.Main)
                        .onCompletion { cause ->
                            if (cause != null) {
                                viewLog(TAG, "onCompletion, err: ${cause.message}")
                            } else {
                                viewLog(TAG, "onCompletion done")
                            }
                        }
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("异常捕获-catch") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { delay(100) }
                        .map {
                            it + 2
                            if (it == 5) {
                                throw Exception("haha")
                            }
                        }
                        .flowOn(Dispatchers.Main)
                        .onCompletion { cause ->
                            if (cause != null) {
                                viewLog(TAG, "onCompletion, err: ${cause.message}")
                            } else {
                                viewLog(TAG, "onCompletion done")
                            }
                        }
                        .catch { cause ->
                            viewLog(TAG, "catch, err: ${cause.message}")
                        }
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("onCompletion") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { delay(100) }
                        .map { it + 2 }
                        .flowOn(Dispatchers.Main)
                        .onCompletion { viewLog(TAG, "onCompletion done") }
                        .collect {
                            viewLog(TAG, "collect $it, ${Thread.currentThread().name}")
                        }
            }
        }
        addView("Backpressure-buffer") {
            mainScope.launch(Dispatchers.IO) {
                val totalTime = measureTimeMillis {
                    (1..3).asFlow()
                            .onStart { start = System.currentTimeMillis() }
                            .onEach {
                                delay(100)
                                viewLog(TAG, "Emit $it starts (${System.currentTimeMillis() - start}ms) ")
                            }
                            .buffer()
                            .collect {
                                viewLog(TAG, "Collect $it starts (${System.currentTimeMillis() - start}ms) ")
                                delay(500)
                                viewLog(TAG, "Collect $it ends (${System.currentTimeMillis() - start}ms) ")
                            }
                }
                viewLog(TAG, "totalTime: $totalTime ms")
            }
        }
        addView("Backpressure-conflate") {
            mainScope.launch(Dispatchers.IO) {
                val totalTime = measureTimeMillis {
                    (1..3).asFlow()
                            .onStart { start = System.currentTimeMillis() }
                            .onEach {
                                delay(100)
                                viewLog(TAG, "Emit $it starts (${System.currentTimeMillis() - start}ms) ")
                            }
                            .conflate()
                            .collect {
                                viewLog(TAG, "Collect $it starts (${System.currentTimeMillis() - start}ms) ")
                                delay(500)
                                viewLog(TAG, "Collect $it ends (${System.currentTimeMillis() - start}ms) ")
                            }
                }
                viewLog(TAG, "totalTime: $totalTime ms")
            }
        }
        addView("retry") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { if (it == 3) throw RuntimeException("err, $it") }
                        .retry(2) {
                            if (it is RuntimeException) {
                                return@retry true
                            }
                            false
                        }
                        .onEach { viewLog(TAG, "Emit $it") }
                        .catch { viewLog(TAG, "catch, ${it.message}") }
                        .collect {}
            }
        }
        addView("retryWhen") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onEach { if (it == 3) throw RuntimeException("err, $it") }
                        //重试，直到某个条件满足了后，才停止
                        .retryWhen { _, attempt ->
                            attempt < 2
                        }
                        .onEach { viewLog(TAG, "Emit $it") }
                        .catch { viewLog(TAG, "catch, ${it.message}") }
                        .collect {}
            }
        }
        addView("FlowLifecycle") {
            mainScope.launch(Dispatchers.IO) {
                listOf(1, 2, 3, 4, 5).asFlow()
                        .onStart { viewLog(TAG, "onStart $it") }
                        .onEach { it + 2 }
                        .onCompletion { viewLog(TAG, "onCompletion $it") }
                        .collect {}
            }
        }
        addView("flatMapMerge 并行") {
            mainScope.launch(Dispatchers.IO) {
                val result = arrayListOf<Int>()
                for (index in 1..100){
                    result.add(index)
                }
                result.asFlow()
                        .flatMapMerge {
                            flow {
                                emit(it)
                            }.flowOn(Dispatchers.IO)
                        }
                        .collect { viewLog(TAG, "flatMapMerge $it") }
            }
        }
        addView("transform") {
            mainScope.launch(Dispatchers.IO) {
                (1..5).asFlow()
                        .transform {
                            emit(it)
                            delay(100)
                            emit(it * 2)
                        }
                        .collect { viewLog(TAG, "collect $it") }
            }
        }
        addView("map") {
            mainScope.launch(Dispatchers.IO) {
                (1..5).asFlow()
                        .map { "map: ${it + 2}" }
                        .collect { viewLog(TAG, "collect $it") }
            }
        }
        addView("take") {
            mainScope.launch(Dispatchers.IO) {
                (1..5).asFlow()
                        .take(3)
                        .collect { viewLog(TAG, "collect $it") }
            }
        }
        addView("zip") {
            mainScope.launch(Dispatchers.IO) {
                val flowA = (1..3).asFlow()
                val flowB = flowOf("one", "two", "three","four","five")
                        .onEach { delay(100) }
                flowA.zip(flowB) { a, b -> "$a and $b" }
                        .collect { viewLog(TAG, "collect $it") }
            }
        }
        addView("combine") {
            mainScope.launch(Dispatchers.IO) {
                val flowA = (1..3).asFlow().onEach { delay(100)  }
                val flowB = flowOf("one", "two").onEach { delay(200)  }
                flowA.combine(flowB) { a, b -> "$a and $b" }
                        .collect { viewLog(TAG, "collect $it") }
            }
        }
        addView("flattenMerge") {
            mainScope.launch(Dispatchers.IO) {
                val flowA = (1..5).asFlow().onEach { delay(100) }
                val flowB = flowOf("one", "two", "three","four","five")
                        .onEach { delay(200) }
                flowOf(flowA, flowB)
                        .flattenMerge(2)
                        .collect{ viewLog(TAG, "collect $it") }
            }
        }
        addView("flatMapConcat") {
            mainScope.launch(Dispatchers.IO) {
                (1..2).asFlow()
                        .onStart { start = System.currentTimeMillis() }
                        .flatMapConcat {
                            flow {
                                emit("before: $it")
                                delay(500)
                                emit("after: $it")
                            }
                        }
                        .collect{ viewLog(TAG, "collect $it at " +
                                "${System.currentTimeMillis() - start} ms from start") }
            }
        }
        addView("flatMapMerge") {
            mainScope.launch(Dispatchers.IO) {
                (1..2).asFlow()
                        .onStart { start = System.currentTimeMillis() }
                        .flatMapMerge {
                            flow {
                                emit("before: $it")
                                delay(500)
                                emit("after: $it")
                            }
                        }
                        .collect{ viewLog(TAG, "collect $it at " +
                                "${System.currentTimeMillis() - start} ms from start") }
            }
        }

    }
}
