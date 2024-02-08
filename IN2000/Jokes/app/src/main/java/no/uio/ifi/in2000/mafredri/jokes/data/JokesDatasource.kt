package no.uio.ifi.in2000.mafredri.jokes.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.jokes.model.Joke
import no.uio.ifi.in2000.mafredri.jokes.model.Jokes


class JokesDatasource {
    private val url: String = "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/ukesoppgaver/jokes.json"

    fun fetchJokes(): List<Joke> {
        val result: Jokes
        runBlocking {
            val httpResponse = JokesClient.client.get(url)
            result = httpResponse.body()
        }
        return result.jokes
    }
}
