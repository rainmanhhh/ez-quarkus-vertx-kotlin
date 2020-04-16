package ez.resteasy

import io.vertx.core.Future
import org.jboss.resteasy.spi.AsyncResponseProvider
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

class VertxFutureResponseProvider : AsyncResponseProvider<Future<*>> {
  override fun toCompletionStage(f: Future<*>): CompletionStage<*> {
    val cs = CompletableFuture<Any?>()
    f.onComplete {
      if (it.failed()) cs.completeExceptionally(it.cause())
      else cs.complete(it.result())
    }
    return cs
  }
}