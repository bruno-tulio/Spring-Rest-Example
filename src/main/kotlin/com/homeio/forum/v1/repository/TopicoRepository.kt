package com.homeio.forum.v1.repository

import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.query.TopicoQuery
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long>, TopicoQuery