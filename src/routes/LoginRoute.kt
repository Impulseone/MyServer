package com.mycorp.routes

import com.mycorp.data.checkPasswordForEmail
import com.mycorp.data.requests.AccountRequest
import com.mycorp.data.responses.SimpleResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.loginRoute() {
    route("/login") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch(e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isPasswordCorrect = checkPasswordForEmail(request.email, request.password)
            if(isPasswordCorrect) {
                call.respond(OK, SimpleResponse(true, "Your are now logged in!"))
            } else {
                call.respond(OK, SimpleResponse(false, "The E-Mail or password is incorrect"))
            }
        }
    }
}