package com.example.lqqq.kotlincloud

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import kotlin.coroutines.*
import kotlin.math.roundToInt

// () -> unit 匿名函数
// 一个函数式应用通常由三大类函数构成：变换transform、过滤filter、合并combine
class Learn {
    // 静态
    companion object {

    }
    // 基础
    fun base(){
        // 查看kotlin字节码，shift两次，输入show kotlin
        // range表达式 1..4  前闭后开
        val range = listOf(1..4)
        // 字符串操作
        val name = "hello,happy".substring(0 until 10).replace("e","a").split(",")
        // 四舍五入
        val num = 7.998.roundToInt()
        // 四舍五入保留X位
        val num1 = "%.2f".format(num)
    }
    // 标准库函数
    fun standard(){
        // 隐式调用 this，返回当前接收者
        val str = "7890".apply {
            replace("7","5")
        }
        // 返回值为最后一句表达式
        val str2 = "123".let {
            it.split("2")
        }
        // 适合作用同一个原始对象，类似let，但是返回接收者
        val str3 = "567".also {
            it.split("2")
        }
        // 隐式调用 this，返回lambda表达式
        val b = "qwe".run {
            substring(1)
        }.run {  }
        // with是run变体，传入参数
        val c = with("wsx") {
            substring(1)
        }
    }
    // 扩展函数
    fun expand(){
        // 扩展函数(例：给String函数添加扩展)
        fun String.expandFun(str: String) = this + str

        // 给超类添加扩展(kotlin中Any是所有类的超类)
        fun Any.logName() {
            Log.d("---", "$this")
        }

        // 泛型扩展函数
        fun <T> T.tName(): T {
            return this
        }

        // 可空类型扩展
        fun String?.nullGet(default: String) = this ?: default
    }

    // 变换函数（例map）过滤函数（filter）合并函数（zip,fold）
    fun mapFun() {
        val number = listOf(1, 2, 3, 4)
        val list = listOf("name", "age", "sex", "height")
        val newList = list.map { str -> "$str  xxx" }
            .map { strXXX -> "$strXXX yyy" }
        // flatMap 操作子集合中的元素，返回一个包含所有子元素（变换后）的集合
        val flatList = listOf(listOf("123"), listOf("456"), listOf("789")).flatMap { it + 1 }
        // 取出所有子元素组成一个新的集合
        listOf(listOf("123"), listOf("456"), listOf("789")).flatten()
        // 返回true则添加到新集合
        val filterList = list.filter { it.contains("s") }
        // 返回键值对集合再转map
        val zipList = number.zip(list).toMap()
        // acc为初始累加器的值(第一次为1，第二次为（1+1*2）=3)，i是集合元素值
        val folder = number.fold(1) { acc, i -> acc + i * 2 }

        // 判断是否是素数
        fun Int.isPrime(): Boolean {
            return (2..this).map { this % it }.none { it == 0 }
        }
        // 惰性集合，按需产生（kotlin自带；序列-sequence）
        // generateSequence初始值（seed）以及迭代器决定每一个元素的值，例：取前1000个素数
        generateSequence(2) { it + 1 }.filter { it.isPrime() }.take(1000).toList()
    }

