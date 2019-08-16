package com.homeio.forum.exception

import java.lang.RuntimeException

class TokenExpiredException(msg: String) : RuntimeException(msg)