package com.info.info_v2_backend.company.domain.businessArea

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class BusinessArea(
    name: String,
) {

    @Id
    val id: String = name

    @JsonIgnore
    @OneToMany(mappedBy = "businessArea", cascade = [CascadeType.REMOVE])
    var businessAreaTaggedList: MutableList<BusinessAreaTagged> = ArrayList()
        protected set


}