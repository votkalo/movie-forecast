package com.vo.movie.forecast.backend.core.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ConstraintException(override val message: String) : RuntimeException(message)