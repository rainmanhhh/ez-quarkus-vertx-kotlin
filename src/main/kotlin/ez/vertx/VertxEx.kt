package ez.vertx

import kotlinx.coroutines.Deferred
import java.util.concurrent.CompletableFuture

@Suppress("EXPERIMENTAL_API_USAGE")
fun <T> Deferred<T>.toJdkFuture(): CompletableFuture<T> {
  val f = CompletableFuture<T>()
  invokeOnCompletion {
    val e = getCompletionExceptionOrNull()
    if (e==null) f.complete(getCompleted())
    else f.completeExceptionally(e)
  }
  return f
}