package com.homeio.forum.v1.repository.impl

import com.homeio.forum.v1.dto.TopicoDto
import com.homeio.forum.v1.mapper.TopicoMapper
import com.homeio.forum.v1.model.Topico
import com.homeio.forum.v1.repository.filter.TopicoFilter
import com.homeio.forum.v1.repository.query.TopicoQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.util.Streamable
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.persistence.criteria.*

class TopicoRepositoryImpl : TopicoQuery{


    @Autowired
    private lateinit var manager: EntityManager

    override fun findAllFilter(topicoFilter: TopicoFilter): List<Topico> {

        val criteriaBuilder = manager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Topico::class.java)
        val root = criteriaQuery.from(Topico::class.java)

        predicateFilter(topicoFilter, criteriaBuilder, root, criteriaQuery)

        val query = manager.createQuery(criteriaQuery)


        return query.resultList

    }

    override fun findAllFilterPageable(topicoFilter: TopicoFilter, pageable: Pageable): Page<TopicoDto> {

        val criteriaBuilder = manager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Topico::class.java)
        val root = criteriaQuery.from(Topico::class.java)

        predicateFilter(topicoFilter, criteriaBuilder, root, criteriaQuery)
        addOrder(pageable, criteriaBuilder, root, criteriaQuery)


        val indexInicial = pageable.pageNumber * pageable.pageSize
        val indexFinal = pageable.pageSize

        val query = manager.createQuery(criteriaQuery)
        query.setFirstResult(indexInicial)
        query.setMaxResults(indexFinal)


        return PageImpl(TopicoMapper.INSTANCE.mapToTopicosDto(query.resultList),pageable, createTotal(topicoFilter))



    }

    private fun addOrder(pageable: Pageable, criteriaBuilder: CriteriaBuilder, root: Root<Topico>, criteriaQuery: CriteriaQuery<Topico>) {
        val orders = pageable.sort.map { order ->
            val property = order.property
            if (order.isAscending) criteriaBuilder.asc(root.get<Any>(property))
            else criteriaBuilder.desc(root.get<Any>(property))
        }


        criteriaQuery.orderBy(orders.toMutableList())
    }

    private fun createTotal(topicoFilter: TopicoFilter): Long {
        val criteriaBuilder = manager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(Topico::class.java)

        predicateFilter(topicoFilter, criteriaBuilder, root, criteriaQuery)

        criteriaQuery.select(criteriaBuilder.count(root))

        return manager.createQuery(criteriaQuery).singleResult
    }

    private fun <T: Any> predicateFilter(topicoFilter: TopicoFilter, criteriaBuilder: CriteriaBuilder, root: Root<Topico>, criteriaQuery: CriteriaQuery<T>) {
        val predicates = mutableListOf<Predicate>()

        topicoFilter.titulo?.let {
            predicates.add(criteriaBuilder.equal(root.get<String>("titulo"), it))
        }

        topicoFilter.mensagem?.let {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get<String>("mensagem")), "%${it.toUpperCase()}%"))
        }

        topicoFilter.dataCriacao?.let {
            predicates.add(criteriaBuilder.between(root.get<LocalDateTime>("dataCriacao"), it.atTime(0, 0, 0), it.atTime(23, 59, 59)))
        }

        criteriaQuery.where(*predicates.toTypedArray())
    }


}