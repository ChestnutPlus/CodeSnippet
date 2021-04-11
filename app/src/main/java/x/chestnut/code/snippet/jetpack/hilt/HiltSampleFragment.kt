package x.chestnut.code.snippet.jetpack.hilt

import android.util.Log
import android.view.View
import androidx.hilt.Assisted
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.jetpack.hilt.HiltSampleFragment.Companion.TAG
import javax.inject.Inject
import javax.inject.Qualifier

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/13 12:17
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
@AndroidEntryPoint
class HiltSampleFragment : ScrollBaseFragment() {

    @Inject
    lateinit var truck: Truck

    companion object {
        const val TAG = "HiltSampleFragment"
    }

    override val actionBarTitle: String
        get() = "Hilt Sample"

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        addView("TruckSample") { truck.deliver() }
    }
}

class Truck @Inject constructor(var driver: Driver) {

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun deliver() {
        gasEngine.start()
        electricEngine.start()
        Log.d(TAG, "${driver.name()} Driver Truck to delivering cargo.")
        gasEngine.shutdown()
        electricEngine.shutdown()
    }
}

class Driver @Inject constructor() {
    fun name(): String {
        return "Tom"
    }
}

interface Engine {
    fun start()
    fun shutdown()
}

class GasEngine @Inject constructor() : Engine {
    override fun start() {
        Log.d(TAG, "Gas engine start.")
    }

    override fun shutdown() {
        Log.d(TAG, "Gas engine shutdown.")
    }
}

class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        Log.d(TAG, "Electric engine start.")
    }

    override fun shutdown() {
        Log.d(TAG, "Electric engine shutdown.")
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {
    @BindGasEngine
    @Binds
    abstract fun bindGasEngine(gasEngine: GasEngine): Engine

    @BindElectricEngine
    @Binds
    abstract fun bindElectricEngine(electricEngine: ElectricEngine): Engine
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindGasEngine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindElectricEngine


