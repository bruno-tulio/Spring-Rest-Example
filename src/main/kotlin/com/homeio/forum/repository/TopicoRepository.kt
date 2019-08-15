package com.homeio.forum.repository

import com.homeio.forum.model.Topico
import com.homeio.forum.repository.query.TopicoQuery
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long>, TopicoQuery