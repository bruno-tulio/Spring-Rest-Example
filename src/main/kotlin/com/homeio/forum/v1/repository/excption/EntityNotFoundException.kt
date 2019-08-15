package com.homeio.forum.v1.repository.excption

import java.lang.RuntimeException

class EntityNotFoundException(msg: String) : RuntimeException(msg)