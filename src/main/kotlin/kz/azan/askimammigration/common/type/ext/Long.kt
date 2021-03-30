package kz.azan.askimammigration.common.type.ext

fun Long.toSeconds() = this / 1000

fun Long.toNano() = (this % 1000 * 1_000_000).toInt()
