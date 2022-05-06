package edu.uw.ischool.talin16.quizdroid.mappers

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
}