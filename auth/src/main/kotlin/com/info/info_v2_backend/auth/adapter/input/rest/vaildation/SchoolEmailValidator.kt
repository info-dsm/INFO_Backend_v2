package com.info.info_v2_backend.auth.adapter.input.rest.vaildation

import org.springframework.core.env.Environment
import java.util.Arrays
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class SchoolEmailValidator : ConstraintValidator<SchoolEmail, String> {
    private val possibleDomain: List<String> = listOf("@dsm.hs.kr", "@info-dsm.info")

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        value?.let {
            str: String ->
            return possibleDomain.any {
                str.endsWith(it)
            }
        }?: return false
    }

}