    // 协程
    fun coroutineFun() {
        /*
        协程业务框架层
         */
        /**
         * 协程调度器--Dispatchers
         * Dispatchers.Main(主线程：处理UI交互和轻量级任务、调用suspend函数、UI函数、更新liveData)
         * Dispatchers.IO(非主线程：转为磁盘和网络IO进行了优化、数据库、文件读写、网络处理)
         * Dispatchers.Main(非主线程：专为CPU密集型任务进行了优化、数组排序、JSON数据解析、处理差异判断)
         */
        /**
         * 协程作用域--coroutineScope（跟踪协程，可以取消由它启动的协程）
         * GlobalScope 生命周期是process级别的，即使Activity或者Fragment销毁了，携程仍在执行
         * MainScope 在Activity中使用，可以在onDestroy中取消协程
         * viewModelScope 只能在viewModel中使用，和viewModel生命周期绑定
         * lifecycleScope 只能在Activity和Fragment中使用，会绑定生命周期
         */
        /**
         * 协程启动模式
         * DEFAULT 协程创建后，立即开始调度，在调度前如果携程被取消，其将直接进入响应取消的状态
         * ATOMIC 协程创建后，立即开始调度，协程执行到第一个挂起点之前不响应取消
         * LAZY 只有协程被需要时，包括主动调用协程的start、join或者await等函数时才会开始调度，
         * 如果调度前就被取消，那么该协程将直接进入异常结束状态
         * UNDISPATCHED 协程创建后立即在当前函数调用栈中执行，直到遇到第一个挂起的点
         */
        /**
         * 协程作用域构建器
         * runBlocking是常规函数，会阻塞线程
         * coroutineScope是挂起函数，只会挂起不会阻塞;一个协程取消了，其他兄弟协程也会被取消
         * supervisorScope一个协程取消了，不会影响其他协程
         * 取消作用域会取消子协程
         * 超时任务withTimeout、withTimeoutOrNull
         */
        /**
         * Job对象生命周期
         * 新创建（New）、活跃（Active）、完成中（completing）、已完成（completed）、
         * 取消中（canceling）、已取消（cancelled）
         */
        /**
         * 协程上下文组成
         * Job  控制协程生命周期
         * CoroutineDispatcher  向合适的线程分发任务
         * CoroutineName    协程的名称，调试时很有用
         * CoroutineExceptionHandler    处理未被捕获的异常
         */
        GlobalScope.launch(Dispatchers.Main) {
            val user = withContext(Dispatchers.IO) {  }
            //下一步 set text
        }
        val mainScope = MainScope()
        mainScope.launch {
            // 省略了withContext，自动检测并切换了IO
        }
        mainScope.cancel()

        // 将主线程包装成协程
        runBlocking {
            val job1 = launch { }
            job1.join() // 等待执行完后继续
            val job2 = launch { }
            val job3 = launch { }
            val job4 = async { 1 }
//                val num1 = job4.await() // 等待执行完后继续
            val job5 = async { 2 }
            val job6 = async { 3 }
            val number = job4.await() + job5.await() + job6.await() // 组合并发
            // 协程作用域构建器
            coroutineScope { }
            runBlocking { }
            supervisorScope { }
            // 异常捕获:supervisorJob不会传递异常给父级。
            // 子协程发生异常，传递给父协程，父协程取消所有子协程后才处理异常
            val handler = CoroutineExceptionHandler { context, exception -> }
            val scope = CoroutineScope(Job() + Dispatchers.Main + handler)
            scope.launch(Dispatchers.IO) { }
        }
        /*
        协程基础设施层
         */
        // 协程体（continuation保存挂起点）
        val continuation = suspend {
            999
        }.createCoroutine(object : Continuation<Int> {
            // 协程上下文
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            // 回调
            override fun resumeWith(result: Result<Int>) {
                Log.e("---", "$result")
            }

        })
        // 启动协程
        continuation.resume(Unit)
    }

