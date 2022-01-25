package com.example.rxjavatest

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun rxTest1() {
        Flowable.just("Hello World").subscribe {
            println(it)
        }
    }

    @Test
    fun rxTest2() {
        val myObservable = Observable.create<Long> { emitter ->
            while (!emitter.isDisposed) {
                val time = System.currentTimeMillis()
                println("emitter is disposed: ${emitter.isDisposed}")
                emitter.onNext(time)
                if (time % 2 != 0L) {
                    emitter.onError(IllegalStateException("Odd millisecond!"))
                    break
                }

            }
        }
        println("미구독")
        val myConsumer = Consumer<Long> { println("accept success: $it") }
        val errorConsumer = Consumer<Throwable> { println("accept error: $it") }
//        val mySubscriber = object : Subscriber<Long> {
//            override fun onComplete() {
//                println("onComplete")
//            }
//
//            override fun onSubscribe(s: Subscription?) {
//                println("onSubscribe")
//            }
//
//            override fun onNext(t: Long?) {
//                println("onNext: $t")
//            }
//
//            override fun onError(t: Throwable?) {
//                println("onError: $t")
//            }
//        }
        println("구독시작")
        myObservable.subscribe(myConsumer, errorConsumer)

    }

    @Test
    fun rxTest3() {
        val flow = Flowable.range(1, 5)
            .map { v: Int -> v * v }
            .filter { v: Int -> v % 3 == 1 }
        println("시작")
        flow.subscribe {
            println(it)
        }
        println("끝")
    }
}