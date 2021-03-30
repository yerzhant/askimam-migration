package kz.azan.askimammigration.common.type.ext

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LongKtTest {

    @Test
    internal fun `should give seconds`() {
        assertThat(1564218269624.toSeconds()).isEqualTo(1564218269)
    }

    @Test
    internal fun `should give nano-seconds`() {
        assertThat(1564218269624.toNano()).isEqualTo(624_000_000)
    }
}