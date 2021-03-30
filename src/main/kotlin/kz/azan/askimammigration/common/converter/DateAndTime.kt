package kz.azan.askimammigration.common.converter

import kz.azan.askimammigration.common.type.ext.toNano
import kz.azan.askimammigration.common.type.ext.toSeconds
import java.time.LocalDateTime
import java.time.ZoneOffset

fun toLocalDateTime(timestamp: Long?) =
    timestamp?.run {
        LocalDateTime.ofEpochSecond(timestamp.toSeconds(), timestamp.toNano(), ZoneOffset.ofHours(6))
    }
