package com.info.info_v2_backend.auth.adapter.input.rest.vaildation

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SchoolEmailValidator::class])
@MustBeDocumented
annotation class SchoolEmail(
    val message: String = "허용된 도메인의 이메일이 아닙니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)

