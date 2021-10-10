package com.avall.kotlin.ms.cousine.consumer.arch.mapper

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.LocalDateTime


class DataClassMapperTests {

    data class PlainObject(
        val id: Int,
        val name: String,
        val time: LocalDateTime
    )

    data class PlainObjectSameStructure(
        val id: Int,
        val name: String,
        val time: LocalDateTime
    )

    data class PlainObjectDifferentStructure(
        val id2: Int,
        val name2: String,
        val time2: LocalDateTime
    )

    data class WrapperObject(
        val id: Int,
        val nested: PlainObject
    )

    data class WrapperObjectSameStructure(
        val id: Int,
        val nested: PlainObjectSameStructure
    )

    @Test
    fun `Given PlainObject When map to PlainObjectSameStructure Then all fields mapped`() {
        val mapper = DataClassMapper<PlainObject, PlainObjectSameStructure>();
        val obj = PlainObject(
            id = 24,
            name = "Name",
            time = LocalDateTime.now()
        )
        val mappedObj = mapper.invoke(obj)
        expectThat(mappedObj) {
            get { id } isEqualTo obj.id
            get { name } isEqualTo obj.name
            get { time } isEqualTo obj.time
        }
    }

    @Test
    fun `Given PlainObject When map to PlainObjectDifferentStructure Then all fields mapped`() {
        val mapper = DataClassMapper(
            mappings = mapOf(
                PlainObject::id to PlainObjectDifferentStructure::id2,
                PlainObject::name to PlainObjectDifferentStructure::name2,
                PlainObject::time to PlainObjectDifferentStructure::time2
            )
        )
        val obj = PlainObject(
            id = 24,
            name = "Name",
            time = LocalDateTime.now()
        )
        val mappedObj = mapper.invoke(obj)
        expectThat(mappedObj) {
            get { id2 } isEqualTo obj.id
            get { name2 } isEqualTo obj.name
            get { time2 } isEqualTo obj.time
        }
    }

    @Test
    fun `Given WrapperObject When map to WrapperObjectSameStructure Then all fields mapped`() {
        val nestedMapper = DataClassMapper<PlainObject, PlainObjectSameStructure>()
        val mapper = DataClassMapper<WrapperObject, WrapperObjectSameStructure>()
            .register("nested", nestedMapper)

        val obj = WrapperObject(
            id = 24,
            nested = PlainObject(
                id = 25,
                name = "Name",
                time = LocalDateTime.now()
            )
        )

        val mappedObj = mapper.invoke(obj)
        expectThat(mappedObj) {
            get { id } isEqualTo obj.id
            get { nested.id } isEqualTo obj.nested.id
            get { nested.name } isEqualTo obj.nested.name
            get { nested.time } isEqualTo obj.nested.time
        }
    }
}