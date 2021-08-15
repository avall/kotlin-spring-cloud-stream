package com.avall.ms.attachments.infrastructure.database


import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name ="attachment")
data class AttachmentDb(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name ="id",nullable = false)
    var id: String?,
    @Column(name ="content_type",nullable = false)
    val contentType : String,
    @Column(name ="description", nullable = true)
    val description : String?,
    @Column(name = "url", nullable = false)
    val url : String,
    @Column(name ="is_private",nullable = false)
    val isPrivate : Boolean?

)