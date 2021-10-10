package com.avall.kotlin.ms.cousine.consumer.arch.mapper
// (1) import kotlin-reflect extension functions
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * Mapper that can convert one data class into another data class.
 * see: https://medium.com/holisticon-consultants/kotlin-data-class-mapping-aa0f9f750ca1
 * @param <I> inType (convert from)
 * @param <O> outType (convert to)
 */
// (3) class that gets input and output class and implements Mapper   (-> 2)
class DataClassMapper<I : Any, O : Any>(
    private val inType: KClass<I>,
    private val outType: KClass<O>,
    private val mappings: Map<String, String>
) : Mapper<I, O> {

    companion object {
        // (4) provide reified constructor that does not require passing of KClass attributes
        inline operator fun <reified I : Any, reified O : Any> invoke(mappings: Map<KProperty1<I, Any>, KProperty1<O, Any>>? = null) =
            DataClassMapper(inType = I::class, outType = O::class,
                //mappings = mappings?.entries?.associateBy({it.value as KProperty<Any>}, {it.key as KProperty<Any>})?: mapOf()
                mappings = mappings?.entries?.associateBy({it.value.name}, {it.key.name})?: mapOf()
            )

        fun <I : Any, O : Any> setMapper(mapper: Mapper<I, O>) = object : Mapper<Set<I>, Set<O>> {
            override fun invoke(data: Set<I>): Set<O> =
                data.map(mapper).toSet()
        }
    }

    // (5) get constructor of target data class
    private val outConstructor = outType.primaryConstructor!!

    // (6) get properties from input type (lazily)
    private val inPropertiesByName by lazy { inType.memberProperties.associateBy { it.name } }

    // (8) get value of source data via reflection or call registered converter
    private fun argFor(parameter: KParameter, data: I): Any? {
        // get value from input data ...
        var parameterName = mappings[parameter.name]?:parameter.name
        val value = inPropertiesByName[parameterName]?.get(data) ?: return null

        // if a special mapper is registered, use it, otherwise keep value
        return fieldMappers[parameter.name]?.invoke(value) ?: value
    }

    // (7) call the target constructor with a map of source attributes and values, where argFor defines the value to use (see (8)
    override fun invoke(data: I): O = with(outConstructor) {
        callBy(parameters.associateWith { argFor(it, data) })
    }

    // (9) register converter classes on attribute level to support transitive conversions or collection types
    val fieldMappers = mutableMapOf<String, Mapper<Any, Any>>()
    inline fun <reified S : Any, reified T : Any> register(
        parameterName: String,
        crossinline mapper: Mapper<S, T>
    ): DataClassMapper<I, O> = apply {
        // to be able to store the sub-mappers, we have to wrap them as (Any) -> Any
        this.fieldMappers[parameterName] = object : Mapper<Any, Any> {
            override fun invoke(data: Any): Any = mapper.invoke(data as S)
        }
    }

}