    /**
     * Flow-异步流
     * 类似序列的一种冷流，Flow构建器中的代码直到流被收集的时候才执行
     * emit -> Flow -> collect
     * 使用缓冲和flowOn处理背压
     */
    fun flowFun() {
        suspend fun simpleFlow() = flow<Int> {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }.flowOn(Dispatchers.IO)//指定发射流的上下文

        fun simple() = runBlocking {
            simpleFlow()
//                    .cancellable() //流的取消检测，影响效率
//                    .buffer(20) //缓冲
//                    .flowOn(Dispatchers.Default)
//                    .conflate() // 处理第一个和最新值，中间的会被过滤
//                    .collectLatest {  } // 重新发送和处理最后一个值
                .collect { value ->
                    Log.d("---", "$value")
                    if (value == 3) cancel()//流的取消
                }
            simpleFlow()
                .onEach { value -> Log.d("---", "$value") }//过度操作
                .launchIn(CoroutineScope(Dispatchers.IO))//替换collect在指定协程作用域中收集数据
                .join()
        }

        suspend fun performRequest(request: Int): String {
            delay(1000)
            return "request"
        }

        fun requestFun() = runBlocking {
            (1..5).asFlow().map { request -> performRequest(request = request) }
                .collect { value -> println(value) }

            (1..5).asFlow().transform { request -> // transform多次转换发送
                emit("transform: $request") // 第一次发射
                emit(performRequest(request)) // 第二次发射
            }
//                    .take(2)//限制收集长度
                .collect { value -> println(value) } // 两次均能收集

            // 展平操作符
            fun strFlow(i: Int) = flow<String> {
                emit("$i str1")
                delay(200)
                emit("$i str2")
            }.catch { e: Throwable -> println(e) }//捕获异常

            val flow1 = (1..3).asFlow().onEach { delay(100) }
                // flatMapConcat 连接模式 将Flow<Flow<String>>展平成Flow<String>
                .flatMapConcat { strFlow(it) }
//                    .flatMapMerge {  } 合并模式
//                    .flatMapLatest {  } 最新模式
                .onCompletion {
                    println("完成")
                } // 完成,可以获取到异常信息，但不能捕获
                .collect {}
        }
    }

    /**
     * Channel-通道
     * 并发安全的队列，它可以用来连接协程，实现不同协程之间的通信
     */
    fun channelFun() = runBlocking {
        val channel = Channel<Int>(Channel.UNLIMITED)//设置缓冲大小，默认0
        // 生产者
        val producer = GlobalScope.launch {
            var i = 0
            while (true) {
                delay(1000)
                channel.send(++i)
            }
        }
        // 消费者
        val consumer = GlobalScope.launch {
//                while (true){
//                    delay(1000)
//                    val element = channel.receive()// 当缓冲区满时，send挂起，receive之后send继续
//                }
            val iterator = channel.iterator() // iterator获取
            while (iterator.hasNext()) {
                iterator.next()
            }

            for (element in channel) { // for in获取
                println(element)
            }
        }
        joinAll(producer, consumer)

        val receiveChannel = GlobalScope.produce { // 快速构建生产者协程
            repeat(100) {
                delay(1000)
                send(it)
            }
        }
        val consumer2 = GlobalScope.launch {
            for (element in receiveChannel) {
                println(element)
            }
        }
        consumer2.join()

        val sendChannel: SendChannel<Int> = GlobalScope.actor { // 快速构建消费者协程
            while (true) {
                val element = receive()
                println(element)
            }
        }
        val producer2 = GlobalScope.launch {
            repeat(20) {
                delay(1000)
                sendChannel.send(it)
            }
        }
        producer2.join()

        val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED) // 多个接收端都能接收到
        val receiveChannel1 = broadcastChannel.openSubscription()// 订阅

        // await多路复用
        val response = select<String> { // 谁先执行完返回谁的结果
            async { }.onAwait { "response" }
            async { }.onAwait { "response1" }
        }
        // channel复用
        val channel1 = Channel<Int>()
        val channel2 = Channel<Int>()
        val result = select<Int> {
            channel1.onReceive { it }
            channel2.onReceive { it }
        }
        // SelectClause:    SelectClause0、SelectClause1、SelectClause2（返回值数量）
        // 没有返回值的：SelectClause0
        val job1 = GlobalScope.launch { }
        val job2 = GlobalScope.launch { }
        select<Unit> {
            job1.onJoin { println("job1") }
            job2.onJoin { println("job2") }
            //带回调的
            GlobalScope.launch {
                channel1.onSend(200) {

                }
            }
        }
    }
}
