package com.abclever.integrationtesting.http

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.function.UnaryOperator
import org.json.JSONObject

val emptyOp = { it: HttpRequest.Builder -> it }

fun get(
  url: String,
  op: (HttpRequest.Builder) -> HttpRequest.Builder = emptyOp,
  handler: HttpResponse.BodyHandler<String> = HttpResponse.BodyHandlers.ofString()
): HttpResponse<String> {
  return processReq(url, op, { it.GET() }, handler)
}

fun delete(
  url: String,
  op: (HttpRequest.Builder) -> HttpRequest.Builder = emptyOp,
  handler: HttpResponse.BodyHandler<String> = HttpResponse.BodyHandlers.ofString()
): HttpResponse<String> {
  return processReq(url, op, { it.DELETE() }, handler)
}

fun post(
  url: String,
  body: Map<String, Any> = mapOf(),
  op: (HttpRequest.Builder) -> HttpRequest.Builder = emptyOp,
  handler: HttpResponse.BodyHandler<String> = HttpResponse.BodyHandlers.ofString(),
  publisher: HttpRequest.BodyPublisher = getReqBodyHandler(body)
): HttpResponse<String> {
  return processReq(url, op, { it.POST(publisher) }, handler)
}

fun put(
  url: String,
  body: Map<String, Any> = mapOf(),
  op: (HttpRequest.Builder) -> HttpRequest.Builder = emptyOp,
  handler: HttpResponse.BodyHandler<String> = HttpResponse.BodyHandlers.ofString()
): HttpResponse<String> {
  return processReq(url, op, { it.PUT(getReqBodyHandler(body)) }, handler)
}

internal fun getReqBodyHandler(body: Map<String, Any>)
: HttpRequest.BodyPublisher {
  return HttpRequest.BodyPublishers.ofString(JSONObject(body).toString())
}

internal fun processReq(
  url: String,
  op: UnaryOperator<HttpRequest.Builder>,
  methodUnary: UnaryOperator<HttpRequest.Builder>,
  handler: HttpResponse.BodyHandler<String> = HttpResponse.BodyHandlers.ofString()
): HttpResponse<String> {
  return HttpClient.newHttpClient().send(
    methodUnary.apply(op.apply((HttpRequest.newBuilder(URI(url)).GET()))).build(),
    handler
  )
}
