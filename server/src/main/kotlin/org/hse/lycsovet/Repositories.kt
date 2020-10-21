package org.hse.lycsovet

import org.springframework.data.repository.CrudRepository
import java.util.*

interface AppealTypeCrudRepository : CrudRepository<AppealType, Long> {
    fun findByName(name: String) : Optional<AppealType>
}
interface AppealStatusCrudRepository : CrudRepository<AppealStatus, Long> {
    fun findByName(name: String) : Optional<AppealStatus>
    fun findAllByMilestoneGreaterThanEqual(milestone: Long) : Collection<AppealStatus>
}
interface UserCrudRepository : CrudRepository<User, Long> {
    fun findByEmail(name: String) : Optional<User>
}
interface NewsCrudRepository : CrudRepository<Article, Long> {
    override fun findAll(): List<Article>
    fun findAllByPublished(published: Boolean) : List<Article>
}
interface AppealCrudRepository : CrudRepository<Appeal, Long> {
    override fun findAll(): List<Appeal>
    fun findAllByAuthor(author: User) : List<Appeal>
    fun findAllByStatusInAndPublished(statuses: Collection<AppealStatus>, published: Boolean) : List<Appeal>
}
interface RoleCrudRepository : CrudRepository<Role, Long> {
    fun findByName(name: String) : Optional<Role>
}