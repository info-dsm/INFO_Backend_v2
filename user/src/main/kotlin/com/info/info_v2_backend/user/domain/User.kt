package com.info.info_v2_backend.user.domain

import com.info.info_v2_backend.common.user.Role
import com.info.info_v2_backend.common.user.TeacherDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CustomGrantedAuthority
import com.info.info_v2_backend.user.domain.time.TimeEntity
import org.hibernate.annotations.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.Table

@Where(clause = "user_is_delete = false")
@SQLDelete(sql = "UPDATE `user` SET user_is_delete = true where id = ?")
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Entity
abstract class User(
    name: String,
    email: String,
    password: String,
    role: Role,
    passwordHint: String?
): TimeEntity(), UserDetails {

    @Id
    val email: String = email

    var name: String = name
        protected set

    private var password: String = password

    @Column(name = "password_hint", nullable = true)
    var passwordHint: String? = passwordHint
        protected set

    override fun getPassword(): String {
        return this.password
    }

    @ElementCollection(fetch = FetchType.EAGER)
    var roleList: MutableList<Role> = ArrayList()
        protected set

    @Column(name = "user_is_delete")
    var isDeleted: Boolean = false
        protected set

    init {
        this.roleList.add(role)
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var authorityList: MutableList<GrantedAuthority> = ArrayList()
        this.roleList.map{
            authorityList.add(SimpleGrantedAuthority(it.toString()))
        }
        return authorityList
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonLocked(): Boolean {
        return this.roleList.contains(Role.BLOCK)
    }

    override fun isAccountNonExpired(): Boolean {
        return !this.isDeleted
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return !this.isDeleted
    }

    fun changePassword(password: String){
        this.password = password
    }

    fun toTeacherDto(profilePhoto: String?): TeacherDto {
        return TeacherDto(
            this.name,
            this.email,
            profilePhoto
        )
    }

    fun toCommonUserDetails(): CommonUserDetails {
        val authorityList: MutableList<CustomGrantedAuthority> = ArrayList()
        this.roleList.map {
            authorityList.add(CustomGrantedAuthority(it.mean))
        }
        return CommonUserDetails(
            this.password,
            this.username,
            authorityList,
            if (this is Contactor) {
                (this as Contactor).companyNumber
            } else null
        )
    }

}
