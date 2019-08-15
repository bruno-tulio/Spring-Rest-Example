package com.homeio.forum.v1.repository.impl

import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.filter.TopicoFilter
import com.homeio.forum.v1.repository.query.TopicoQuery
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.persistence.criteria.Predicate

class TopicoRepositoryImpl : TopicoQuery{

    @Autowired
    private lateinit var manager: EntityManager

    override fun findAllFilter(topicoFilter: TopicoFilter): List<Topico>? {

        val criteriaBuilder = manager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Topico::class.java)
        val root = criteriaQuery.from(Topico::class.java)


        val predicates = mutableListOf<Predicate>()

        topicoFilter.titulo?.let {
            predicates.add(criteriaBuilder.equal(root.get<String>("titulo"), it))
        }

        topicoFilter.mensagem?.let {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get<String>("mensagem")), "%${it.toUpperCase()}%"))
        }

        topicoFilter.dataCriacao?.let {
            predicates.add(criteriaBuilder.between(root.get<LocalDateTime>("dataCriacao"),  it.atTime(0, 0, 0), it.atTime(23,59, 59)))
        }

        criteriaQuery.where(*predicates.toTypedArray())

        val query = manager.createQuery(criteriaQuery)


        return query.resultList

    }


}