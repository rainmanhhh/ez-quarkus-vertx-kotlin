package ez.vertx

import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import javax.annotation.PostConstruct
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class VertxScope : CoroutineScope {
  @Inject
  lateinit var vertx: Vertx

  private lateinit var cs: CoroutineContext

  @PostConstruct
  private fun init() {
    cs = vertx.dispatcher()
  }

  override val coroutineContext: CoroutineContext get() = cs
}