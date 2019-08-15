package com.homeio.forum.repository.excption

import java.lang.RuntimeException

class EntityNotFoundException(msg: String) : RuntimeException(msg)