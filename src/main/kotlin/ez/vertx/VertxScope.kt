package ez.vertx

import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class VertxScope : CoroutineScope {
  @Inject
  lateinit var vertx: Vertx

  private val cs = ThreadLocal.withInitial {
    vertx.dispatcher()
  }

  override val coroutineContext: CoroutineContext get() = cs.get()
}