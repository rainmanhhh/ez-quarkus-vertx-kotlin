package ez.resteasy

import ez.vertx.toJdkFuture
import kotlinx.coroutines.Deferred
import org.jboss.resteasy.spi.AsyncResponseProvider
import java.util.concurrent.CompletionStage

class DeferredResponseProvider : AsyncResponseProvider<Deferred<*>> {
  @Suppress("EXPERIMENTAL_API_USAGE")
  override fun toCompletionStage(d: Deferred<*>): CompletionStage<*> {
    return d.toJdkFuture()
  }
}