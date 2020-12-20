package x.chestnut.code.snippet.kotlin.coroutine

import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.*
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.kotlin.KotlinSampleFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/19 21:40
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class KotlinCoroutineFragment : ScrollBaseFragment() {

    private val mainScope = MainScope()
    private var job: Job? = null

    companion object {
        private val TAG = KotlinSampleFragment::class.java.name
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
        job?.cancel()
    }

    override fun onLazyViewCreate(rootView: View?) {
        super.onLazyViewCreate(rootView)
        addView("协程 - CoroutineBuilders | launch") {
            //1. 创建一个顶级协程，生命周期结束时候需要主动 cancel，避免内存泄漏
            job = GlobalScope.launch(Dispatchers.Main) {
                Log.d(TAG, "launch, 1, thread: ${Thread.currentThread().name}")
                val token = firstRequestToken()
                val postMsg = secondCreatePostMsg(token)
                val result = thirdExePost(postMsg)
                Log.d(TAG, "launch, 2, thread: ${Thread.currentThread().name}, " +
                        "result: $result")
                //can update because this coroutine run on Dispatchers.Main
                Toast.makeText(context, "result: $result", Toast.LENGTH_SHORT).show()
            }
            //2. Android 中推介这个：创建一个统一的协程，在 onDestroy 中释放
            mainScope.launch {
                Log.d(TAG, "launch, 1, thread: ${Thread.currentThread().name}")
                val token = firstRequestToken()
                val postMsg = secondCreatePostMsg(token)
                val result = thirdExePost(postMsg)
                Log.d(TAG, "launch, 2, thread: ${Thread.currentThread().name}, " +
                        "result: $result")
                //can update because this coroutine run on Dispatchers.Main
                Toast.makeText(context, "result: $result", Toast.LENGTH_SHORT).show()
            }
        }
        addView("协程 - CoroutineBuilders | withContext") {
            //withContext，在当前协程中挂起代码块
            mainScope.launch {
                Log.d(TAG, "withContext, 1, thread: ${Thread.currentThread().name}")
                withContext(Dispatchers.IO) {
                    delay(2000)
                    Log.d(TAG, "withContext, 2, delay done, thread: ${Thread.currentThread().name}")
                }
                Log.d(TAG, "withContext, 3, thread: ${Thread.currentThread().name}")
            }
        }
        addView("协程 - CoroutineBuilders | async ") {
            mainScope.launch {
                //1.模拟一个耗时的协程任务
                Log.d(TAG, "async, 1, thread: ${Thread.currentThread().name}")
                val deferred = mainScope.async(Dispatchers.IO) {
                    Log.d(TAG, "async, 2, thread: ${Thread.currentThread().name}")
                    delay(3000L)
                    Log.d(TAG, "async, 3, thread: ${Thread.currentThread().name}")
                    "ok"
                }
                //2.此处继续执行其他
                Log.d(TAG, "async, 4, thread: ${Thread.currentThread().name}")
                val result = deferred.await() //此处挂起协程
                Log.d(TAG, "async, 5, thread: ${Thread.currentThread().name}")
                withContext(Dispatchers.Main) { //挂起协程切换UI线程，展示结果
                    Log.d(TAG, "async, 6, thread: ${Thread.currentThread().name}")
                    Toast.makeText(context, "async: $result", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun firstRequestToken(): String {
        Log.d(TAG, "firstRequestToken, 1, thread: ${Thread.currentThread().name}")
        delay(2000)
        Log.d(TAG, "firstRequestToken, 2, thread: ${Thread.currentThread().name}")
        return "Token"
    }

    private suspend fun secondCreatePostMsg(token: String) : String {
        Log.d(TAG, "secondCreatePost, 1, thread: ${Thread.currentThread().name}")
        delay(2000)
        Log.d(TAG, "secondCreatePost, 2, thread: ${Thread.currentThread().name}")
        return "Create Post Done"
    }

    private suspend fun thirdExePost(postMsg: String) : String {
        Log.d(TAG, "thirdExePost, 1, thread: ${Thread.currentThread().name}")
        delay(2000)
        Log.d(TAG, "thirdExePost, 2, thread: ${Thread.currentThread().name}")
        return "Success"
    }
}
